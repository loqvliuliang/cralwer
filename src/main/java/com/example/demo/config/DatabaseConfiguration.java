package com.example.demo.config;

import com.alibaba.druid.pool.DruidDataSource;

import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.example.demo.interceptor.CrawlerSqlInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

/**
 * Created by 刘亮 on 2017/7/27.
 */
@Configuration
@EnableConfigurationProperties({ DataSourceProperties.class, MybatisProperties.class})
public class DatabaseConfiguration {
    private final DataSourceProperties dataSourceProperties;
    private final MybatisProperties properties;
    private final ResourceLoader resourceLoader;

    @Autowired(required = false)
    private Interceptor[] interceptors;

    public DatabaseConfiguration(DataSourceProperties dataSourceProperties, MybatisProperties properties, ResourceLoader resourceLoader) {
        this.dataSourceProperties = dataSourceProperties;
        this.properties = properties;
        this.resourceLoader = resourceLoader;
    }

    @Bean
    public DataSource dataSource(){
        new JdbcTemplate();
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(dataSourceProperties.getUrl());
        dataSource.setUsername(dataSourceProperties.getUsername());
        dataSource.setPassword(dataSourceProperties.getPassword());
        dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        return dataSource;
    }


    @Bean
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean() {
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(dataSource());
        mybatisSqlSessionFactoryBean.setVfs(SpringBootVFS.class);

        if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
            mybatisSqlSessionFactoryBean.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
        }
        if (StringUtils.hasText(this.properties.getConfigLocation())) {
            mybatisSqlSessionFactoryBean.setConfigLocation(this.resourceLoader.getResource(this.properties.getConfigLocation()));
        }
        if (StringUtils.hasLength(this.properties.getTypeHandlersPackage())) {
            mybatisSqlSessionFactoryBean.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
        }else {
            mybatisSqlSessionFactoryBean.setTypeHandlersPackage("com.example.demo.mapper.typehandler");
        }
        Resource[] resources = this.properties.resolveMapperLocations();

        //扫描.xml包
        if(!ObjectUtils.isEmpty(resources)){
            mybatisSqlSessionFactoryBean.setMapperLocations(this.properties.resolveMapperLocations());
        }

        //扫描自定义的拦截器
        if (!ObjectUtils.isEmpty(this.interceptors)) {
            mybatisSqlSessionFactoryBean.setPlugins(this.interceptors);
        }
        return mybatisSqlSessionFactoryBean;
    }

    /**
     * 向spring注入自定义sql拦截器，启动项目的时候，就管理他
     *  注释掉bean:将result转domain卡住了，取消自己的拦截器
     * @return
     */
//    @Bean
    public CrawlerSqlInterceptor crawlerSqlInterceptor(){
        CrawlerSqlInterceptor crawlerSqlInterceptor = new CrawlerSqlInterceptor();
        return crawlerSqlInterceptor;
    }


}
