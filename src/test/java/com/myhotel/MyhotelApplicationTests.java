package com.myhotel;

import com.myhotel.service.RoomService;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@SpringBootTest
class MyhotelApplicationTests {


    @Autowired
    private RoomService roomService;

    @Test
    void contextLoads() throws ParseException {
        String s1 = "2022-08-04 13:16:10";
        String s2 ="2022-08-05 11:16:00";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //        将字符串转化为日期
        java.util.Date date1 = df.parse(s1);
        java.util.Date date2 = df.parse(s2);
        System.out.println(date1.after(date2));

    }

}
