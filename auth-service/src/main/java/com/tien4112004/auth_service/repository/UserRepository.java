package com.tien4112004.auth_service.repository;

import com.tien4112004.auth_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {
    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserById(UUID id);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
