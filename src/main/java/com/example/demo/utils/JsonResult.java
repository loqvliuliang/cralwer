package com.example.demo.utils;

import java.io.Serializable;

/**
 * 返回的json类
 */
public class JsonResult implements Serializable{


	private static final long serialVersionUID = 2426429061389769165L;

	public static final int SUCCESS = 0;
	public static final int ERROR=1;
	
	/**
	 * state
	 *  0--成功
	 *  1--失败
	 */
	private int state;
	
	/**
	 * 返回json数据
	 */
	private Object data;
	
	/**
	 * 提示信息
	 */
	private String msg;
	
	
	public JsonResult(){
		
	}


	public JsonResult(int state, Object data, String msg) {
		super();
		this.state = state;
		this.data = data;
		this.msg = msg;
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
	
	public JsonResult(int i ,Throwable e){
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
	
	
	
	
	
	


	public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
	}


	public Object getData() {
		return data;
	}


	public void setData(Object data) {
		this.data = data;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	@Override
	public String toString() {
		return "JsonResult [state=" + state + ", data=" + data + ", msg=" + msg + "]";
	}
	
	
	
	
}
