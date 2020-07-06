package com.github.devsjh.service.security;

import com.github.devsjh.advice.exception.CustomUserNotFoundException;
import com.github.devsjh.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @title  : 계정 조회 서비스 클래스
 * @author : jaeha-dev (Git)
 * @memo   : https://daddyprogrammer.org (원문)
 */
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return userRepository.findById(Long.parseLong(userId))
                             .orElseThrow(CustomUserNotFoundException::new);
    }
}