package com.example.demo.controller.dto;

import com.example.demo.domain.Blogs;
import lombok.Data;

import java.util.List;

/**
 * Created by 刘亮 on 2018/5/2.
 */
@Data
public class BlogDTO {
    private List<Blogs> blogs;
    private Integer count ;
}
