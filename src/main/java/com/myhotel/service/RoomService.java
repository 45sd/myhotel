package com.myhotel.service;

import com.myhotel.pojo.Room;

import java.util.List;


public interface RoomService {
    //    房间添加
    Integer AddRoom(Room room);
    //    房间号查找
    Room FindRoomNumber(Integer number);
    //    更新数据
    Integer updateRoom(Room room);
    //    查找房间号
    Integer GetRoomNumber(Integer number);
    //    获取房间
    List<Room> AllRoom(Room room);
    //    更新房间状态
    Integer UpdateConditions(Room room);
    //    根据房间状态 获取不同状态的数量
    List<Room> GetConditionsList(Integer conditions);

    //    根据房间号获取价格房间类型
    Room GetRoomByNumber(Integer number);
    //    获取所有房间号
    List<Integer> GetAllRoomNumber();
}
