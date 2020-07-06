package com.github.devsjh.repository;

import com.github.devsjh.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * @title  : 계정 레포지토리 클래스
 * @author : jaeha-dev (Git)
 * @memo   : https://daddyprogrammer.org (원문)
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);
    void deleteByEmailAndPassword(String email, String password);
}