package Intern.Service;

import Intern.model.enitty.UserEntity;
import java.util.List;


public interface UserService {
    UserEntity saveUser(UserEntity user);
    List<UserEntity> getUsers();
    Boolean existsByUsername(String username);
    UserEntity findByUsername(String username);
//
//    UserEntity deleteUser(String username);
//    UserEntity updateActive(UserEntity user);
}
