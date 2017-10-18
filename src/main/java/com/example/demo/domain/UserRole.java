package com.example.demo.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by 刘亮 on 2017/9/14.
 */
@TableName("user_role")
@Data
public class UserRole  {
    private Long id;
    @TableField(value = "user_id")
    @NotNull
    private Long userId;
    @TableField("role_id")
    @NotNull
    private Long roleId;
}
