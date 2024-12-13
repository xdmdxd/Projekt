package com.example.projekt.service;



import com.example.projekt.model.Demand;
import com.example.projekt.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    User getUserById(long id); // Method to find an offer by its ID
    void saveUser(User user); // Method to save or update an offer
    void deleteUser(long id);
    User getUserByUsername(String username);


}

