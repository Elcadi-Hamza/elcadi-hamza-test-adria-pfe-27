package com.hamza.accountservice.web.rest;

import com.hamza.accountservice.dto.AccountRequestDTO;
import com.hamza.accountservice.dto.AccountResponseDTO;
import com.hamza.accountservice.exception.AccountNotFound;
import com.hamza.accountservice.service.AccountSerivce;
import lombok.AllArgsConstructor;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.internalServerError;

@RestController
@AllArgsConstructor
public class AccountRestController {
    private AccountSerivce accountSerivce;

    @GetMapping("/accounts")
    public List<AccountResponseDTO> listAccounts() {
        return accountSerivce.listAccount();
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable UUID id){
        try {
            AccountResponseDTO customerById = accountSerivce.getAccountById(id);
            return ResponseEntity.ok(customerById);
        } catch (AccountNotFound e) {
            return internalServerError().body(new ErrorMessage(e.getMessage()));
        }
    }
    @PostMapping("/accounts")
    public ResponseEntity<?> saveNewAccount(@RequestBody AccountRequestDTO request) {
        AccountResponseDTO accountResponseDTO = accountSerivce.saveAccount(request);
        return ResponseEntity.ok(accountResponseDTO);
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity<?> updateAccount(@RequestBody AccountRequestDTO request, @PathVariable UUID id){
        try {
            AccountResponseDTO accountResponseDTO = accountSerivce.updateAccount(request);
            return ResponseEntity.ok(accountResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ErrorMessage(e.getMessage()));
        }
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable UUID id){
        try {
            accountSerivce.deleteAccount(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ErrorMessage(e.getMessage()));
        }
    }
}
