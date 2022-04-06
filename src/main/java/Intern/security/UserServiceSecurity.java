package Intern.security;

import Intern.model.enitty.UserEntity;
import Intern.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceSecurity implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);
        if(userEntity.isEmpty())
        {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(userEntity.get());
    }
}
