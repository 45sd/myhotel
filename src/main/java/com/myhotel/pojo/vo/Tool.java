package com.myhotel.pojo.vo;

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
public class Tool {
//    旅客名称
    private String listName;

//    年龄
    private long age;
//    房间类型
    private String roomType;
//    缴费金额
    private long money;

}
