package com.example.demo.utils;

/**
 * Created by 刘亮 on 2017/11/10.
 */
public interface ResponseCode {

    /*00000-50000作保留*/


    String USER_NAME_NOT_FOUND_60001="60001";//新增时用户为空
    String USER_NAME_EXIT_60002="60002";//用户名已经存在
    String USER_MAIL_EXIT_60003="60003";//用户邮箱已经存在
    String USER_PHONE_EXIT_60004="60004";//用户电话已经存在
    String USER_MAIL_WRONGFUL_60005="60005";//邮箱格式不合法
    String USER_MAIL_IS_EMPTY_60006="60006";//待认证邮箱为空
    String MAIL_CODE_ERROR_60007="60007";//邮箱验证码不正确


    /*附件模块70*/
    String ATTACHMENT_TYPE_NOT_NULL = "70001";//附件类型为空
    String BUDGET_JOURNAL_HEAD_UPLOAD_ERR="70002";//商品上传错误
    String BUDGET_JOURNAL_HEAD_FILE_IS_NULL="70003";//商品上传为空


}
