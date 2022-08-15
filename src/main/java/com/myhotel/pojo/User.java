package com.myhotel.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
public class User {
  private long id;
  private String name;
  private String account;
  private String password;


}
