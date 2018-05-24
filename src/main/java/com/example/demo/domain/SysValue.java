package com.example.demo.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName("sys_value")
public class SysValue {

    private Long id;

    private String type;//系统值列表大类类型代码
}
