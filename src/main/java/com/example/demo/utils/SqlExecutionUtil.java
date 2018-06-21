package com.example.demo.utils;

import com.baomidou.mybatisplus.entity.TableFieldInfo;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.util.ObjectUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by liang.liu04@hand-china.com
 * on 2018/6/15
 * 连接数据库，执行sql语句
 */
public class SqlExecutionUtil {


        public static List<T> executeForListWithManyParams(Connection connection,String sql,Class domainClass,List<Object> paramList,List<TableFieldInfo> tableFieldInfoList){
            try {
                //通过连接，获取ps
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                if(!ObjectUtils.isEmpty(paramList)){
                    //将参数一一放入进去
                    for (int i = 0; i < paramList.size(); i++) {
                        preparedStatement.setObject(i + 1, paramList.get(i));
                    }
                }


                //执行查询，得到结果集
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()){
                    Class[] domain = domainClass.getInterfaces();
                    //这里将rs转换为domain


                }

            } catch (SQLException e) {
                e.printStackTrace();

            }

            return null;
        }


}
