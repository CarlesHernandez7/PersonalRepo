package com.tecnocampus.bankblackroulette.Application.dto;

import com.tecnocampus.bankblackroulette.Domain.User;
import lombok.NoArgsConstructor;
import java.util.Date;


@NoArgsConstructor
public class UserDTO {
    private String Id;
    private String email;
    private String phoneNumber;
    private String userName;
    private String password;
    private Date registerDate;

    public UserDTO(User user){
        this.Id = user.getId();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.registerDate = user.getRegisterDate();
    }

    public String getId() {
        return Id;
    }

    public String getEmail() throws Exception {
        if(this.email == null || this.email.equals("")){
            throw new Exception("the email is invalid");
        }
        return email;
    }

    public String getPhoneNumber() throws Exception {
        if(this.phoneNumber == null || this.phoneNumber.equals("")){
            throw new Exception("the phone number is invalid");
        }
        return phoneNumber;
    }

    public String getUserName() throws Exception {
        if(this.userName == null || this.userName.equals("")){
            throw new Exception("the username is invalid");
        }
        return userName;
    }

    public String getPassword() throws Exception {
        if(this.password == null || this.password.equals("")){
            throw new Exception("the password is invalid");
        }
        return password;
    }

    public Date getRegisterDate() throws Exception {
        if(this.registerDate == null){
            throw new Exception("the register date is invalid");
        }
        return registerDate;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "Id='" + Id + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", registerDate=" + registerDate +
                '}';
    }
}
