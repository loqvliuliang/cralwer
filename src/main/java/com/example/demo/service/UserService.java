package com.example.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.example.demo.controller.dto.UserDTO;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRole;
import com.example.demo.exception.BizException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.mapper.UserRoleMapper;
import com.example.demo.utils.MailUtil;
import com.example.demo.utils.ResponseCode;
import com.example.demo.utils.UserRoleInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 刘亮 on 2017/9/13.
 */
@Service
public class UserService extends ServiceImpl<UserMapper,User> {
    private final  UserMapper userMapper;
    private final UserRoleMapper userRoleMapper;

    @Autowired
    RedisService redisService;



    public UserService(UserMapper userMapper, UserRoleMapper userRoleMapper) {
        this.userMapper = userMapper;
        this.userRoleMapper = userRoleMapper;
    }

    public boolean login(User user){
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("user_name",user.getUsername());
        String u = user.getUsername();
        wrapper.eq("password",user.getPassword());
        if(userMapper.selectList(wrapper).size()!=0){
            return true;
        }
        if(
                userMapper.selectList(
                new EntityWrapper<User>()
                        .eq("mail",user.getMail())
                        .eq("password",user.getPassword())).size()!=0
                ){
            return true;
        }
        return false;
    }



    //新建或修改用户
    public User insertOrUpdateUser(UserDTO userDTO){
        User user = new User();
                BeanUtils.copyProperties(userDTO,user);
        if(user.getId()==null){//新增
            checkUser(user);
             if(!AuthMailCode(user.getMail(),userDTO.getAuthCode())){
                 throw new BizException(ResponseCode.MAIL_CODE_ERROR_60007,new Object[]{userDTO.getAuthCode()});
             }
            //注册成功后，绑定用户角色，默认是用户角色;
            userMapper.insert(user);
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(UserRoleInfo.USER_ROLE);
            userRoleMapper.insert(userRole);
            return userMapper.selectById(user);

        }

        return null;
    }


    //发送邮件验证码
    public String AuthMail(String mail) throws EmailException {
        //首先看邮箱是否已经注册
        List<User> list = userMapper.selectList(new EntityWrapper<User>().eq("mail",mail));
        if(CollectionUtils.isNotEmpty(list)){
            throw new BizException(ResponseCode.USER_MAIL_EXIT_60003);
        }
        if(StringUtils.isNotEmpty(mail)){
            String authMail= MailUtil.AuthMailSSL(mail);
            //将验证码存入Reids缓存
            redisService.setStr(mail,authMail);
            return authMail;
        }else {
            throw new BizException(ResponseCode.USER_MAIL_IS_EMPTY_60006);
        }
    }

    //验证邮件验证码
    public Boolean AuthMailCode(String mail,String code){
        if(code.equals(redisService.getStr(mail))){
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

    /*根据用户名或邮箱获取用户信息 */
    public User getUserByNameOrMail(String name){
        return userMapper.selectList(
                new EntityWrapper<User>()
                        .eq("user_name",name)
                        .or()
                        .eq("mail",name))
                .get(0) ;
    }




}
