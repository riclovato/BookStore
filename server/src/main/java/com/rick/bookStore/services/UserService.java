package com.rick.bookStore.services;

import com.rick.bookStore.controllers.BookController;
import com.rick.bookStore.data.vo.v1.BookVO;
import com.rick.bookStore.exceptions.ResourceNotFound;
import com.rick.bookStore.mapper.DozerMapper;
import com.rick.bookStore.model.Book;
import com.rick.bookStore.model.User;
import com.rick.bookStore.repositories.BookRepository;
import com.rick.bookStore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       var user = repository.findByUsername(username);
       if(user != null) {
                return user;
       } else{
           throw new UsernameNotFoundException("Username " + username + " not found.");
       }
    }
}
