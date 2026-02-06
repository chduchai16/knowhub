package com.spring.knowhub.infrastructure.repositories.impls.user;

import com.spring.knowhub.domain.exceptions.user.user.DuplicateUserException;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.repositories.user.UserRepository;
import com.spring.knowhub.domain.specifications.Specification;
import com.spring.knowhub.infrastructure.entities.user.UserEntity;
import com.spring.knowhub.infrastructure.exceptions.user.user.UserMapperException;
import com.spring.knowhub.infrastructure.exceptions.user.user.UserRepositoryException;
import com.spring.knowhub.infrastructure.mappers.user.UserMapper;
import com.spring.knowhub.infrastructure.repositories.jpas.user.JpaUserRepository;
import com.spring.knowhub.infrastructure.repositories.specifications.user.UserJpaSpecificationAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository userJpaRepository;
    private final UserMapper userMapper;

    @Override
    public User save(User user) {
        log.info("Đang lưu user: {}", user.getUsername());
        try {
            UserEntity entity = userMapper.fromDomainToEntity(user);
            UserEntity savedEntity = userJpaRepository.save(entity);
            User savedUser = userMapper.fromEntityToDomain(savedEntity);
            log.info("User đã lưu thành công với ID: {}", savedUser.getId());
            return savedUser;
        } catch (DuplicateUserException ex) {
            log.error("Lỗi trùng lặp khi lưu user: {}", user.getUsername(), ex);
            throw ex;
        } catch (DataIntegrityViolationException ex) {
            log.error("Vi phạm ràng buộc database khi lưu user: {}", user.getUsername(), ex);
            throw new UserRepositoryException("Vi phạm ràng buộc database: " + ex.getMostSpecificCause().getMessage(),
                    ex);
        } catch (UserMapperException ex) {
            log.error("Lỗi mapping khi lưu user: {}", user.getUsername(), ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi thực hiện lưu user: {}", user.getUsername(), ex);
            throw UserRepositoryException.saveFailed("Lỗi khi lưu user: " + user.getUsername());
        }
    }

    @Override
    public void deleteById(Long id) {
        log.info("Xóa user với ID: {}", id);
        try {
            userJpaRepository.deleteById(id);
            log.info("User với ID {} đã được xóa thành công", id);
        } catch (Exception ex) {
            log.error("Lỗi khi thực hiện xóa user theo ID: {}", id, ex);
            throw UserRepositoryException.deleteFailed("Lỗi khi xóa user theo ID: " + id);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        log.debug("Tìm user theo ID: {}", id);
        try {
            return userJpaRepository.findById(id)
                    .map(userMapper::fromEntityToDomain);
        } catch (UserMapperException ex) {
            log.error("Lỗi mapping khi tìm user theo ID: {}", id, ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi thực hiện tìm kiếm user theo ID: {}", id, ex);
            throw UserRepositoryException.findFailed("Lỗi khi tìm user theo ID: " + id);
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        log.debug("Tìm user theo username: {}", username);
        try {
            return userJpaRepository.findByUsername(username).map(userMapper::fromEntityToDomain);
        } catch (UserMapperException ex) {
            log.error("Lỗi mapping khi tìm user theo username: {}", username, ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi thực hiện tìm kiếm user theo username: {}", username, ex);
            throw UserRepositoryException.findFailed("Lỗi khi tìm user theo username: " + username);
        }
    }

    @Override
    public List<User> findByIds(List<Long> ids) {
        log.debug("Tìm user theo danh sách ID: {}", ids);
        try {
            List<UserEntity> entities = userJpaRepository.findAllById(ids);
            List<User> users = entities.stream().map(userMapper::fromEntityToDomain).toList();
            log.info("Tìm thấy {} user theo danh sách ID", users.size());
            return users;
        } catch (UserMapperException ex) {
            log.error("Lỗi mapping khi tìm user theo danh sách ID: {}", ids, ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi thực hiện tìm kiếm user theo danh sách ID: {}", ids, ex);
            throw UserRepositoryException.findFailed("Lỗi khi tìm user theo danh sách ID: " + ids);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        log.debug("Tìm user theo email: {}", email);
        try {
            return userJpaRepository.findByEmail(email)
                    .map(userMapper::fromEntityToDomain);
        } catch (UserMapperException ex) {
            log.error("Lỗi mapping khi tìm user theo email: {}", email, ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi thực hiện tìm kiếm user theo email: {}", email, ex);
            throw UserRepositoryException.findFailed("Lỗi khi tìm user theo email: " + email);
        }
    }

    @Override
    public Page<User> findUsersPaged(Specification<User> specification, Pageable pageable) {
        log.info(
                "Lấy danh sách user phân trang: page={}, size={}",
                pageable.getPageNumber(),
                pageable.getPageSize());
        try {
            org.springframework.data.jpa.domain.Specification<UserEntity> jpaSpec = UserJpaSpecificationAdapter
                    .toJpaSpecification(specification);
            Page<UserEntity> entityPage = userJpaRepository.findAll(jpaSpec, pageable);
            return entityPage.map(userMapper::fromEntityToDomain);
        } catch (UserMapperException ex) {
            log.error("Lỗi mapping khi lấy danh sách user phân trang", ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi khi thực hiện lấy danh sách user phân trang", ex);
            throw UserRepositoryException.findFailed("Lỗi khi lấy danh sách user phân trang: " + ex.getMessage());
        }
    }

    @Override
    public Boolean existsByUsername(String username) {
        log.info("Kiểm tra tồn tại user theo username: {}", username);
        try {
            return userJpaRepository.existsByUsername(username);
        } catch (Exception ex) {
            log.error("Lỗi khi kiểm tra tồn tại user theo username: {}", username, ex);
            throw UserRepositoryException.findFailed("Lỗi khi kiểm tra tồn tại user theo username: " + username);
        }
    }

    @Override
    public Boolean existsByEmail(String email) {
        log.info("Kiểm tra tồn tại user theo email: {}", email);
        try {
            return userJpaRepository.existsByEmail(email);
        } catch (Exception ex) {
            log.error("Lỗi khi kiểm tra tồn tại user theo email: {}", email, ex);
            throw UserRepositoryException.findFailed("Lỗi khi kiểm tra tồn tại user theo email: " + email);
        }
    }
}
