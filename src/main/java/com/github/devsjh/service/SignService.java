package com.github.devsjh.service;

import com.github.devsjh.config.security.JwtProvider;
import com.github.devsjh.domain.User;
import com.github.devsjh.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Optional;

/**
 * @title  : 계정 등록/로그인 서비스 클래스
 * @author : jaeha-dev (Git)
 * @memo   : https://daddyprogrammer.org (원문)
 */
@RequiredArgsConstructor
@Service
public class SignService {

    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    // 계정 등록
    public User join(String name, String email, String password) {
        User user = User.builder().name(name)
                                  .email(email)
                                  .password(passwordEncoder.encode(password))
                                  // 권한을 리스트 타입으로 갖는다면 singletonList()를 사용한다.
                                  .roles(Collections.singleton("ROLE_USER"))
                                  .build();

        return userRepository.save(user);
    }

    // 계정 로그인
    public Optional<User> login(String email, String password) {
        Optional<User> optUser = userRepository.findByEmail(email);

        if (optUser.isPresent()) {
            // optUser가 null이 아닌 경우
            if (! passwordEncoder.matches(password, optUser.get().getPassword())) {
                // 비밀번호 불일치 시 null을 담은 빈 객체를 반환한다.
                return Optional.empty();
            }
        }

        return optUser;
    }

    // JWT 발급
    public String createToken(User user) {
        return jwtProvider.createToken(String.valueOf(user.getId()), user.getRoles());
    }
}