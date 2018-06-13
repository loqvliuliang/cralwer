package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by 刘亮 on 2017/11/12.
 */
@Service
public class RedisService {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Resource(name = "stringRedisTemplate")
    @Autowired
    ValueOperations<String, String> valOpsStr;

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @Resource(name = "redisTemplate")
    ValueOperations<Object, Object> valOpsObj;

    public String getStr(String key) {
        return stringRedisTemplate.opsForValue().get(key);//获取对应key的value
//        return valOpsStr.get(key);
    }

    public void setStr(String key, String val) {
        stringRedisTemplate.opsForValue().set(key,val,1800, TimeUnit.SECONDS);
//        valOpsStr.set(key, val);
    }

    public void del(String key) {
        stringRedisTemplate.delete(key);
    }


    /**
     * 根据指定o获取Object
     *
     * @param o
     * @return
     */
    public Object getObj(Object o) {
        return valOpsObj.get(o);
    }

    /**
     *       * 设置obj缓存
     *       * @param o1
     *       * @param o2
     *
     */
    public void setObj(Object o1, Object o2) {
        valOpsObj.set(o1, o2);
    }

    /**
     * 删除Obj缓存
     *
     * @param o
     */
    public void delObj(Object o) {
        redisTemplate.delete(o);
    }

}
