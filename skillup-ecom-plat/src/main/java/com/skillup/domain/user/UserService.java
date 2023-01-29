package com.skillup.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public UserDomain registerUser(UserDomain userDomain){
        userRepository.createUser(userDomain);


        //TODO: save userDomain to database
        return userDomain;
    }

    public UserDomain getUserById(String id) {
        return userRepository.getUserByUserId(id);
    }

    public UserDomain getUserByName(String name) {
        return userRepository.getUserByUserName(name);
    }

    public UserDomain updateUserDomain(UserDomain userDomain) {
        userRepository.updateUser(userDomain);
        return userDomain;
    }
}
