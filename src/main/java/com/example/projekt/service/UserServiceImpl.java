package com.example.projekt.service;

import com.example.projekt.model.Demand;
import com.example.projekt.model.Offer;
import com.example.projekt.model.User;
import com.example.projekt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll(); // Vrátí seznam všech uživatelů
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }


    @Override
    public void saveUser(User user) {
        userRepository.save(user); // Uloží nebo aktualizuje uživatele
    }

    @Override
    public void deleteUser(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
        }
    }
}
