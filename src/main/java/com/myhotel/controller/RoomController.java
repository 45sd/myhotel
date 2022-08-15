package com.myhotel.controller;


import com.myhotel.pojo.Room;
import com.myhotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class RoomController {

    @Autowired
    private RoomService roomService;

    //    携带图片添加
    @RequestMapping("/GetFileImg")
    private String GetFileImg(Room room, MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        room.setFiles(bytes);
        room.setConditions(1);
        Integer row = roomService.AddRoom(room);
        return row > 0 ? "添加成功" : "添加失败";

    }

    //    不携带图片添加
    @RequestMapping("/AddRoom")
    private String AddRoom(Room room) throws IOException {
        room.setFiles(null);
        room.setConditions(1);
        Integer row = roomService.AddRoom(room);
        return row > 0 ? "添加成功" : "添加失败";
    }

    //    房间号查找
    @RequestMapping("/FindRoomNumber")
    public Room FindRoomNumber(@RequestParam("number") Integer number) {
        return roomService.FindRoomNumber(number);
    }


    @RequestMapping("/getImgUrl")
    public byte[] getByName(@RequestParam("number") Integer number) {
        Room room = roomService.FindRoomNumber(number);
        byte[] bytes = room.getFiles();
        return bytes;
    }

    //    有图片
    @RequestMapping("/UpdateRoom")
    public Integer UpdateRoom(Room room, MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        room.setFiles(bytes);
        room.setConditions(1);
        Integer row = roomService.updateRoom(room);
        return row;
    }

    //    没有图片
    @RequestMapping("/UpdateImg")
    public Integer UpdateImg(Room room) {
        room.setFiles(null);
        room.setConditions(1);
        Integer row = roomService.updateRoom(room);
        return row;
    }

    //    获取所有房间
    @RequestMapping("/AllRoom")
    public List<Room> AllRoom(Room room) {
//        对数据进行排序返回
        List<Room> roomList = roomService.AllRoom(room);
        Collections.sort(roomList);
        List<Room> roomList1 = new ArrayList<Room>();
        for (Room room1: roomList) {
            roomList1.add(room1);
        }
        return roomList1;
    }

    //    获取房间号 -- 待用
    @RequestMapping("/GetRoomNumber")
    public Integer GetRoomNumber(Integer number) {
        System.out.println(number);
        try {
            Integer integer = roomService.GetRoomNumber(number);
            return integer;
        } catch (Exception exception) {
            return 1000;
        }

    }

//    更改房间状态
    @RequestMapping("/UpdateConditions")
    public Integer UpdateConditions(@RequestParam("roomNumber") Integer roomNumber, @RequestParam("conditions") Integer conditions) {
        Room room = new Room();
        room.setConditions(conditions)
                .setRoomNumber(roomNumber);
        return roomService.UpdateConditions(room);
    }

//    获取房间 不同状态下的数量
    @RequestMapping("/GetConditionsList")
    public Map<String, Object> GetConditionsList() {
        int zero = roomService.GetConditionsList(1).size();
        int one = roomService.GetConditionsList(2).size();
        int two = roomService.GetConditionsList(3).size();
        //创建Map对象 Object是所有类型的父类
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("0",zero);
        map.put("1",one);
        map.put("2",two);
        return map;
    }
}
