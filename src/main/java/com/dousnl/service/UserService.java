package com.dousnl.service;

import com.dousnl.domain.User;
import com.dousnl.domain.entity.UserEntity;
import java.util.List;

public interface UserService {

     List<UserEntity> listUserEntity();

     void addUser();

     void updateUser();

     User getUser() throws InterruptedException;
}
