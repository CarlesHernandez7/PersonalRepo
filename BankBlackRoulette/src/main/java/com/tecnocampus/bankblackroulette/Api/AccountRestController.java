package com.tecnocampus.bankblackroulette.Api;

import com.tecnocampus.bankblackroulette.Application.BlackBankRouletteController;
import com.tecnocampus.bankblackroulette.Application.dto.AccountDTO;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class AccountRestController {
    private BlackBankRouletteController accountRestController;

    public AccountRestController(BlackBankRouletteController accountRestController) {
        this.accountRestController = accountRestController;
    }

    @PostMapping("/user/{userId}/account")
    public AccountDTO addAccount(@PathVariable String userId, @RequestBody AccountDTO accountDTO) throws Exception{
        return accountRestController.addAccount(userId, accountDTO);
    }

    @DeleteMapping("/user/{userId}/{accountId}")
    public void removeAccount(@PathVariable String userId, @PathVariable String accountId){
        accountRestController.removeAccount(userId, accountId);
    }

    @GetMapping("/account/{accountId}")
    public AccountDTO getAccount(@PathVariable String accountId) throws Exception{
        return accountRestController.getAccount(accountId);
    }

    @GetMapping("/user/{userId}/account")
    public Set<AccountDTO> getAllAccounts (@PathVariable String userId) throws Exception{
        return accountRestController.getAllAccounts(userId);
    }

    @PostMapping("/accounts/{accountId}/balanceroulette")
    public void balanceRoulette(@PathVariable String accountId) throws Exception {
        accountRestController.balanceRoulette(accountId);
    }

    @PostMapping("/accounts/{accountId}/blackswamp")
    public void blackSwamp(@PathVariable String accountId) throws Exception {
        accountRestController.blackSwamp(accountId);
    }
}