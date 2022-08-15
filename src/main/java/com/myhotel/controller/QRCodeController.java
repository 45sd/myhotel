package com.myhotel.controller;

import com.myhotel.service.UserService;
import com.myhotel.util.QrCodeUtils;
import com.wordnik.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
public class QRCodeController {
    @Autowired
    private UserService userService;

    @RequestMapping("/QRCode")
    @ApiOperation(value = "获取二维码")
    public void qrCodeTest(HttpServletResponse response) throws Exception {
        String text = "密码为:\t"+userService.GetPassword(1);
        String logoPath = "C:\\Users\\Zhuxingyong\\Desktop\\宾馆系统\\宾馆页面\\src\\assets\\门面素材.jpg";
        QrCodeUtils.encode(text,logoPath,response.getOutputStream(),true);

    }


}