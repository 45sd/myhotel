package com.myhotel.service.Impl;

import com.myhotel.mapper.RoomMapper;
import com.myhotel.pojo.Room;
import com.myhotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomMapper roomMapper;

    @Override
    public Integer AddRoom(Room room) {
        return roomMapper.AddRoom(room);
    }

    @Override
    public Room FindRoomNumber(Integer number) {
        return roomMapper.FindRoomNumber(number);
    }

    @Override
    public Integer updateRoom(Room room) {
        return roomMapper.updateRoom(room);
    }

    @Override
    public Integer GetRoomNumber(Integer number) {
        return roomMapper.GetRoomNumber(number);
    }

    @Override
    public List<Room> AllRoom(Room room) {
        return roomMapper.AllRoom(room);
    }

    @Override
    public Integer UpdateConditions(Room room) {
        return roomMapper.UpdateConditions(room);
    }

    @Override
    public List<Room> GetConditionsList(Integer conditions) {
        return roomMapper.GetConditionsList(conditions);
    }

    @Override
    public Room GetRoomByNumber(Integer number) {
        return roomMapper.GetRoomByNumber(number);
    }

    @Override
    public List<Integer> GetAllRoomNumber() {
        return roomMapper.GetAllRoomNumber();
    }


}
