package com.tecnocampus.bankblackroulette.Domain;
import com.tecnocampus.bankblackroulette.Application.dto.AccountDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import java.security.InvalidParameterException;
import java.util.UUID;

@NoArgsConstructor
@Entity(name = "accounts")
public class Account {
    @jakarta.persistence.Id
    @GeneratedValue
    @Id
    private String Id = UUID.randomUUID().toString();

    @ManyToOne
    private User user;
    private String iban;
    private double balance = 0;
    private int balanceRouletteCount = 0;
    private int blackSwampCount = 0;

    public Account(AccountDTO accountDTO, User user) throws Exception{
        checkIban(accountDTO.getIban());
        this.iban = accountDTO.getIban();
        this.user = user;
    }

    private void checkIban(String Iban) throws InvalidParameterException{
        if (!Iban.startsWith("ES") || Iban.length() != 20) throw new InvalidParameterException();
        if (!Iban.substring(2).matches("[0-9]+")) throw new InvalidParameterException();
    }

    public String getId() {
        return Id;
    }

    public User getUser() {
        return user;
    }

    public String getIban() {
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

    public void balanceRoulette() throws Exception{
        if (balanceRouletteCount>=3) throw new Exception("Not Balance Roulette left");
        balanceRouletteCount++;
        balance = (int) ((Math.random() * (50000 - (-50000)) + (-50000)));
    }

    public void blackSwamp(Account ramdomAccount) throws Exception{
        if (blackSwampCount>=1) throw new Exception("Not Black Swamp left");
        blackSwampCount++;
        double previousBalance = this.balance;
        this.balance = ramdomAccount.getBalance();
        ramdomAccount.balance = previousBalance;
    }
}
