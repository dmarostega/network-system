package com.sistema.s4;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "Spring Boot está rodando 🚀";
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
