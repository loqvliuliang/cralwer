package com.example.demo.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;

@TableName("goods")
public class Good implements Serializable {

	
	private static final long serialVersionUID = 2715502732563651533L;
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;
	private String good_name;
	private String good_price;
	private String good_img;
	private String good_type;
	private Integer good_num;
	private String good_desc;
	private Integer good_com;
	public Good(){
		
	}
	
	
	


	public Good(Long good_id, String good_name, String good_price, String good_img, String good_type,
                Integer good_num, String good_desc, Integer good_com) {
		super();
		this.id = good_id;
		this.good_name = good_name;
		this.good_price = good_price;
		this.good_img = good_img;
		this.good_type = good_type;
		this.good_num = good_num;
		this.good_desc = good_desc;
		this.good_com = good_com;
	}





	public Integer getGood_com() {
		return good_com;
	}





	public void setGood_com(Integer good_com) {
		this.good_com = good_com;
	}





	public String getGood_desc() {
		return good_desc;
	}





	public void setGood_desc(String good_desc) {
		this.good_desc = good_desc;
	}





	public Long getGood_id() {
		return id;
	}
	public void setGood_id(Long good_id) {
		this.id = good_id;
	}
	public String getGood_name() {
		return good_name;
	}
	public void setGood_name(String good_name) {
		this.good_name = good_name;
	}
	public String getGood_price() {
		return good_price;
	}
	public void setGood_price(String good_price) {
		this.good_price = good_price;
	}
	public String getGood_img() {
		return good_img;
	}
	public void setGood_img(String good_img) {
		this.good_img = good_img;
	}
	public String getGood_type() {
		return good_type;
	}
	public void setGood_type(String good_type) {
		this.good_type = good_type;
	}
	public Integer getGood_num() {
		return good_num;
	}
	public void setGood_num(Integer good_num) {
		this.good_num = good_num;
	}


	@Override
	public String toString() {
		return "Good [good_id=" + id + ", good_name=" + good_name + ", good_price=" + good_price + ", good_img="
				+ good_img + ", good_type=" + good_type + ", good_num=" + good_num + ", good_desc=" + good_desc
				+ ", good_com=" + good_com + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Good other = (Good) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
