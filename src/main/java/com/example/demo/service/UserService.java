package com.example.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.example.demo.controller.dto.UserDTO;
import com.example.demo.domain.User;
import com.example.demo.exception.BizException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.utils.MailUtil;
import com.example.demo.utils.MessageUtil;
import com.example.demo.utils.ResponseCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by 刘亮 on 2017/9/13.
 */
@Service
public class UserService extends ServiceImpl<UserMapper,User> {
    private final  UserMapper userMapper;
    private RedisCache redisCache;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public boolean login(User user){
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("user_name",user.getUsername());
        String u = user.getUsername();
        wrapper.eq("password",user.getPassword());
        if(userMapper.selectList(wrapper).size()!=0){
            return true;
        }
        return false;
    }



    //新建或修改用户
    public User insertOrUpdateUser(User user){
        if(user.getId()==null){//新增
            checkUser(user);
//            if(StringUtils.isNotEmpty(user.getMail())){
//                MailUtil.AuthMail(user.getMail());
//            }
            userMapper.insert(user);
            return userMapper.selectById(user);

        }

        return null;
    }


    //发送邮件验证码
    public String AuthMail(String mail){
        if(StringUtils.isNotEmpty(mail)){
            String authMail= MailUtil.AuthMail(mail);
            //将验证码存入Reids缓存
            redisCache.put(mail,authMail);
            return authMail;
        }else {
            throw new BizException(ResponseCode.USER_MAIL_IS_EMPTY_60006);
        }
    }

    //验证邮件验证码
    public Boolean AuthMailCode(String mail,String code){
        if(code.equals(redisCache.get(mail))){
            return true;
        }
        return false;
    }




    //校验方法
    private void checkUser(User user){
        //检验用户名是否为空
        if(StringUtils.isEmpty(user.getUsername())){
            throw new BizException(ResponseCode.USER_NAME_NOT_FOUND_60001);
        }
        if(CollectionUtils.isNotEmpty(userMapper.selectList(new EntityWrapper<User>().eq("user_name",user.getUsername())))){
            throw  new BizException(ResponseCode.USER_NAME_EXIT_60002);
        }
        if(CollectionUtils.isNotEmpty(userMapper.selectList(new EntityWrapper<User>().eq("mail",user.getMail())))){
            throw  new BizException(ResponseCode.USER_MAIL_EXIT_60003);
        }
        if(CollectionUtils.isNotEmpty(userMapper.selectList(new EntityWrapper<User>().eq("phone",user.getPhone())))){
            throw  new BizException(ResponseCode.USER_PHONE_EXIT_60004);
        }
    }




}
