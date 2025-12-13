package com.spring.knowhub.infrastructure.repositories.impls.user;

import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.domain.repositories.user.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public Optional<User> save(User user) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public Page<User> findUsersPaged(Pageable pageable) {
        return null;
    }
}
