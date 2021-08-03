package org.example.textwrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class TextWrapApplication {

    public static void main(String[] args) {
        SpringApplication.run(TextWrapApplication.class, args);
    }
}
