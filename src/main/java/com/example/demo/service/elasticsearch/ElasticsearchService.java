//package com.example.demo.service.elasticsearch;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.rabbitmq.client.impl.nio.WriteRequest;
//import lombok.extern.slf4j.Slf4j;
//import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
//import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
//import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequestBuilder;
//import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
//import org.elasticsearch.action.bulk.BulkRequestBuilder;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.xcontent.XContentBuilder;
//import org.elasticsearch.common.xcontent.XContentFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// *
// */
//@Service
//@Slf4j
//public class ElasticsearchService {
//
//    @Autowired
//    TransportClient transportClient;
//
//    private static final int NUMBER_OF_SHARDS = 5;//分片数
//    private static final int NUMBER_OF_REPLICAS = 1;//副本数
//    private static final String REFRESH_INTERVAL = "10s";//刷新间隔
//
//
//
//    @Value("${elasticsearch.enable:}")
//    private boolean enable;
//
//    private static final int MAX_ES_HITS = 10000;
//
//    //保存索引方法
//    public void saveIndex(Collection<?> objectList,String index){
//        if(enable){
////            BulkRequestBuilder bulkRequest = transportClient.prepareBulk().setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE);
//            try{
////                if (isIndexExists(index)) {
////                    log.info("索引对象已经存在，无法创建！");
////                    return;
////                }
//
//                CreateIndexRequestBuilder builder = transportClient.admin().indices().prepareCreate(index);
//                // 直接创建Map结构的setting
//                Map<String, Object> settings = new HashMap<>();
//                settings.put("number_of_shards", NUMBER_OF_SHARDS); // 分片数
//                settings.put("number_of_replicas", NUMBER_OF_REPLICAS); // 副本数
//                settings.put("refresh_interval", REFRESH_INTERVAL); // 刷新间隔
//                builder.setSettings(settings);
//                builder.addMapping(index, getIndexSource());
////                JSONArray elDataList = JSONArray.parseArray(JSONObject.toJSONString(objectList));
////                for (int i = 0; i < elDataList.size(); i++) {
////                    bulkRequest.add(transportClient.prepareIndex(index, ElasticSearchConstants.DEFAULT_INDEX_TYPE, elDataList.getJSONObject(i).getString("id"))
////                            .setSource(elDataList.getJSONObject(i))
////                    );
////                }
//                CreateIndexResponse res = builder.get();
//                log.info(res.isAcknowledged() ? "索引创建成功！" : "索引创建失败！");
//
//            }catch (IOException e){
//                e.printStackTrace();
//                log.error("getIndexSource()方法失败！");
//            }catch (Exception e){
//                e.printStackTrace();
//                log.error("创建索引失败");
//            }
//        }else {
//            log.warn("未启用elasticsearch");
//        }
//
//    }
//
//
//    public XContentBuilder getIndexSource() throws IOException {
//        XContentBuilder source = XContentFactory.jsonBuilder().startObject().startObject("test_type2")
//                .startObject("properties")
//                // code字段
//                .startObject("code").field("type", "text").field("index", true).field("fielddata", true).endObject()
//                // 名称字段
//                .startObject("name").field("type", "keyword").field("store", false).field("index", true).endObject()
//                // 信息字段
//                .startObject("info").field("type", "keyword").field("store", false).field("index", true).endObject()
//                // 主要内容字段
//                .startObject("content").field("type", "text").field("store", true).field("index", true).field("analyzer", "ik_max_word").endObject()
//                .startObject("my_title").field("type", "keyword").field("store", true).field("index", true).endObject()
//                .startObject("you_title").field("type", "keyword").field("store", true).field("index", true).endObject()
//                .startObject("isDelete").field("type", "boolean").field("store", true).field("index", true).endObject()
//                .startObject("age").field("type", "long").field("store", true).field("index", true).endObject()
//
//                .endObject().endObject().endObject();
//        return source;
//    }
//
//
//    /**
//     * 判断索引是否存在
//     * @param indexName
//     * @return
//     */
//    public boolean isIndexExists(String indexName) {
//        IndicesExistsRequestBuilder builder = transportClient.admin().indices().prepareExists(indexName);
//        IndicesExistsResponse res = builder.get();
//        return res.isExists();
//    }
//
//
//
//}
