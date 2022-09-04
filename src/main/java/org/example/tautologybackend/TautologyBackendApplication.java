package org.example.tautologybackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc // to fix issue with springfox error
public class TautologyBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TautologyBackendApplication.class, args);
    }

}
