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

    public void removeUser(String userId){
        User user = userRepository.findById(userId).get();
        List<Account> accountList = accountRepository.findAllByUser(user);
        user.deleteAccounts();
        accountRepository.deleteAll(accountList);
        userRepository.deleteById(userId);
    }

    public AccountDTO addAccount(String userId, AccountDTO accountDTO) throws Exception {
        User user = userRepository.findById(userId).get();
        Account account = new Account(accountDTO, user);
        user.addAccount(account);
        accountRepository.save(account);
        userRepository.save(user);
        return new AccountDTO(account);
    }

    public void removeAccount(String userId, String accountId){
        User user = userRepository.findById(userId).get();
        Account account = accountRepository.findById(accountId).get();
        user.deleteAccount(account);
        accountRepository.deleteById(accountId);
        userRepository.save(user);
    }

    public UserDTO updateUser(UserDTO userDTO) throws Exception {
        User user = userRepository.findById(userDTO.getId()).get();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        userRepository.save(user);
        return new UserDTO(user);
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
        User user = userRepository.findById(userId).get();
        List<Account> accountList = accountRepository.findAllByUser(user);
        return accountList.stream().map(AccountDTO::new).collect(Collectors.toSet());
    }

    public void balanceRoulette(String accountId) throws Exception {
        Account account = accountRepository.findById(accountId).get();
        account.balanceRoulette();
        accountRepository.save(account);
    }

    public void blackSwamp(String accountId) throws Exception {
        Account accountBase = accountRepository.findById(accountId).get();
        User userBase = accountBase.getUser();

        List<User> userList = userRepository.findAll();
        User randomUser = userList.stream().skip((int)(userList.size() * Math.random())).findFirst().get();
        while(userBase.equals(randomUser)){
            randomUser = userList.stream().skip((int)(userList.size() * Math.random())).findFirst().get();
        }
        Account randomAccount = randomUser.getAccounts().stream().skip((int)(randomUser.getAccounts().size() * Math.random())).findFirst().get();
        accountBase.blackSwamp(randomAccount);

        accountRepository.save(accountBase);
        accountRepository.save(randomAccount);
        userRepository.save(userBase);
        userRepository.save(randomUser);
    }

}
