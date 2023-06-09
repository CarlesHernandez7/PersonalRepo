package com.tecnocampus.bankblackroulette.Application.dto;

import com.tecnocampus.bankblackroulette.Domain.Account;
import com.tecnocampus.bankblackroulette.Domain.User;
import lombok.NoArgsConstructor;



@NoArgsConstructor
public class AccountDTO {
    private String Id;
    private String iban;
    private double balance;
    private int balanceRouletteCount;
    private int blackSwampCount;

    public AccountDTO(Account account){
        this.Id = account.getId();
        this.iban = account.getIban();
        this.balance = account.getBalance();
        this.balanceRouletteCount = account.getBalanceRouletteCount();
        this.blackSwampCount = account.getBlackSwampCount();
    }

    public String getId() {
        return Id;
    }

    public String getIban() throws Exception {
        if(this.iban == null || this.iban.equals("")){
            throw new Exception("the iban is invalid");
        }
        return iban;
    }

    public double getBalance() {
        return balance;
    }

    public int getBalanceRouletteCount() {
        return balanceRouletteCount;
    }

    public int getBlackSwampCount() {
        return blackSwampCount;
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "Id='" + Id + '\'' +
                ", iban='" + iban + '\'' +
                ", balance=" + balance +
                ", balanceRouletteCount=" + balanceRouletteCount +
                ", blackSwampCount=" + blackSwampCount +
                '}';
    }
}
