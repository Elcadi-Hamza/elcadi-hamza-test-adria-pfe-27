package com.hamza.accountservice.web.rest;

import com.hamza.accountservice.domain.AccountStatus;
import com.hamza.accountservice.dtos.AccountRequestDto;
import com.hamza.accountservice.dtos.AccountResponseDto;
import com.hamza.accountservice.exceptions.AccountNotFound;
import com.hamza.accountservice.service.AccountService;
import lombok.AllArgsConstructor;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.internalServerError;

@RestController
@AllArgsConstructor
public class AccountRestController {
    private AccountService accountService;

    @GetMapping("/accounts")
    public List<AccountResponseDto> listAccounts() {
        return accountService.listAccount();
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable UUID id){
        try {
            AccountResponseDto customerById = accountService.getAccountById(id);
            return ResponseEntity.ok(customerById);
        } catch (AccountNotFound e) {
            return internalServerError().body(new ErrorMessage(e.getMessage()));
        }
    }
    @PostMapping("/accounts")
    public ResponseEntity<?> saveNewAccount(@RequestBody AccountRequestDto request) {
        AccountResponseDto accountResponseDTO = accountService.saveAccount(request);
        return ResponseEntity.ok(accountResponseDTO);
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity<?> updateAccount(@RequestBody AccountRequestDto request, @PathVariable UUID id){
        try {
            AccountResponseDto accountResponseDTO = accountService.updateAccount(request);
            return ResponseEntity.ok(accountResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ErrorMessage(e.getMessage()));
        }
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable UUID id){
        try {
            accountService.deleteAccount(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ErrorMessage(e.getMessage()));
        }
    }

    @GetMapping("/accounts/{id}/status")
    public AccountStatus getAccountStatusById(@PathVariable UUID id) {
        return accountService.getAccountStatus(id);
    }
}
