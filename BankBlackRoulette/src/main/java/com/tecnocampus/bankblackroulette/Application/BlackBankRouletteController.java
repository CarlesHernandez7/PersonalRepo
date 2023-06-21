package com.tecnocampus.bankblackroulette.Application;

import com.tecnocampus.bankblackroulette.Application.dto.AccountDTO;
import com.tecnocampus.bankblackroulette.Application.dto.UserDTO;
import com.tecnocampus.bankblackroulette.Domain.Account;
import com.tecnocampus.bankblackroulette.Domain.User;
import com.tecnocampus.bankblackroulette.Persistance.AccountRepository;
import com.tecnocampus.bankblackroulette.Persistance.UserRepository;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class BlackBankRouletteController {
    private UserRepository userRepository;
    private AccountRepository accountRepository;

    public BlackBankRouletteController(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    public UserDTO createUser(UserDTO userDTO) throws Exception {
        User user = new User(userDTO);
        userRepository.save(user);
        return new UserDTO(user);
    }

    public void deleteUser(String userId){
        Optional<User> user = userRepository.findById(userId);
        List<Account> accountList = accountRepository.findAllByUser(user.get());
        accountRepository.deleteAll(accountList);
        userRepository.deleteById(userId);
    }

    public AccountDTO addAccount(String userId, AccountDTO accountDTO) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        Account account = new Account(accountDTO, user.get());
        user.get().addAccount(account);
        accountRepository.save(account);
        return new AccountDTO(account);
    }

    public void removeAccount(String accountId){
        accountRepository.deleteById(accountId);
    }

    public void updateUser(String userId){

    }

    public UserDTO getUser(String userId){
        return new UserDTO(userRepository.findById(userId).get());
    }

    public Set<UserDTO> getAllUsers(){
        List<User> userList = userRepository.findAll();
        return userList.stream().map(UserDTO::new).collect(Collectors.toSet());
    }

    public AccountDTO getAccount(String accountId) throws Exception {
        return new AccountDTO(accountRepository.findById(accountId).get());
    }

    public Set<AccountDTO> getAllAccounts(String userId) throws Exception{
        Optional<User> user = userRepository.findById(userId);
        List<Account> accountList = accountRepository.findAllByUser(user.get());
        return accountList.stream().map(AccountDTO::new).collect(Collectors.toSet());
    }

    public void balanceRoulette(String accountId) throws Exception {
        accountRepository.findById(accountId).get().balanceRoulette();
    }

    public void blackSwamp(String accountId) throws Exception {
        Account accountBase = accountRepository.findById(accountId).get();
        User userBase = accountBase.getUser();

        List<User> userList = userRepository.findAll();
        Optional<User> randomUser = userList.stream().skip((int)(userList.size() * Math.random())).findFirst();
        while(userBase.equals(randomUser.get())){
            randomUser = userList.stream().skip((int)(userList.size() * Math.random())).findFirst();
        }

        Optional<Account> randomAccount = randomUser.get().getAccounts().stream().skip((int)(randomUser.get().getAccounts().size() * Math.random())).findFirst();
        accountBase.blackSwamp(randomAccount.get());
    }

}
