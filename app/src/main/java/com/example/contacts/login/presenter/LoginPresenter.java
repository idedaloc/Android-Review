package com.example.contacts.login.presenter;

import com.example.contacts.dtos.UserDTO;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class LoginPresenter {
    public static UserDTO validateLogin(UserDTO userDTO) {
        UserDTO user1 = new UserDTO();
        UserDTO user2 = new UserDTO();
        user1.setUserId(1l);
        user1.setUserName("j");
        user1.setPassword(Hashing.sha256()
                .hashString("j", StandardCharsets.UTF_8)
                .toString());
        user1.setAuthorized(true);
        user2.setUserId(2l);
        user2.setUserName("k");
        user2.setPassword(Hashing.sha256()
                .hashString("k", StandardCharsets.UTF_8)
                .toString());
        user2.setAuthorized(true);

        switch (userDTO.getUserName()) {
            case "j":
                if (userDTO.getPassword().equals(user1.getPassword()))
                    return user1;
                break;
            case "k":
                if (userDTO.getPassword().equals(user2.getPassword()))
                    return user2;
                break;
            default:
                userDTO.setAuthorized(false);
        }
        return userDTO;
    }

}

