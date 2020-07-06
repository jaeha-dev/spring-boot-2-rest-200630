package com.github.devsjh.config.security;

import com.github.devsjh.service.security.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.Set;

/**
 * @title  : JWT 프로바이더 클래스
 * @author : jaeha-dev (Git)
 * @memo   : https://daddyprogrammer.org (원문)
 */
@RequiredArgsConstructor
@Component
public class JwtProvider {

    @Value("${spring.jwt.secret}")
    private String secretKey;

    private Long tokenValidMs;

    private final CustomUserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        tokenValidMs = 1000L * 60 * 60; // 1시간
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // JWT 생성
    public String createToken(String userId, Set<String> roles) {
        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("roles", roles);

        Date now = new Date();
        return Jwts.builder().setClaims(claims) // 데이터
                             .setIssuedAt(now) // 토큰 발행 일자
                             .setExpiration(new Date(now.getTime() + tokenValidMs)) // 토큰 만료 일자
                             .signWith(SignatureAlgorithm.HS256, secretKey) // 암호화
                             .compact();
    }

    // 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserId(token));

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 계정 구별 정보 추출
    public String getUserId(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                            .parseClaimsJws(token)
                            .getBody()
                            .getSubject();
    }

    // 요청의 헤더에서 토큰 파싱 ("X-AUTH-TOKEN: jwt")
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    // JWT 유효성 및 만료 시각 검사
    public boolean validateToken(String jwt) {
        try {
            // 유효성 검사
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt);

            // 만료 시각 검사
            return ! claims.getBody().getExpiration().before(new Date());

        } catch (Exception e) {
            return false;
        }
    }
}