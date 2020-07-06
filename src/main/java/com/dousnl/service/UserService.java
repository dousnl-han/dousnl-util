package com.dousnl.service;

import com.dousnl.domain.User;
import com.dousnl.domain.entity.UserEntity;
import java.util.List;

public interface UserService {

    public List<UserEntity> listUserEntity();

    public void addUser();

    public User getUser() throws InterruptedException;
}
