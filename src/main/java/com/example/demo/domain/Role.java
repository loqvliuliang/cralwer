package com.example.demo.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * Created by 刘亮 on 2017/9/14.
 */
@TableName("role")
@Data
public class Role {
    private Long id;
    private String role;
    @TableField("user_id")
    private Long userId;
}
