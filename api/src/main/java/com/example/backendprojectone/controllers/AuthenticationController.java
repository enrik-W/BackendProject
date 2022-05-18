package com.example.backendprojectone.controllers;

import com.example.backendprojectone.models.AuthenticationResponse;
import com.example.backendprojectone.models.Customer;
import com.example.backendprojectone.service.CustomerService;
import com.example.backendprojectone.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("authenticate")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @PostMapping()
    public ResponseEntity<?> createAuthenticationToken(@RequestBody Customer customer) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(customer.getName(), customer.getPassword()));
        } catch (BadCredentialsException e) {
                throw new Exception("Incorrect username or password", e);
            }
        final UserDetails userdetails = customerService.loadUserByUsername(customer.getName());
        final String jwt = jwtTokenUtil.generateToken(userdetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
