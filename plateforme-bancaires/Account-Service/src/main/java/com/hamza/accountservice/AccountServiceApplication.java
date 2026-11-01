package com.hamza.accountservice;

import com.hamza.accountservice.domain.AccountStatus;
import com.hamza.accountservice.dtos.AccountRequestDto;
import com.hamza.accountservice.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start (AccountService service) {
        return (args) -> {
            for (int i = 0 ; i < 10 ; i++) {
                service.saveAccount(AccountRequestDto.builder()
                        .customerReference("reference " + i)
                        .status(Math.random() > 0.5 ? AccountStatus.ACTIVE : AccountStatus.BLOCKED )
                        .currency("MAD")
                        .balance(i * Math.random()*1000)
                        .build());
            }

        };
    }
}
