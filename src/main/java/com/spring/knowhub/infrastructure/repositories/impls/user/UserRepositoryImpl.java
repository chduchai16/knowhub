package com.spring.knowhub.infrastructure.repositories.impls.user;

import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.repositories.user.UserRepository;
import com.spring.knowhub.infrastructure.entities.user.UserEntity;
import com.spring.knowhub.infrastructure.exceptions.user.UserMapperException;
import com.spring.knowhub.infrastructure.exceptions.user.UserRepositoryException;
import com.spring.knowhub.infrastructure.mappers.user.UserMapper;
import com.spring.knowhub.infrastructure.repositories.jpas.user.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository userJpaRepository;
    private final UserMapper userMapper;

    @Override
    public Optional<User> save(User user) {
        log.info("Đang lưu user: {}", user.getUsername());
        try {
            UserEntity entity = userMapper.fromDomainToEntity(user);
            UserEntity savedEntity = userJpaRepository.save(entity);
            User savedUser = userMapper.fromEntityToDomain(savedEntity);
            log.info("User đã lưu thành công với ID: {}", savedUser.getId());
            return Optional.of(savedUser);
        } catch (DataIntegrityViolationException ex) {
            log.error("Vi phạm ràng buộc database: {}", ex.getMessage(), ex);
            if (ex.getMessage() != null && ex.getMessage().contains("email")) {
                throw UserRepositoryException.constraintViolation("email", user.getEmail());
            }
            if (ex.getMessage() != null && ex.getMessage().contains("username")) {
                throw UserRepositoryException.constraintViolation("username", user.getUsername());
            }
            throw UserRepositoryException.saveFailed(ex.getMessage());
        } catch (UserMapperException ex) {
            log.error("Lỗi mapping khi lưu user: {}", ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi không lường trước khi lưu user", ex);
            throw UserRepositoryException.saveFailed(ex.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        log.info("Xóa user với ID: {}", id);
        try {
            userJpaRepository.deleteById(id);
            log.info("User với ID {} đã được xóa thành công", id);
        } catch (Exception ex) {
            log.error("Lỗi khi xóa user với ID: {}", id, ex);
            throw UserRepositoryException.deleteFailed(ex.getMessage());
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
            log.error("Lỗi database khi tìm user theo ID: {}", id, ex);
            throw UserRepositoryException.findFailed(ex.getMessage());
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        log.debug("Tìm user theo username: {}", username);
        try {
            return userJpaRepository.findByUsername(username)
                    .map(userMapper::fromEntityToDomain);
        } catch (UserMapperException ex) {
            log.error("Lỗi mapping khi tìm user theo username: {}", username, ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi database khi tìm user theo username: {}", username, ex);
            throw UserRepositoryException.findFailed(ex.getMessage());
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
            log.error("Lỗi database khi tìm user theo email: {}", email, ex);
            throw UserRepositoryException.findFailed(ex.getMessage());
        }
    }

    @Override
    public Page<User> findUsersPaged(Pageable pageable) {
        log.debug("Lấy danh sách user phân trang: page={}, size={}", 
                  pageable.getPageNumber(), pageable.getPageSize());
        try {
            Page<UserEntity> entityPage = userJpaRepository.findAll(pageable);
            return entityPage.map(userMapper::fromEntityToDomain);
        } catch (UserMapperException ex) {
            log.error("Lỗi mapping khi lấy danh sách user phân trang", ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Lỗi database khi lấy danh sách user phân trang", ex);
            throw UserRepositoryException.findFailed(ex.getMessage());
        }
    }
}
