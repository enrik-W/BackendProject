package com.example.backendprojectone.security;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BCryptPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt(8));
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPasswordInDatabase) {
        return BCrypt.checkpw(rawPassword.toString(), encodedPasswordInDatabase);
    }
}
