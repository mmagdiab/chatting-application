package com.iti.chatting.service.Impl;

import com.iti.chatting.model.UserEntity;
import com.iti.chatting.repository.UserRepository;
import com.iti.chatting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder bcryptPasswordEncoder;



    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bcryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bcryptPasswordEncoder = bcryptPasswordEncoder;
    }

    @Override
    public UserEntity addUser(UserEntity userEntity) {
        if (userEntity == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parameters not provided correctly!");

        userEntity.setPassword(bcryptPasswordEncoder.encode(userEntity.getPassword()));
        // rabbitMqAdmin.createUserQueue(userEntity.getUsername());
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity) {
        if (!userRepository.existsById(userEntity.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not found!");
        }
        userRepository.save(userEntity);
        return userEntity;
    }

    @Override
    public UserEntity findByID(Long userId) {
        if (userRepository.existsById(userId)) {
            return userRepository.findById(userId).get();
        }
        throw new UsernameNotFoundException("User Not Found");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);

        if (userEntity == null)
            throw new UsernameNotFoundException("User " + username + " Not found");


        return new org.springframework.security.core.userdetails.User(userEntity.getUsername(),
                userEntity.getPassword(),
                mapToGrantedAuthorities());
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities() {
        List<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
        return grantedAuthoritiesList;

    }
}
