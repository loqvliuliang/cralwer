package com.example.demo.utils;

import com.example.demo.controller.dto.BlogDTO;
import com.example.demo.domain.Blogs;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by 刘亮 on 2018/5/2.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogResponse {
    private BlogDTO data;
    private String status;
}
