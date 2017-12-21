package com.example.demo.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

/**
 * Created by 刘亮 on 2017/12/21.
 */
@Data
public class Writes {
    private String write_id;
    @NotNull
    private String user_id;
    @NotNull
    private String good_id;
    private String write_desc;
    private ZonedDateTime createTime;
}
