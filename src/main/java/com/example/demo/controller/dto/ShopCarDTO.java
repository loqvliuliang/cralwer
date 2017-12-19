package com.example.demo.controller.dto;

import lombok.Data;

/**
 * Created by 刘亮 on 2017/12/18.
 */
@Data
public class ShopCarDTO {
    private String good_id;
    private String good_name;
    private String good_price;
    private String good_img;
    private String good_type;
    private Integer good_num;
    private String good_desc;
    private Integer good_com;
    private Long shopcar_id;
}
