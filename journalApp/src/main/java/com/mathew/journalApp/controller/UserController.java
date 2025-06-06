package com.mathew.journalApp.controller;

import com.mathew.journalApp.entity.JournalEntry;
import com.mathew.journalApp.entity.User;
import com.mathew.journalApp.service.JournalEntryService;
import com.mathew.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUser()
    {
        return userService.getall();
    }
    @PostMapping
    public void createUser(@RequestBody User user)
    {
        userService.saveEntry(user);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user)
    {
        User userInDb=userService.findByUserName(user.getUserName());
        if(userInDb != null)
        {
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
