package com.example.demo.utils;

import java.util.UUID;

/**
 * Created by 刘亮 on 2017/12/18.
 */
public class ShopUtil {
    /**
     * 利用UUID生成主键值
     */
    public static String createId(){
        String id = UUID.randomUUID().toString();
//		return id;
        return id.replace("-", "");
    }
}
