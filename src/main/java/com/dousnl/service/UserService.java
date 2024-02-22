package com.dousnl.service;

import com.dousnl.domain.User;
import com.dousnl.domain.entity.UserEntity;
import java.util.List;
import java.util.Map;

public interface UserService {

     List<UserEntity> listUserEntity();

     void addUser();

     void updateUser(Map<String, Integer> map);

     User getUser() throws InterruptedException;
}
