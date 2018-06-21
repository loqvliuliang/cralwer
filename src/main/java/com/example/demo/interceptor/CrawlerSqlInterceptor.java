package com.example.demo.interceptor;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.entity.TableFieldInfo;
import com.baomidou.mybatisplus.entity.TableInfo;
import com.baomidou.mybatisplus.enums.SqlMethod;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.toolkit.TableInfoHelper;
import com.example.demo.utils.ReflectionUtil;
import com.example.demo.utils.SqlExecutionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;


import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.*;

/**
 * 拦截查询和更新，mybatis底层没有insert和删除，拦截这两个就可以拦截所有增删改查了。
 */
@Slf4j
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class CrawlerSqlInterceptor implements Interceptor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final static EnumMap<SqlCommandType, String[]> supportedOperationMap = new EnumMap<>(SqlCommandType.class);

    //支持的mp的method集合
    static {
        supportedOperationMap.put(SqlCommandType.INSERT, new String[]{SqlMethod.INSERT_ONE.getMethod(), SqlMethod.INSERT_ONE_ALL_COLUMN.getMethod()});
        supportedOperationMap.put(SqlCommandType.UPDATE, new String[]{SqlMethod.UPDATE.getMethod(), SqlMethod.UPDATE_ALL_COLUMN_BY_ID.getMethod(), SqlMethod.UPDATE_BY_ID.getMethod()});
        supportedOperationMap.put(SqlCommandType.DELETE, new String[]{SqlMethod.DELETE.getMethod(), SqlMethod.DELETE_BY_ID.getMethod(), SqlMethod.DELETE_BY_MAP.getMethod()});
        supportedOperationMap.put(SqlCommandType.SELECT, new String[]{SqlMethod.SELECT_BY_ID.getMethod(), SqlMethod.SELECT_LIST.getMethod(), SqlMethod.SELECT_ONE.getMethod(), SqlMethod.SELECT_MAPS.getMethod(), SqlMethod.SELECT_PAGE.getMethod(), SqlMethod.SELECT_MAPS_PAGE.getMethod()});
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        //拿到mp的Statement
        MappedStatement mpStatement = (MappedStatement)invocation.getArgs()[0];
        //拿到参数
        Object param = invocation.getArgs()[1];
        //拿到mp的方法名(例如：XXXX.selectById-->selectById)
        String methodName = mpStatement.getId().substring(mpStatement.getId().lastIndexOf(".") + 1);

        //?
        Class paramClass =  param == null?null:param.getClass();

        //拿到数据库连接
        Executor executor = (Executor)invocation.getTarget();
        Connection connection = executor.getTransaction().getConnection();


        TableInfo tableInfo = new TableInfo();
        Class domainClass = null;
        try{
             //采用mp方式
             domainClass = ReflectionUtil.mapperGetDomain(Class.forName(mpStatement.getId().substring(0, mpStatement.getId().lastIndexOf("."))));
            if (domainClass != null) {
                tableInfo = TableInfoHelper.getTableInfo(domainClass);
            }
        }catch (ClassNotFoundException e){
            log.warn("Mapper: {}, 未使用mybatis-plus的方式注入Mapper,找不到entity类", mpStatement.getId());
        }

        //不采用mp的mapper使用方式情况
        if (domainClass == null) {
            return invocation.proceed();
        }


        //区分增删改查区拦截
        switch (mpStatement.getSqlCommandType()) {
            case INSERT:{

                break;
            }
            case DELETE:{

                break;
            }
            case UPDATE:{
                break;
            }
            case SELECT:{
                if(methodSupported(SqlCommandType.SELECT,methodName)){
                    log.debug("满足MP查询语句");
                    BoundSql boundSql = mpStatement.getSqlSource().getBoundSql(param);
                    String baseSql = boundSql.getSql();
                    List<TableFieldInfo> tableFieldInfoList = tableInfo.getFieldList();
                    //顺序很重要,根据where里面的?对应的column顺序
                    List<Object> parameterList = new ArrayList<>();
                    parameterList.add(param);
//                    parameterList.add(LocaleContextHolder.getLocale().toString());
                    SqlExecutionUtil.executeForListWithManyParams(connection, baseSql, domainClass,parameterList,tableFieldInfoList);
                }
                break;
            }
            default:{
                throw new RuntimeException("crawler:非CRUD操作，请检查！");
            }
        }

        return new Object();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        String dialect = properties.getProperty("dialect");
        logger.info("mybatis intercept dialect:{}", dialect);
    }



    private Boolean methodSupported(SqlCommandType sqlCommandType,String methodName){
        String[] methodArrays = supportedOperationMap.get(sqlCommandType);
        if(methodArrays==null){
            return false;
        }
        //mp是否包含此方法
        return Arrays.asList(methodArrays).contains(methodName);
    }

}
