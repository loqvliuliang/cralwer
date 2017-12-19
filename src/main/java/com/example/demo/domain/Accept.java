package com.example.demo.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * Created by 刘亮 on 2017/12/19.
 */
@Data
@TableName("accept")
public class Accept {
    private String accept_id;
    private String accept_name;
    private String user_id;
    private String good_id;
    private String address;
    private String phone;
}
