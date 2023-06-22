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

    @DeleteMapping("/users/{userId}")
    public void removeUser(@PathVariable String userId) throws Exception{
        userRestController.removeUser(userId);
    }

    @PutMapping("/user")
    public UserDTO updateUser(@RequestBody UserDTO userDTO) throws Exception {
        return userRestController.updateUser(userDTO);
    }

    @GetMapping("/user/{userId}")
    public UserDTO getUser(@PathVariable String userId){
        return userRestController.getUser(userId);
    }

    @GetMapping("/users")
    public Set<UserDTO> getAllUsers(){
        return userRestController.getAllUsers();
    }

}