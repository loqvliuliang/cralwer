package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Configuration;



@Configuration
@Slf4j
public class ElasticsearchConfig {

    @Value("${elasticsearch.host:}")
    private String elasticHost;
    @Value("${elasticsearch.tcp-port:}")
    private Integer elasticTcpPort;
    @Value("${elasticsearch.cluster-name:}")
    private String clusterName;
    @Value("${elasticsearch.user:}")
    private String elasticUserName;
    @Value("${elasticsearch.password:}")
    private String password;
    @Value("${elasticsearch.enable:}")
    private boolean enable;


}
