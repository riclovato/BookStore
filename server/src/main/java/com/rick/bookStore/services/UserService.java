package com.rick.bookStore.services;

import com.rick.bookStore.data.vo.v1.UserVO;
import com.rick.bookStore.mapper.DozerMapper;
import com.rick.bookStore.model.Book;
import com.rick.bookStore.model.Permission;
import com.rick.bookStore.model.User;
import com.rick.bookStore.repositories.PermissionRepository;
import com.rick.bookStore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository repository;
    @Autowired
    PermissionRepository permissionRepository;

    public List<User> findAll(){
        return repository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       var user = repository.findByUserName(username);
       if(user != null) {
                return user;
       } else{
           throw new UsernameNotFoundException("Username " + username + " not found.");
       }
    }
    public UserVO create(UserVO user){

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        Permission permission = permissionRepository.findById(3L).orElse(null);
        if (permission != null) {
            user.setPermissions(Collections.singletonList(permission));
        }
        var entity = DozerMapper.parseObject(user, User.class);
        entity = repository.save(entity);
       return user;

    }

    public UserVO createManager(UserVO user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        Permission permission = permissionRepository.findById(2L).orElse(null);
        if (permission != null) {
            user.setPermissions(Collections.singletonList(permission));
        }
        var entity = DozerMapper.parseObject(user, User.class);
        entity = repository.save(entity);
        return user;

    }
}
