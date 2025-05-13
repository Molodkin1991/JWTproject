package org.example.jwtproject.repo;

import org.example.jwtproject.endpoint.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
