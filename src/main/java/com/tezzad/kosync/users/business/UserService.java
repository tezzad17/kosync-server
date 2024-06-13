package com.tezzad.kosync.users.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tezzad.kosync.repositories.UserRepository;
import com.tezzad.kosync.users.entity.UserEntity;
import com.tezzad.kosync.users.models.UserBean;
import com.tezzad.kosync.users.models.exceptions.AuthUserException;
import com.tezzad.kosync.users.models.exceptions.CreateUserException;

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

    public UserEntity authUser(UserBean userBean) throws AuthUserException {

        boolean existsUsername = userRepository.existsUserEntityByUsername(userBean.getUsername());
        System.out.println(existsUsername);

        if (!existsUsername) {
            throw new AuthUserException(userBean.getUsername());
        }

        List<UserEntity> userEntityList = userRepository.findByUsername(userBean.getUsername());
        UserEntity userEntity = userEntityList.get(0);

        if (userEntity != null && !userEntity.getPassword().equals(userBean.getPassword())) {
            throw new AuthUserException(userBean.getUsername());
        }

        return userEntity;

    }
}
