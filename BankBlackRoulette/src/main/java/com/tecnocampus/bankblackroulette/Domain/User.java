package com.tecnocampus.bankblackroulette.Domain;

import com.tecnocampus.bankblackroulette.Application.dto.UserDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Entity(name = "users")
public class User {
    @jakarta.persistence.Id
    @GeneratedValue
    @Id
    private String Id = UUID.randomUUID().toString();

    private String email;
    private String phoneNumber;
    private String userName;
    private String password;
    private Date registerDate;

    @OneToMany
    private List<Account> accounts = new ArrayList<Account>();

    public User(UserDTO userDTO) throws Exception{
        checkPassword(userDTO.getPassword());
        this.email = userDTO.getEmail();
        this.phoneNumber = userDTO.getPhoneNumber();
        this.userName = userDTO.getUserName();
        this.password = userDTO.getPassword();
        this.registerDate = userDTO.getRegisterDate();
    }

    private void checkPassword(String password) throws InvalidParameterException{
        if (!password.matches("[0-9]+") || password.length() < 8) throw new InvalidParameterException();
    }

    public String getId() {
        return Id;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public List<Account> getAccounts(){
        return accounts;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addAccount(Account account) throws Exception {
        if (!this.accounts.add(account)) {
            throw new Exception("The account is already created");
        }
    }

}
