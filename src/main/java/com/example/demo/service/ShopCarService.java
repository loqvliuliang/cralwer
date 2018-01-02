package com.example.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.cloudhelios.atlantis.service.BaseService;
import com.example.demo.controller.dto.ShopCarDTO;
import com.example.demo.domain.Good;
import com.example.demo.domain.ShopCar;
import com.example.demo.mapper.ShopCarMapper;
import com.example.demo.utils.ShopUtil;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by 刘亮 on 2017/12/18.
 */
@Service
public class ShopCarService extends BaseService<ShopCarMapper,ShopCar> {
    private final ShopCarMapper shopCarMapper;
    private final GoodService goodService;

    public ShopCarService(ShopCarMapper shopCarMapper, GoodService goodService) {
        this.shopCarMapper = shopCarMapper;
        this.goodService = goodService;
    }


    public Map<String,Integer> getShopGoodsByUserId(Long userId){
        ShopCarDTO shopCarDTO = new ShopCarDTO();
        Map<String,Integer> map = new HashMap<String,Integer>();
        List<ShopCar> shopCars = shopCarMapper.selectList(new EntityWrapper<ShopCar>().eq("user_id",userId));
        shopCars.forEach(
                shopCar -> {
                    map.put(shopCar.getGood_id(),shopCar.getNumber());
                }
        );
        return map;
    }



    public Boolean addGood(Long userId,String goodId){
        /*现需要通过user和good的id去表里面查*/
        List<ShopCar> list = shopCarMapper.selectList(new EntityWrapper<ShopCar>()
                                    .eq("user_id",userId)
                                    .eq("good_id",goodId)
        );
        if(CollectionUtils.isEmpty(list)){
            ShopCar shopCar = new ShopCar();
            shopCar.setGood_id(goodId);
            shopCar.setNumber(1);
            shopCar.setUser_id(userId.toString());
            shopCar.setShopcar_id(ShopUtil.createId());
            shopCar.setCreateTime(new Date().getTime());
            shopCar.setGoodprice(goodService.selectOne(new EntityWrapper<Good>().eq("id",goodId)).getGood_price());
            shopCarMapper.insert(shopCar);
            return true;
        }else {
            ShopCar shopCar = list.get(0);
            shopCar.setNumber(shopCar.getNumber()+1);
            shopCar.setGoodprice(shopCar.getGoodprice()+goodService.selectOne(new EntityWrapper<Good>().eq("good_id",goodId)).getGood_price());
            shopCarMapper.update(shopCar,new EntityWrapper<ShopCar>().eq("shopcar_id",shopCar.getShopcar_id()));
            return true;
        }
    }


    /*根据id从购物车删除*/
    public Boolean deleteById(String id){
        return shopCarMapper.deleteShopCarById(id)!=0;
    }

    /*根据商品和用户id删除*/
    public Boolean deleteByGoodIdAndUserId(Long goodId,Long userId){
        shopCarMapper.delete(
                new EntityWrapper<ShopCar>()
                    .eq("good_id",goodId)
                    .eq("user_id",userId)
        );
        return true;
    }

}
