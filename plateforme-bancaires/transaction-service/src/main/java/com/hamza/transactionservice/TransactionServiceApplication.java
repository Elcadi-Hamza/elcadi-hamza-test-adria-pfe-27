package com.hamza.transactionservice;

import com.hamza.transactionservice.dtos.TransactionRequestDto;
import com.hamza.transactionservice.feign.AccountRestClient;
import com.hamza.transactionservice.model.Account;
import com.hamza.transactionservice.model.AccountStatus;
import com.hamza.transactionservice.service.TransactionServiceImp;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class TransactionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionServiceApplication.class, args);
    }
//    @Bean
//    List<Account> start (AccountRestClient accountRestClient) {
//        List<Account> accounts = accountRestClient.listAccounts().stream().toList();
//        System.out.println("testing ");
//        accounts.forEach(System.out::println);
//        return  accounts;
//    }
    @Bean
    CommandLineRunner start (TransactionServiceImp service, AccountRestClient accountRestClient) {
        return (args -> {
            List<Account> activeAccounts = accountRestClient.listAccounts().stream()
                    .filter(a -> a.getStatus() == AccountStatus.ACTIVE)
                    .toList();

            for (int i = 0 ; i < activeAccounts.size() ; i++) {

                service.saveTransaction(TransactionRequestDto.builder()
                                .accountId(activeAccounts.get(i).getId())
                                .amount(BigDecimal.valueOf( Math.random()*1000))
                                .currency("MAD")
                                .merchantCategory("cat" + i)
                                .country("country" + i)
                                .build());
            }
        });
    }
}
