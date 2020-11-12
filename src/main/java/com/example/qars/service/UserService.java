package com.example.qars.service;

import com.example.qars.dao.UserRepository;
import com.example.qars.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class UserService extends BaseService<User> implements UserDetailsService {

    /************************* Service initialisation ************************/
    private final UserRepository repository;
    public UserService(UserRepository repository){
        super(repository);
        this.repository = repository;
    }

    /***************************** Custom Functions ***************************/

    @Override
    @Transactional
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.getAccountByUsername(username);
        if(user != null) return user;
        else throw new UsernameNotFoundException(username);
    }


}









