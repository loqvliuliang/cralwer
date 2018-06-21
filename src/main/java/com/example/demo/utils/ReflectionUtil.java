package com.example.demo.utils;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by liang.liu04@hand-china.com
 * on 2018/6/15
 * 反射工具类
 */
@Slf4j
public class ReflectionUtil {

    /**
     * mp mapper获得domain类
     * @param mapperClass
     * @return
     */
    public static Class<?> mapperGetDomain(Class<?> mapperClass){
        if(mapperClass == BaseMapper.class){
            log.warn("Current Class is BaseMapper");
            return null;
        }

        Type[] types = mapperClass.getGenericInterfaces();
        ParameterizedType target = null;
        for (Type type : types) {
            if (type instanceof ParameterizedType && BaseMapper.class.isAssignableFrom(mapperClass)) {
                target = (ParameterizedType) type;
                break;
            }
        }
        return target == null ? null : (Class<?>) target.getActualTypeArguments()[0];

    }

}
