package com.myhotel.pojo;


import com.myhotel.pojo.vo.Tool;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@ToString
//生成全参数的构造器
@AllArgsConstructor
//无参构造方法
@NoArgsConstructor
public class Traveler extends Tool {

  private long id;
  private long roomNumber;
  private String housingNowtime;
  private String housingEndtime;
  private String certificateType;
  private String certificateNumber;
  private String birthday;
  private String sex;
  private String username;
//  籍贯
  private String hometown;
//  民族
  private String nationvalue;
  private String address;
  private String phone;
  private String guest;
  private String healthy;
  private long temperature;
  private String route;
  private String rent;
  private String toll;
  private String cart;
//  是否退房
  private String checkout;


}
