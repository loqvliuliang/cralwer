package com.example.demo.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by 刘亮 on 2017/12/18.
 */
@TableName("shopcar")
@Data
public class ShopCar implements Serializable {
    private static final long serialVersionUID = -2046150139249782855L;
    private String shopcar_id;
    private long createTime;
    private String user_id;
    private String good_id;
    private Integer number;
    private Double goodprice;

}
