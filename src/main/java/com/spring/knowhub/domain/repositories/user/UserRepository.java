package com.spring.knowhub.domain.repositories.user;

import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.specifications.Specification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);

    void deleteById(Long id);

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    List<User> findByIds(List<Long> ids);

    Optional<User> findByEmail(String email);

    Page<User> findUsersPaged(Specification<User> specification, Pageable pageable);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsById(Long id);
}
