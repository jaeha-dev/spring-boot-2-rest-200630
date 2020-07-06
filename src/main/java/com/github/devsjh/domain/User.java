package com.github.devsjh.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @title  : 계정 엔터티 클래스
 * @author : jaeha-dev (Git)
 * @memo   : https://daddyprogrammer.org (원문)
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User extends BaseTime implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 계정 번호

    @Column(nullable = false, length = 50)
    private String name; // 사용자 이름

    @Column(nullable = false, unique = true, length = 50)
    private String email; // 계정 이메일

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // JSON 결과로 출력하지 않는다.
    @Column(length = 100)
    private String password; // 계정 비밀번호

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private Set<String> roles = new HashSet<>(); // 계정 권한

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                         .map(SimpleGrantedAuthority::new)
                         .collect(Collectors.toSet());
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getUsername() {
        return this.email;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override // 계정 만료 확인 (사용 안 함: T)
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override // 계정 잠김 확인 (사용 안 함: T)
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override // 계정 비밀번호 확인 (사용 안 함: T)
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override // 계정 활성 확인 (사용 안 함: T)
    public boolean isEnabled() {
        return true;
    }
}