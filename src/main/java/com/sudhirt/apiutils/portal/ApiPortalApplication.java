package com.sudhirt.apiutils.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
public class ApiPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiPortalApplication.class, args);
    }
}
