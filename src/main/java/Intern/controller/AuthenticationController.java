package Intern.controller;

import Intern.Service.UserService;
import Intern.mapping.UserMapping;
import Intern.model.enitty.UserEntity;
import Intern.model.playload.request.Authentication.LoginRequest;
import Intern.model.playload.request.Authentication.RegisterRequest;
import Intern.model.playload.response.ResponeHandler;
import Intern.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@ComponentScan
@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;


    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<Object> addUser(@RequestBody @Valid RegisterRequest user, BindingResult errors) throws Exception {
        try {

            UserEntity newUser = UserMapping.registerToEntity(user);
            userService.saveUser(newUser);
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("user", newUser);
            return ResponeHandler.generateResponse(HttpStatus.OK, data, true);

        } catch (Exception ex) {
            return ResponeHandler.generateResponse(HttpStatus.MULTI_STATUS, null, false);
        }
    }


    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<Object> login(@RequestBody @Valid LoginRequest request, BindingResult errors) throws Exception {
        try {


            UserEntity loginUser = userService.findByUsername(request.getUsername());

            if (!(passwordEncoder.matches(request.getPassword(), loginUser.getPassword()))) {
                return ResponeHandler.generateResponse(HttpStatus.BAD_REQUEST, null, false);
            }

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();


            Map<String, Object> data = new HashMap<String, Object>();
            data.put("user", loginUser);
            return ResponeHandler.generateResponse(HttpStatus.OK, data, true);

        } catch (Exception ex) {
            return ResponeHandler.generateResponse(HttpStatus.MULTI_STATUS, null, false);
        }
    }



}
