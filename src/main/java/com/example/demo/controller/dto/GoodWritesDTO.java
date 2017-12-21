package com.example.demo.controller.dto;

import lombok.Data;

import java.time.ZonedDateTime;

/**
 * Created by 刘亮 on 2017/12/21.
 */
@Data
public class GoodWritesDTO {
    private String writesName;
    private String writesDesc;
    private ZonedDateTime writesDate;
}
