package com.example.Service;


import com.example.DTO.UserDTO;
import com.example.Domain.User;
import com.example.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;

public class UserDetailService implements UserDetailsService {


    private final UserRepository userRepository;

    public UserDetailService(UserRepository userRepository){
        this.userRepository= userRepository;
    }


    @Override
    public User loadUserByUsername(String Username){
        return userRepository.findByUsername(Username)
                .orElseThrow(() -> new IllegalArgumentException(Username));
    }
}
