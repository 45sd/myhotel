package com.myhotel.service;

import com.myhotel.pojo.Traveler;
import com.myhotel.pojo.vo.SubTools;

import java.util.List;


public interface TravelerService {
//    添加
    Integer insertPages(Traveler traveler);
    //    查找
    Traveler AllTraveler(String str);
    //    获取旅客列表
    List<Traveler> GetAllTraveler(Traveler traveler);
    //    通过房间查找同旅客的名称
    List<String> GetCommonByNumber(Integer number);
    //    更改旅客是否 退房状态
    Integer UpdateCheckout(Traveler traveler);
    //    通过房间号获取所有旅客的性别
    List<SubTools> GetSexByNumber(Integer number);
    //    删除所有旅客
    Integer DeleteAll();
}
