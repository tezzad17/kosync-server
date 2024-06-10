package com.tezzad.kosync.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tezzad.kosync.entity.UserEntity;
import com.tezzad.kosync.models.UserBean;
import com.tezzad.kosync.models.exceptions.CreateUserException;
import com.tezzad.kosync.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity createUser(UserBean userBean) throws CreateUserException{

        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(userBean.getPassword());
        userEntity.setUsername(userBean.getUsername());

        boolean existsUsername = userRepository.existsUserEntityByUsername(userBean.getUsername());
        System.out.println(existsUsername);

        if (existsUsername) {
            throw new CreateUserException(userBean.getUsername());
        }

        return userRepository.save(userEntity);
    }
}
