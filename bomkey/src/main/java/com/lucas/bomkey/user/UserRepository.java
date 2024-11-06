package com.lucas.bomkey.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserEmail(String email);

    @Query("SELECT user FROM User user JOIN FETCH user.authorities WHERE user.username=:username")
    User findByUsername(String username);
}
