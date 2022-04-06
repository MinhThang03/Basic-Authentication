package Intern.mapping;


import Intern.model.enitty.UserEntity;
import Intern.model.playload.request.Authentication.RegisterRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.time.LocalDateTime;
import java.time.ZoneId;

public class UserMapping {
    public static UserEntity registerToEntity(RegisterRequest registerRequest) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        return new UserEntity(registerRequest.getUsername(), registerRequest.getPassword());
    }


}
