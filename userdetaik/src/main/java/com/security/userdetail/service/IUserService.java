package com.security.userdetail.service;

import com.security.userdetail.entity.User;
import org.springframework.stereotype.Service;


public interface IUserService {
    public Integer saveUser(User user);
}
