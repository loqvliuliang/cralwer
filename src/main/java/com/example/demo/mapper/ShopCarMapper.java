package com.example.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.domain.ShopCar;
import org.apache.ibatis.annotations.Param;

/**
 * Created by 刘亮 on 2017/12/18.
 */
public interface ShopCarMapper extends BaseMapper<ShopCar> {

    ShopCar selectShopCarById(@Param("id") Long id);
    Integer deleteShopCarById(@Param("id") String id);
}
