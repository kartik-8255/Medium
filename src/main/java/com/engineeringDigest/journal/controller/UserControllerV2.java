package com.engineeringDigest.journal.controller;


import com.engineeringDigest.journal.entity.User;
import com.engineeringDigest.journal.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")

public class UserControllerV2 {

    @Autowired
    private UserService userService;

    @GetMapping
    public List getAllUsers(){
        return userService.getAll();
    }

    @PostMapping
    public boolean createNewUser(@RequestBody User new_user){
        userService.saveEntry(new_user);
        return true;

    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        User userInDb = userService.findByUserName(user.getUserName());

        if (userInDb!=null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }




}
