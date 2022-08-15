package com.myhotel.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;

@Data
@ToString
//生成全参数的构造器
@AllArgsConstructor
//无参构造方法
@NoArgsConstructor
//启用链式编程
@Accessors(chain = true)
public class Room implements Comparable<Room>{

  private long id;
  private long roomNumber;
  private long roomType;
  private String facility;
  private String sex;
  private long money;
  private String briefly;
  private byte[] files;

  private long conditions;

  @Override
  public int compareTo(Room room) {
    return (int) (this.roomNumber-room.roomNumber); //升序
    //return o.id-this.id;  降序
  }

}
