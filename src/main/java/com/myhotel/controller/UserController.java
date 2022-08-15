package com.myhotel.controller;

import com.myhotel.pojo.User;
import com.myhotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public boolean login(User user) {
        try {
            User user1 = new User();
            user1.setAccount(user.getAccount());
            //       账号查询 判断是否为空
            if (userService.questsAll(user1).getPassword().equals(user.getPassword())) {
                return true;
            } else {
                return false;
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return false;
    }

//    更新密码
    @RequestMapping("UpdatePassword")
    public Integer UpdatePassword(String password){
        User user = new User();
        user.setPassword(password);
        user.setId(1);
        return userService.UpdatePassword(user);

    }
//    确认密码
    @RequestMapping("VerifyPassword")
    public boolean GetPassword(@RequestParam("password") String password){
        return userService.GetPassword(1).equals(password);
    }
}
