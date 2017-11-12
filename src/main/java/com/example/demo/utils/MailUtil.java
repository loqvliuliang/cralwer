package com.example.demo.utils;

import com.example.demo.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by 刘亮 on 2017/11/10.
 */
@Component
public class MailUtil {

    private static JavaMailSender javaMailSender;

    @Autowired
    public MailUtil(JavaMailSender javaMailSender){
        MailUtil.javaMailSender=javaMailSender;
    }

    public static String AuthMail(String mail){
        //校验邮箱格式
        String format = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
        if(!mail.matches(format)){
            throw new BizException(ResponseCode.USER_MAIL_WRONGFUL_60005);
        }
        //生成随机数，发送邮件
        Random random = new Random();
        Integer i = random.nextInt(9999-1000+1)+1000;
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("939513800@qq.com");
        simpleMailMessage.setTo(mail);
        simpleMailMessage.setSubject(MessageUtil.LOGIN_MAIL);
        simpleMailMessage.setText(i.toString());
        javaMailSender.send(simpleMailMessage);
        return i.toString();
    }
}
