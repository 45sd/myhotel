package com.myhotel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;


@SpringBootApplication()
//@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class}) 不链接数据库
public class MyhotelApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyhotelApplication.class, args);
    }

}
