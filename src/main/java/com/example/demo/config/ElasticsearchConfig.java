//package com.example.demo.config;
//
//import lombok.extern.slf4j.Slf4j;
//
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
//import org.springframework.beans.factory.annotation.Value;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.net.InetAddress;
//
//
//@Configuration
//@Slf4j
//public class ElasticsearchConfig {
//
//    @Value("${elasticsearch.host:}")
//    private String elasticHost;
//    @Value("${elasticsearch.tcp-port:}")
//    private Integer elasticTcpPort;
//    @Value("${elasticsearch.cluster-name:}")
//    private String clusterName;
//    @Value("${elasticsearch.user:}")
//    private String elasticUserName;
//    @Value("${elasticsearch.password:}")
//    private String password;
//    @Value("${elasticsearch.enable:}")
//    private boolean enable;
//
//
//    @Bean
//    public TransportClient transportClient() {
//        if (enable) {
//            try {
//                Settings settings = Settings.builder()
//                        .put("cluster.name", clusterName)
//                        .put("xpack.security.user", elasticUserName.concat(":").concat(password))
//                        .put("client.transport.sniff", true).build();
//                TransportClient client = new PreBuiltXPackTransportClient(settings)
//                        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(elasticHost), elasticTcpPort));
//
//                return client;
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
//        return null;
//    }
//
//
//}
