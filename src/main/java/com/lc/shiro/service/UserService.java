package com.lc.shiro.service;

import com.lc.shiro.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

public interface UserService {

    public User findByName(String name);
    public User findById(Integer id);
}
