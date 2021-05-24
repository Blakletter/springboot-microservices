package com.cancerup.sqlaccesslayer;


import com.cancerup.sqlaccesslayer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Transactional
    long deleteByEmail(String email);
}
