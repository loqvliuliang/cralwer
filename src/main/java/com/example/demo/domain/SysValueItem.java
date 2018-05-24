package com.example.demo.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@TableName("sys_value_item")
@Data
public class SysValueItem {

    private Long id;

    @TableField("sys_value_id")
    private Long sysValueId;

    private String code;

    private String name;

}
