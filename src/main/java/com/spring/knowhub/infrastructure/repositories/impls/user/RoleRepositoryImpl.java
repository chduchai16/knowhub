package com.spring.knowhub.infrastructure.repositories.impls.user;

import com.spring.knowhub.domain.exceptions.user.role.RoleNotFoundException;
import com.spring.knowhub.domain.models.user.Permission;
import com.spring.knowhub.domain.models.user.Role;
import com.spring.knowhub.domain.repositories.user.RoleRepository;
import com.spring.knowhub.infrastructure.entities.user.PermissionEntity;
import com.spring.knowhub.infrastructure.entities.user.RoleEntity;
import com.spring.knowhub.infrastructure.exceptions.user.permission.PermissionMapperException;
import com.spring.knowhub.infrastructure.exceptions.user.role.RoleMapperException;
import com.spring.knowhub.infrastructure.exceptions.user.role.RoleRepositoryException;
import com.spring.knowhub.infrastructure.mappers.user.PermissionMapper;
import com.spring.knowhub.infrastructure.mappers.user.RoleMapper;
import com.spring.knowhub.infrastructure.repositories.jpas.user.JpaRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {

    private final JpaRoleRepository jpaRoleRepository;
    private final RoleMapper roleMapper;
    private final PermissionMapper permissionMapper ;

    @Override
    public Optional<Role> save(Role role) {
        log.info("Bắt đầu lưu vai trò: {}", role.getName());
        try {
            RoleEntity entity = roleMapper.fromDomainToEntity(role);
            RoleEntity savedEntity = jpaRoleRepository.save(entity);
            Role savedRole = roleMapper.fromEntityToDomain(savedEntity);
            log.info("Vai trò đã lưu thành công với ID: {}", savedRole.getId());
            return Optional.of(savedRole);
        } catch (RoleMapperException ex) {
            log.error("Lỗi mapping khi lưu vai trò: {}", role.getName(), ex);
            throw ex;
        } catch (Exception e) {
            log.error("Lỗi khi lưu vai trò: {}", role.getName(), e);
            throw RoleRepositoryException.saveFailed(e.getMessage());
        }
    }

    @Override
    public Void deleteById(Long id) {
        log.info("Bắt đầu xóa vai trò với ID: {}", id);
        try {
            Optional<RoleEntity> existingRole = jpaRoleRepository.findById(id);
            if (existingRole.isEmpty()) {
                log.warn("Không tìm thấy vai trò với ID: {}", id);
                throw RoleNotFoundException.byId(id);
            }
            jpaRoleRepository.deleteById(id);
            log.info("Xóa vai trò thành công với ID: {}", id);
        } catch (RoleNotFoundException ex) {
            log.error("Lỗi khi xóa vai trò với ID: {}", id, ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi xóa vai trò với ID: {}", id, ex);
            throw RoleRepositoryException.deleteFailed(ex.getMessage());
        }
        return null;
    }

    @Override
    public Optional<Role> findById(Long id) {
        log.info("Bắt đầu tìm vai trò với ID: {}", id);
        try {
            Optional<RoleEntity> entityOpt = jpaRoleRepository.findById(id);
            if (entityOpt.isEmpty()) {
                log.warn("Không tìm thấy vai trò với ID: {}", id);
                throw RoleNotFoundException.byId(id);
            }
            Role role = roleMapper.fromEntityToDomain(entityOpt.get());
            log.info("Tìm thấy vai trò với ID: {}", id);
            return Optional.of(role);
        } catch (RoleNotFoundException ex) {
            log.error("Lỗi khi tìm vai trò với ID: {}", id, ex);
            throw ex;
        } catch (RoleMapperException ex) {
            log.error("Lỗi mapping khi tìm vai trò với ID: {}", id, ex);
            throw ex;
        } catch (Exception e) {
            log.error("Lỗi khi tìm vai trò với ID: {}", id, e);
            throw RoleRepositoryException.findFailed(e.getMessage());
        }
    }

    @Override
    public Page<Role> findRolesPaged(Pageable pageable) {
        log.info("Bắt đầu lấy danh sách vai trò phân trang: page={}, size={}",
                pageable.getPageNumber(), pageable.getPageSize());
        try {
            Page<RoleEntity> roleEntitiesPage = jpaRoleRepository.findAll(pageable);
            Page<Role> rolesPage = roleEntitiesPage.map(roleMapper::fromEntityToDomain);
            log.info("Lấy danh sách vai trò thành công, tổng: {}", rolesPage.getTotalElements());
            return rolesPage;
        } catch (RoleMapperException ex) {
            log.error("Lỗi mapping khi phân trang vai trò", ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi phân trang vai trò", ex);
            throw RoleRepositoryException.findFailed(ex.getMessage());
        }
    }

    @Override
    public Set<Permission> findByIds(Set<Long> ids) {
        log.info("Bắt đầu tìm quyền với IDs: {}", ids);
        try {
            Set<PermissionEntity> permissions = jpaRoleRepository.findByIdIn(ids);
            Set<Permission> permissionSet = permissions.stream().map(permissionMapper::fromEntityToDomain).collect(java.util.stream.Collectors.toSet());
            log.info("Tìm thấy {} quyền với IDs: {}", permissions.size(), ids);
            return permissionSet;
        } catch (PermissionMapperException ex){
            log.error("Lỗi mapping khi tìm quyền với IDs: {}", ids, ex);
            throw ex;
        }catch (Exception ex) {
            log.error("Lỗi khi tìm quyền với IDs: {}", ids, ex);
            throw RoleRepositoryException.findFailed(ex.getMessage());
        }
    }
}

