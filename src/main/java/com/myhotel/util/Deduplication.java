package com.myhotel.util;

import com.myhotel.pojo.vo.SubTools;
import com.myhotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Deduplication {

    public List<SubTools> getNewList(List<SubTools> subToolsList){
        HashMap<Integer, SubTools> tempMap = new HashMap<Integer, SubTools>();

        for (SubTools subTools : subToolsList) {

            int temp = (int) subTools.getRoomNumber();//获取房间号
            //containsKey(Object key)该方法判断Map集合中是否包含指定的键名，如果包含返回true，不包含返回false
            //containsValue(Object value)该方法判断Map集合中是否包含指定的键值，如果包含返回true，不包含返回false
            if (tempMap.containsKey(temp)) {

                SubTools p = new SubTools();
                p.setRoomNumber(temp);

                //合并相同 roomNumber 的sex userName值
                p.setUsername(tempMap.get(temp).getUsername()+"\t"+subTools.getUsername());
                p.setSex(tempMap.get(temp).getSex() + subTools.getSex());


                //HashMap不允许key重复，当有key重复时，前面key对应的value值会被覆盖
                tempMap.put(temp, p);
            } else {
                tempMap.put(temp, subTools);
            }
        }

        //去除重复 房间号  list
        List<SubTools> newList = new ArrayList<SubTools>();
        for(Integer temp:tempMap.keySet()){
            newList.add(tempMap.get(temp));
        }

        return newList;
    }
}
