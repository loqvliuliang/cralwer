package com.example.demo.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * 已购订单
 * Created by 刘亮 on 2018/1/2.
 */
@TableName("order_form")
@Data
public class Order {

    private String id;

    @TableField("user_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    @TableField("good_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long goodId;

    //商品数量
    private Integer number;

    //创建时间
    private ZonedDateTime time;

}
