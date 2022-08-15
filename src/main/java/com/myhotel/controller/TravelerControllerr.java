package com.myhotel.controller;

import com.myhotel.pojo.Room;
import com.myhotel.pojo.Traveler;
import com.myhotel.pojo.vo.SubTools;
import com.myhotel.service.RoomService;
import com.myhotel.service.TravelerService;
import com.myhotel.util.Deduplication;
import com.myhotel.util.TimeConversion;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TravelerControllerr {
    @Autowired
    private TravelerService travelerService;
    @Autowired
    private RoomService roomService;

    //    添加旅客
    @RequestMapping("/addlist")
    private String insert(Traveler traveler) throws ParseException {
        boolean isFalse = false;
        boolean sexFalse;
        long roomNumber1 = traveler.getRoomNumber();
        String housingEndtime;
        if (traveler.getHousingEndtime().equals(traveler.getHousingNowtime())){
            return "入住时间不能与结束时间一样，请确认后再提交！";
        }
        TimeConversion timeConversion = new TimeConversion();
        if (traveler.getHousingEndtime().isEmpty()){
            housingEndtime = timeConversion.setTime(traveler.getHousingNowtime(), "yyyy-MM-dd 12:00:00");
        }else {
            housingEndtime = traveler.getHousingEndtime();
        }
        try{
            //        判断房间号是否存在
            Room room = roomService.FindRoomNumber((int)roomNumber1);
            int roomNumber = (int) room.getRoomNumber();
            long roomType = roomService.GetRoomByNumber(roomNumber).getRoomType();
            //            入住性别比较
            if (room.getSex().equals("男女混住")){
                sexFalse = true;
            }else {

                if(room.getSex().equals(traveler.getSex())){
                    sexFalse = true;
                }else {
                    return "房间居住性别:"+room.getSex()+",不符合该旅客条件，请确认后再添加！";
                }
            }

            List<SubTools> subToolsList = travelerService.GetSexByNumber((int) traveler.getRoomNumber());
            Deduplication deduplication = new Deduplication();
            List<SubTools> list = deduplication.getNewList(subToolsList);
            for (SubTools subTools:list){
                if (subTools.getRoomNumber()== traveler.getRoomNumber()){
                    int number = (int) (roomType-subTools.getSex().length());
                    if (number>0){
                        isFalse = true;
                    }else {
                        isFalse = false;

                    }
                }
            }
            if (isFalse && sexFalse){
                traveler.setHousingEndtime(housingEndtime);

                traveler.setId(1);
                traveler.setCheckout("未退");

                try {
                    String username1 = travelerService.AllTraveler(traveler.getUsername()).getUsername();
                    if (username1.equals(traveler.getUsername())) {
                        return "请勿重复操作";
                    } else {
                        Integer row = travelerService.insertPages(traveler);
                        return row > 0 ? "添加成功" : "添加失败";
                    }
                } catch (Exception exception) {

                    Integer row = travelerService.insertPages(traveler);

                    return row > 0 ? "添加成功" : "添加失败";
                }
            }else { return "该房间已满，请更换其他房间！";}

        }catch (Exception exception){
            return "房间不存在，请确认后在添加!";
        }



    }

    //    通过房间号展示 待用
    @RequestMapping("/AllHouse")
    private List<Traveler> AllHouse() {
        return null;
    }

    //    获取旅客列表
    @RequestMapping("/GetAllTraveler")
    private List<Traveler> GetAllTraveler(Traveler traveler) {

        String[] str = {"单人间", "双人间", "三人间", "四人间"};
        List<Traveler> travelerList1 = new ArrayList<Traveler>();
        List<Traveler> travelerList = travelerService.GetAllTraveler(traveler);

        for (Traveler traveler1 : travelerList) {
            Room getRoomByNumber = roomService.GetRoomByNumber((int) traveler1.getRoomNumber());
            List<String> userList = travelerService.GetCommonByNumber((int) traveler1.getRoomNumber());
            String listName = StringUtils.join(userList, ',');

            TimeConversion timeConversion = new TimeConversion();
            int age = timeConversion.countAge(traveler1.getCertificateNumber());


//            traveler1.setHousingNowtime(traveler.getHousingNowtime());
            traveler1.setListName(listName);
            traveler1.setRoomType(str[(int) getRoomByNumber.getRoomType() - 1]);
            traveler1.setMoney(getRoomByNumber.getMoney());
            traveler1.setAge(age);

            travelerList1.add(traveler1);
        }

        return travelerList1;
    }

    //    更改旅客是否 退房状态
    @RequestMapping("/UpdateCheckout")
    private Integer UpdateCheckout(Traveler traveler) {
        return travelerService.UpdateCheckout(traveler);
    }


    //   左上 -- 房间
    @RequestMapping("/GetNullHouse")
    private List<SubTools> GetNullHouse(@RequestParam("choose") int choose) {
        List<SubTools> subToolsList = travelerService.GetSexByNumber(null);
        Deduplication deduplication = new Deduplication();
        List<SubTools> newList = new ArrayList<SubTools>();
        List<SubTools> list = deduplication.getNewList(subToolsList);
        for (SubTools subTools : list) {
            Room room = roomService.FindRoomNumber((int) subTools.getRoomNumber());
            int number = (int) (room.getRoomType() - subTools.getSex().length());

            subTools.setRoomType(room.getRoomType());
            subTools.setRoomSex(room.getSex());
            subTools.setNullNumber(number);

            switch (choose){
//                全部房间
                case 1:
                    newList.add(subTools);
                    break;
                //    空房间
                case 2:
                    if (subTools.getSex().equals("")) {
                        newList.add(subTools);
                    }
                    break;
//                    客清 ----- 待写
                case 3:
                    break;
                //    有空床
                case 4:
                    if (number > 0) {
                        newList.add(subTools);
                    }
                    break;
                //    指定居住性别获取房间
                case 5:
//                    女
                    if (subTools.getRoomSex().equals("女")){
                        newList.add(subTools);
                    }
                    break;
                case 6:
                    //            男
                    if (subTools.getRoomSex().equals("男")){
                        newList.add(subTools);
                    }
                    break;
                case 7:
                    //     男女混住
                    if (subTools.getRoomSex().equals("男女混住")){
                        newList.add(subTools);
                    }
                    break;
                //    显示所有居住的旅客房间
                case 8:
                    if (!subTools.getUsername().equals("\t")){
                        newList.add(subTools);
                    }
                    break;
            }

        }
//   对象实现了Comparable接口  才能   对List接口的集合进行排序
        Collections.sort(newList);
        return newList;
    }

    //右  补录旅客信息展示
    @RequestMapping("/ShowAll")
    private List<Traveler> ShowAll(){
        List<Traveler> travelerList = travelerService.GetAllTraveler(null);
        //        时间排序
        travelerList.sort((t1, t2) -> t2.getHousingNowtime().compareTo(t1.getHousingNowtime()));
        return travelerList;
    }

//    客清 --- 删除所有旅客
    @RequestMapping("/DeleteAll")
    private String DeleteAll(){
        int row = travelerService.DeleteAll();
        if (row>0){
            return "清理成功";
        }
        return "系统繁忙，请稍后再试！";
    }
}
