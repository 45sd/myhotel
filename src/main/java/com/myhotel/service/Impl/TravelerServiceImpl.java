package com.myhotel.service.Impl;

import com.myhotel.mapper.RoomMapper;
import com.myhotel.mapper.TravelerMapper;
import com.myhotel.pojo.Traveler;
import com.myhotel.pojo.vo.SubTools;
import com.myhotel.service.TravelerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TravelerServiceImpl implements TravelerService {
    @Autowired
    private TravelerMapper travelerMapper;
    @Autowired
    private RoomMapper roomMapper;

    @Override
    public Integer insertPages(Traveler traveler) {
        return travelerMapper.insertPages(traveler);
    }

    @Override
    public Traveler AllTraveler(String str) {
        return travelerMapper.AllTraveler(str);
    }

    @Override
    public List<Traveler> GetAllTraveler(Traveler traveler) {
        return travelerMapper.GetAllTraveler(traveler);
    }

    @Override
    public List<String> GetCommonByNumber(Integer number) {
        return travelerMapper.GetCommonByNumber(number);
    }

    @Override
    public Integer UpdateCheckout(Traveler traveler) {
        return travelerMapper.UpdateCheckout(traveler);
    }

    @Override
    public List<SubTools> GetSexByNumber(Integer number) {
        List<SubTools> list = new ArrayList<SubTools>();
//        所有房间
        List<Integer> integerList = roomMapper.GetAllRoomNumber();
//        遍历 每一个房间号
        for (Integer integer:integerList){
//            查找每一个房间号的旅客信息 集合
            List<SubTools> subToolsList = travelerMapper.GetSexByNumber(integer);
            for (SubTools subTools:subToolsList){
                subTools.setRoomNumber(integer);
                list.add(subTools);
            }
            SubTools subTools = new SubTools();
            subTools.setUsername("\t");
            subTools.setSex("");
            subTools.setRoomNumber(integer);
            list.add(subTools);
        }
        return list;
    }

    @Override
    public Integer DeleteAll() {
        return travelerMapper.DeleteAll();
    }


}
