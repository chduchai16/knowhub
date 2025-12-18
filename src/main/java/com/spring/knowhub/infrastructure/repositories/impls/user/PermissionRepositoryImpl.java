package com.spring.knowhub.infrastructure.repositories.impls.user;

import com.spring.knowhub.domain.exceptions.user.permission.PermissionNotFoundException;
import com.spring.knowhub.domain.models.user.Permission;
import com.spring.knowhub.domain.repositories.user.PermissionRepository;
import com.spring.knowhub.infrastructure.entities.user.PermissionEntity;
import com.spring.knowhub.infrastructure.exceptions.user.permission.PermissionMapperException;
import com.spring.knowhub.infrastructure.exceptions.user.permission.PermissionRepositoryException;
import com.spring.knowhub.infrastructure.mappers.user.PermissionMapper;
import com.spring.knowhub.infrastructure.repositories.jpas.user.JpaPermissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PermissionRepositoryImpl implements PermissionRepository {
    private final JpaPermissionRepository jpaPermissionRepository;
    private final PermissionMapper permissionMapper ;

    @Override
    public Optional<Permission> save(Permission permission) {
        log.info("Đang lưu quyền: {}", permission.getCode());
        try {
            PermissionEntity entity = permissionMapper.fromDomainToEntity(permission);
            PermissionEntity savedEntity = jpaPermissionRepository.save(entity);
            Permission savedPermission = permissionMapper.fromEntityToDomain(savedEntity);
            log.info("Quyền đã lưu thành công với ID: {}", savedPermission.getId());
            return Optional.of(savedPermission);
        } catch (PermissionNotFoundException e) {
            log.error("Lỗi khi tìm kiếm quyền liên quan khi lưu: {}", permission.getCode(), e);
            throw e;
        }
        catch (PermissionMapperException e) {
            log.error("Lỗi mapping khi lưu quyền: {}", permission.getCode(), e);
            throw e;
        }
        catch (Exception e) {
            log.error("Lỗi khi thực hiện lưu quyền: {}", permission.getCode(), e);
            throw PermissionRepositoryException.saveFailed("Lỗi khi lưu quyền: " + permission.getCode());
        }
    }

    @Override
    public void deleteById(Long id) {
        log.info("Xóa quyền với ID: {}", id);
        try {
            Optional <Permission> permissionOpt = findById(id);
            if (permissionOpt.isEmpty()) {
                throw PermissionNotFoundException.permissionNotFoundById(id);
            }
            jpaPermissionRepository.deleteById(id);
            log.info("Quyền với ID {} đã được xóa thành công", id);
        }
        catch (PermissionNotFoundException ex) {
            log.error("Lỗi khi xóa quyền: {}", ex.getMessage(), ex);
            throw ex;
        }
        catch (Exception ex) {
            log.error("Lỗi khi thực hiện xóa quyền với ID {}: {}", id, ex.getMessage(), ex);
            throw PermissionRepositoryException.deleteFailed("Lỗi khi xóa quyền với ID " + id);
        }
    }

    @Override
    public Optional<Permission> findById(Long id) {
        log.info("Tìm kiếm quyền với ID: {}", id);
        try {
            Optional<PermissionEntity> entityOpt = jpaPermissionRepository.findById(id);
            if (entityOpt.isEmpty()) {
                throw PermissionNotFoundException.permissionNotFoundById(id);
            }
            Permission permission = permissionMapper.fromEntityToDomain(entityOpt.get());
            return Optional.of(permission);
        } catch (PermissionNotFoundException ex) {
            log.error("Lỗi khi tìm kiếm quyền: {}", ex.getMessage(), ex);
            throw ex;
        }catch (PermissionMapperException ex) {
            log.error("Lỗi khi map Permission Entity sang Permission Domain cho ID {}: {}", id, ex.getMessage(), ex);
            throw ex;
        }
        catch (Exception ex) {
            log.error("Lỗi khi thực hiện tìm kiếm quyền với ID {}: {}", id, ex.getMessage(), ex);
            throw PermissionRepositoryException.findFailed("Lỗi khi tìm kiếm quyền với ID " + id);
        }
    }

    @Override
    public List<Permission> findAll() {
        log.info("Tìm kiếm tất cả quyền");
        try {
            List<PermissionEntity> entities = jpaPermissionRepository.findAll();
            log.info("Tìm thấy {} quyền", entities.size());
            return entities.stream().map(permissionMapper::fromEntityToDomain).toList();
        } catch (Exception e) {
            log.error("Lỗi khi thực hiện tìm kiếm tất cả quyền: {}", e.getMessage(), e);
            throw PermissionRepositoryException.findFailed("Lỗi khi tìm kiếm tất cả quyền: " + e.getMessage());
        }
    }
}
