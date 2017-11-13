package com.example.demo.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回的json类
 */
@Data
public class JsonResult implements Serializable{


	private static final long serialVersionUID = 2426429061389769165L;

	public static final String SUCCESS = "0";
	public static final String ERROR="1";

	/**
	 * state
	 *  0--成功
	 *  1--失败
	 */
	private String state;

	/**
	 * 返回json数据
	 */
	private Object data;

	/***
	 * 异常代码
	 */
	private String errorCode;

	/**
	 * 提示信息
	 */
	private String msg;


	public JsonResult(){

	}


	public JsonResult(String state, Object data, String msg,String errorCode) {
		super();
		this.state = state;
		this.data = data;
		this.msg = msg;
		this.errorCode = errorCode;
	}

	/**
	 * 异常时返回json的值
	 */
	public JsonResult(Throwable e){
		//将状态值改为1
		state = ERROR;
		//不返回json对象
		this.data="";
		//返回错误信息
		msg=e.getMessage();
	}

	public JsonResult(String i ,Throwable e){
		//将状态值改为1
		state = i;
		//不返回json对象
		this.data="";
		//返回错误信息
		msg=e.getMessage();
	}


	/**
	 * 正常时返回json对象
	 * @return
	 */

	public JsonResult(Object data){
		//将状态设置为0
		state=SUCCESS;
		//返回json对象
		this.data=data;
		//返回正确时的信息
		msg="";

	}

}
