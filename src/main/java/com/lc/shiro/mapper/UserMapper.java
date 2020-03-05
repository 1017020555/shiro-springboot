package com.lc.shiro.mapper;

import com.lc.shiro.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    public User findByName(@Param("name") String name);
    public User findById(@Param("id") Integer id);
}
