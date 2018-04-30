package com.oauth2.example.repositories;

import com.oauth2.example.entities.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
@Primary
public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findOneByUsername(String username);
}
