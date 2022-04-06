package Intern.controller;

import Intern.Service.UserService;
import Intern.model.enitty.UserEntity;
import Intern.model.playload.response.ResponeHandler;
import Intern.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("api/account")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<Object> getInfo(HttpServletRequest request) throws Exception {
        try {

            CustomUserDetails user = (CustomUserDetails)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("user", user);
            return ResponeHandler.generateResponse(HttpStatus.OK, data, true);
        } catch (Exception e){
            return ResponeHandler.generateResponse(HttpStatus.MULTI_STATUS, null, false);
        }
    }


    @GetMapping("/logout")
    @ResponseBody
    public String logout() {
        return null;
    }

}
