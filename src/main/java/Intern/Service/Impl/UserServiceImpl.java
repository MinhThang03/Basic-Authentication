package Intern.Service.Impl;

import Intern.Service.UserService;
import Intern.model.enitty.UserEntity;
import Intern.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }



    @Override
    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }


    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserEntity findByUsername(String username) {
        if (userRepository.findByUsername(username).isEmpty()){
            return null;
        }
        return userRepository.findByUsername(username).get();
    }


//    @Override
//    public UserEntity deleteUser(String username) {
//        UserEntity user = findByUsername(username);
//        if (userRepository.deleteUserBy_id(user.getId()))
//        return userRepository.deleteUserBy_id(user.getId()).get();
//    }
//
//    @Override
//    public UserEntity updateActive(UserEntity user) {
//        user.
//        return userRepository.save(user);
//    }
}
