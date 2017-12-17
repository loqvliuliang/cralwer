package com.example.demo.utils;

import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by 刘亮 on 2017/12/17.
 */
public class MyBatisPageUtil {
    /**
     * pageable 转 mybatis plus page 参数
     * @param pageable
     * @return
     */
    public static Page getPage(Pageable pageable){
        return  new com.baomidou.mybatisplus.plugins.Page(pageable.getPageNumber()+1,pageable.getPageSize());
    }
}
