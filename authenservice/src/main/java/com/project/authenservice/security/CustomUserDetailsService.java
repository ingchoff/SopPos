package com.project.authenservice.security;

import com.project.authenservice.model.UserModel;
import com.project.authenservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found with username : " + username));
        return UserPrincipal.create(userModel);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        UserModel userModel = userRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("User not found with id : "+ id));
        return UserPrincipal.create(userModel);
    }
}
