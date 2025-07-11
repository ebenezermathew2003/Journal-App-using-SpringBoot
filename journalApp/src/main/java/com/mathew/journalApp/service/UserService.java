package com.mathew.journalApp.service;

import com.mathew.journalApp.entity.User;
import com.mathew.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
@Autowired
    private UserRepository userRepository;

    public void saveEntry(User user)
    {
        userRepository.save(user);
    }
    public List<User> getall()
    {
        return userRepository.findAll();
    }
    public Optional<User> findById(ObjectId id)
    {
        return userRepository.findById(id);
    }

    public User findByUserName(String userName)
    {
        return userRepository.findByUserName(userName);

    }
}
