package com.github.devsjh.service;

import com.github.devsjh.domain.User;
import com.github.devsjh.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * @title  : 계정 관리 서비스 클래스
 * @author : jaeha-dev (Git)
 * @memo   : https://daddyprogrammer.org (원문)
 */
@RequiredArgsConstructor
@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    // 계정 조회
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // 계정 조회
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // 계정 수정
    public User editByNameAndPassword(String name, String password) {
        User user = User.builder().name(name)
                                  .password(passwordEncoder.encode(password))
                                  .build();

        // save() 시 수정한 데이터를 반영한다.
        return userRepository.save(user);
    }

    // 계정 삭제
    public void deleteByEmailAndPassword(String email, String password) {
        userRepository.deleteByEmailAndPassword(email, password);
    }
}