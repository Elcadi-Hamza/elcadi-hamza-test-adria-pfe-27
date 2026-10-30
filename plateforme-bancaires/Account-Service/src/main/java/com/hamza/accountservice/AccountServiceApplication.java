package com.hamza.accountservice;

import com.hamza.accountservice.domain.AccountStatus;
import com.hamza.accountservice.dto.AccountRequestDTO;
import com.hamza.accountservice.service.AccountSerivce;
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
    CommandLineRunner start(AccountSerivce accountSerivce){
        return args -> {
            accountSerivce.saveAccount(
                    AccountRequestDTO.builder()
                            .customerReference("Reference1")
                            .status(AccountStatus.ACTIVE)
                            .currency("MAD")
                            .balance(12000)
                            .build()
            );
            accountSerivce.saveAccount(
                    AccountRequestDTO.builder()
                            .customerReference("Reference2")
                            .status(AccountStatus.ACTIVE)
                            .currency("MAD")
                            .balance(13000)
                            .build()
            );
            accountSerivce.saveAccount(
                    AccountRequestDTO.builder()
                            .customerReference("Reference3")
                            .status(AccountStatus.ACTIVE)
                            .currency("MAD")
                            .balance(14000)
                            .build()
            );
        };
     }
}
