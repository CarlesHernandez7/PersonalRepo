package com.tecnocampus.bankblackroulette.Api;

import com.tecnocampus.bankblackroulette.Application.BlackBankRouletteController;
import com.tecnocampus.bankblackroulette.Application.dto.UserDTO;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
public class UserRestController {
    private BlackBankRouletteController userRestController;

    public UserRestController (BlackBankRouletteController userRestController){
        this.userRestController = userRestController;
    }

    @PostMapping("/user")
    public UserDTO createUser(@RequestBody UserDTO userDTO) throws Exception{
        return userRestController.createUser(userDTO);
    }

    @DeleteMapping("/user/{userId}")
    public void removeUser(@PathVariable String userId) throws Exception{
        userRestController.deleteUser(userId);
    }

    @PutMapping("/user/{userId}")
    public void updateUser(@PathVariable String userId){
        userRestController.updateUser(userId);
    }

    @GetMapping("/user/{userId}")
    public UserDTO getUser(@PathVariable String userId){
        return userRestController.getUser(userId);
    }

    @GetMapping("/user")
    public Set<UserDTO> getAllUsers(){
        return userRestController.getAllUsers();
    }

}
