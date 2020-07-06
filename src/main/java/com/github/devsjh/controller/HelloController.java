package com.github.devsjh.controller;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @title  : 테스트 컨트롤러 클래스
 * @author : jaeha-dev (Git)
 * @memo   : https://daddyprogrammer.org (원문)
 */
@Controller
public class HelloController {

    @GetMapping(value = "/")
    public String home(Model model) {
        Map<String, Object> map = new HashMap<>();
        map.put("url","http://localhost:8000/swagger-ui.html");
        map.put("currentTime", LocalDateTime.now());
        model.addAttribute("data", map);

        return "home";
    }

    @GetMapping(value = "/hello")
    @ResponseBody
    public String helloString() {
        // 문자열
        return "Hello, Spring!";
    }

    @GetMapping(value = "/hello/json")
    @ResponseBody
    public Hello helloJson() {
        // hello 객체를 직렬화한 JSON 데이터
        return Hello.builder().message("안녕, 스프링!").currentTime(Instant.now()).build();
    }

    @GetMapping(value = "/hello/page")
    public String helloPage(Model model) {
        Hello hello = Hello.builder().message("안녕, 스프링!").currentTime(Instant.now()).build();
        model.addAttribute("hello", hello);

        // hello.html
        return "hello";
    }

    @Data
    public static class Hello {
        private String message;
        private Instant currentTime;

        @Builder
        public Hello(String message, Instant currentTime) {
            this.message = message;
            this.currentTime = currentTime;
        }
    }
}