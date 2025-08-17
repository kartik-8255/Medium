package com.engineeringDigest.journal.controller;


import com.engineeringDigest.journal.entity.JournalEntry;
import com.engineeringDigest.journal.entity.User;
import com.engineeringDigest.journal.service.JournalEntryService;
import com.engineeringDigest.journal.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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
    public ResponseEntity<?> createNewUser(@RequestBody User new_user){
       try {

           userService.saveEntry(new_user);
           return new ResponseEntity<>("Created new user\n"+new_user.getUserName(),HttpStatus.CREATED);
       }catch (Exception e){
           log.error(String.valueOf(e));

           return new ResponseEntity<>("Failed to create User", HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable String userName){
        User userInDb = userService.findByUserName(userName);

        if (userInDb!=null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }




}
