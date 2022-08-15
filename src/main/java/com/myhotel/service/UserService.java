package com.myhotel.service;

import com.myhotel.pojo.User;

public interface UserService {
    //    查找
    User questsAll(User user);
    //    更新密码
    Integer UpdatePassword(User user);
    //    获取密码
    String GetPassword(Integer id);
}
