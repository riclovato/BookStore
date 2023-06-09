package com.rick.bookStore.services;

import com.rick.bookStore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       var user = repository.findByUserName(username);
       if(user != null) {
                return user;
       } else{
           throw new UsernameNotFoundException("Username " + username + " not found.");
       }
    }
}
