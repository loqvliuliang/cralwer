package com.example.demo.controller.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * Created by 刘亮 on 2017/11/10.
 */
@Data
public class UserDTO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String userName;
    private String password;
    private String mail;

}
