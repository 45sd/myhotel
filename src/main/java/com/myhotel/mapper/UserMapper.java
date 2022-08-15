package com.myhotel.mapper;

import com.myhotel.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
//    查找
    User questsAll(User user);
//    更新密码
    Integer UpdatePassword(User user);
//    获取密码
    String GetPassword(Integer id);
}
