package com.example.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.baomidou.mybatisplus.spring.boot.starter.MybatisPlusProperties;
import liquibase.integration.spring.SpringLiquibase;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

/**
 * Created by 刘亮 on 2017/7/27.
 */
@Configuration
@EnableConfigurationProperties({ DataSourceProperties.class, MybatisPlusProperties.class})
public class DatabaseConfiguration {
    private final DataSourceProperties dataSourceProperties;
    private final MybatisPlusProperties properties;
    private final ResourceLoader resourceLoader;

    public DatabaseConfiguration(DataSourceProperties dataSourceProperties, MybatisPlusProperties properties, ResourceLoader resourceLoader) {
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
        if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
            mybatisSqlSessionFactoryBean.setMapperLocations(this.properties.resolveMapperLocations());
        }
        return mybatisSqlSessionFactoryBean;
    }


}
