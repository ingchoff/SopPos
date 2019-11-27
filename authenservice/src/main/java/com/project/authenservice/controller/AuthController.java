package com.project.authenservice.controller;


import com.project.authenservice.exception.AppException;
import com.project.authenservice.model.RoleModel;
import com.project.authenservice.model.RoleName;
import com.project.authenservice.model.UserModel;
import com.project.authenservice.payload.ApiResponse;
import com.project.authenservice.payload.JwtAuthenticationResponse;
import com.project.authenservice.payload.LoginRequest;
import com.project.authenservice.payload.SignUpRequest;
import com.project.authenservice.repository.RoleRepository;
import com.project.authenservice.repository.UserRepository;
import com.project.authenservice.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return  ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())){
            return  new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }
        System.out.println(signUpRequest.getFname()+" "+ signUpRequest.getSname()+" "+ signUpRequest.getUsername()+" "+signUpRequest.getPassword());
        UserModel userModel = new UserModel(signUpRequest.getFname(), signUpRequest.getSname(), signUpRequest.getUsername(),signUpRequest.getPassword());

        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        System.out.println(userModel.getPassword());

        RoleModel userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));
//        RoleModel userRole = new RoleModel(RoleName.ROLE_USER);

        userModel.setRoles(Collections.singleton(userRole));

        UserModel result = userRepository.save(userModel);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}
