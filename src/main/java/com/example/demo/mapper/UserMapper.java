package com.example.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by 刘亮 on 2017/9/13.
 */
@Mapper
public interface UserMapper extends BaseMapper<User>{

}
