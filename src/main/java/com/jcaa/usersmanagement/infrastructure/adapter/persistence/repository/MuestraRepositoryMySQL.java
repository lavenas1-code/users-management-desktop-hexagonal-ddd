package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.DeleteMuestraPort;
import com.jcaa.usersmanagement.application.port.out.GetAllMuestrasPort;
import com.jcaa.usersmanagement.application.port.out.GetMuestraByCodigoPort;
import com.jcaa.usersmanagement.application.port.out.GetMuestraByIdPort;
import com.jcaa.usersmanagement.application.port.out.SaveMuestraPort;
import com.jcaa.usersmanagement.application.port.out.UpdateMuestraPort;
import com.jcaa.usersmanagement.domain.exception.MuestraNotFoundException;
import com.jcaa.usersmanagement.domain.model.MuestraModel;
import com.jcaa.usersmanagement.domain.valueobject.MuestraCodigo;
import com.jcaa.usersmanagement.domain.valueobject.MuestraId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.MuestraPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.MuestraPersistenceMapper;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public final class MuestraRepositoryMySQL
    implements SaveMuestraPort,
        UpdateMuestraPort,
        GetMuestraByIdPort,
        GetMuestraByCodigoPort,
        GetAllMuestrasPort,
        DeleteMuestraPort {

  private static final String SQL_INSERT =
      "INSERT INTO muestras "
      + "(id, codigo, lote_id, descripcion, estado, created_at, updated_at) "
      + "VALUES (?, ?, ?, ?, ?, NOW(), NOW())";

  private static final String SQL_UPDATE =
      "UPDATE muestras SET codigo = ?, lote_id = ?, descripcion = ?, estado = ?, updated_at = NOW() "
      + "WHERE id = ?";

  private static final String SQL_SELECT_BY_ID =
      "SELECT id, codigo, lote_id, descripcion, estado, created_at, updated_at "
      + "FROM muestras "
      + "WHERE id = ? LIMIT 1";

  private static final String SQL_SELECT_BY_CODIGO =
      "SELECT id, codigo, lote_id, descripcion, estado, created_at, updated_at "
      + "FROM muestras "
      + "WHERE codigo = ? LIMIT 1";

  private static final String SQL_SELECT_ALL =
      "SELECT id, codigo, lote_id, descripcion, estado, created_at, updated_at "
      + "FROM muestras "
      + "ORDER BY codigo ASC";

  private static final String SQL_DELETE =
        "DELETE FROM muestras "
        + "WHERE id = ?";

  private final Connection connection;

  @Override
  public MuestraModel save(final MuestraModel muestra) {
    final MuestraPersistenceDto dto = MuestraPersistenceMapper.fromModelToDto(muestra);
    executeSave(dto);
    return findByIdOrFail(muestra.getId());
  }

  @Override
  public MuestraModel update(final MuestraModel muestra) {
    final MuestraPersistenceDto dto = MuestraPersistenceMapper.fromModelToDto(muestra);
    executeUpdate(dto);
    return findByIdOrFail(muestra.getId());
  }

  @Override
  public Optional<MuestraModel> getById(final MuestraId muestraId) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
      statement.setString(1, muestraId.value());
      final ResultSet resultSet = statement.executeQuery();
      if (!resultSet.next()) {
        return Optional.empty();
      }
      return Optional.of(MuestraPersistenceMapper.fromResultSetToModel(resultSet));
    } catch (final SQLException exception) {
      throw PersistenceException.becauseMuestraFindByIdFailed(muestraId.value(), exception);
    }
  }

  @Override
  public Optional<MuestraModel> getByCodigo(final MuestraCodigo codigo) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_CODIGO)) {
      statement.setString(1, codigo.value());
      final ResultSet resultSet = statement.executeQuery();
      if (!resultSet.next()) {
        return Optional.empty();
      }
      return Optional.of(MuestraPersistenceMapper.fromResultSetToModel(resultSet));
    } catch (final SQLException exception) {
      throw PersistenceException.becauseMuestraFindByCodigoFailed(codigo.value(), exception);
    }
  }

  @Override
  public List<MuestraModel> getAll() {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)) {
      final ResultSet resultSet = statement.executeQuery();
      return MuestraPersistenceMapper.fromResultSetToModelList(resultSet);
    } catch (final SQLException exception) {
      throw PersistenceException.becauseMuestraFindAllFailed(exception);
    }
  }

  @Override
  public void delete(final MuestraId muestraId) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
      statement.setString(1, muestraId.value());
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseMuestraDeleteFailed(muestraId.value(), exception);
    }
  }

  private void executeSave(final MuestraPersistenceDto dto) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
      statement.setString(1, dto.id());
      statement.setString(2, dto.codigo());
      statement.setString(3, dto.loteId());
      statement.setString(4, dto.descripcion());
      statement.setString(5, dto.estado());
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseMuestraSaveFailed(dto.id(), exception);
    }
  }

  private void executeUpdate(final MuestraPersistenceDto dto) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
      statement.setString(1, dto.codigo());
      statement.setString(2, dto.loteId());
      statement.setString(3, dto.descripcion());
      statement.setString(4, dto.estado());
      statement.setString(5, dto.id());
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseMuestraUpdateFailed(dto.id(), exception);
    }
  }

  private MuestraModel findByIdOrFail(final MuestraId muestraId) {
    return getById(muestraId)
        .orElseThrow(() -> MuestraNotFoundException.becauseIdWasNotFound(muestraId.value()));
  }
}
