package com.arjunshaji.demo.service;

import com.arjunshaji.demo.Entity.Admin;
import com.arjunshaji.demo.repository.AdminRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AdminRepo adminRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("INSIDE LOAD USER BY USERNAME METHOD OF USER DETAILS SERVICE");
        Admin admin = adminRepo.findByUsername(username);
        if (admin != null){
//            throw new UsernameNotFoundException("INVALID USERNAME OR PASSWORD");
            return new User(admin.getUsername(),admin.getPassword(),new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("INVALID USERNAME OR PASSWORD");
        }
    }
}
