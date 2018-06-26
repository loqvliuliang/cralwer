//package com.example.demo.elasticsearch.service;
//
//import com.baomidou.mybatisplus.mapper.EntityWrapper;
//import com.example.demo.domain.User;
//import com.example.demo.elasticsearch.dao.EsUserDao;
//import com.example.demo.elasticsearch.domain.EsUser;
//import com.example.demo.service.UserService;
//import org.elasticsearch.index.query.QueryStringQueryBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
///**
// * Created by liang.liu04@hand-china.com
// * on 2018/6/22
// */
//@Service
//public class EsUserServiceImpl {
//
//    @Autowired
//    private EsUserDao esUserDao;
//
//    @Autowired
//    private UserService userService;
//
//
//    //插入es
//    public Boolean insertUser(EsUser esUser){
//
//        boolean falg=false;
//        try{
//            esUserDao.save(esUser);
//            falg=true;
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return falg;
//    }
//
//
//    //查询es
//    public List<EsUser> searchUser(String searchContent) {
//        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(searchContent);
//        System.out.println("查询的语句:"+builder);
//        Iterable<EsUser> searchResult = esUserDao.search(builder);
//        Iterator<EsUser> iterator = searchResult.iterator();
//        List<EsUser> list=new ArrayList<EsUser>();
//        while (iterator.hasNext()) {
//            list.add(iterator.next());
//        }
//        return list;
//    }
//
//
//    //初始化user
//    public Boolean initUserToEs(){
//        List<User> users = userService.selectList(
//                new EntityWrapper<User>()
//        );
//        for(User user: users ){
//            EsUser esUser = userToEsUser(user);
//            insertUser(esUser);
//        }
//        return true;
//    }
//
//
//    public EsUser userToEsUser(User user){
//        EsUser esUser = EsUser.builder()
//                .id(user.getId())
//                .mail(user.getMail())
//                .password(user.getPassword())
//                .phone(user.getPhone())
//                .userName(user.getUsername())
//                .build();
//
//        return esUser;
//    }
//
//
//
//}
