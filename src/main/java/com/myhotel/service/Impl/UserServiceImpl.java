package com.myhotel.service.Impl;

import com.myhotel.mapper.UserMapper;
import com.myhotel.pojo.User;
import com.myhotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User questsAll(User user) {
        return userMapper.questsAll(user);
    }

    @Override
    public Integer UpdatePassword(User user) {
        return userMapper.UpdatePassword(user);
    }

    @Override
    public String GetPassword(Integer id) {
        return userMapper.GetPassword(id);
    }
}
