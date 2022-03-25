package com.izobretatel777.chat.dao.repo;

import com.izobretatel777.chat.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByLogin(String login);

    @Query(value = "select id from users where login=?1", nativeQuery = true)
    Long findIdByLogin(String login);

    User findByPhoneNumber(String phoneNumber);
}