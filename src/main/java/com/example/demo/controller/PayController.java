package com.example.demo.controller;

import java.io.UnsupportedEncodingException;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.demo.domain.Good;
import com.example.demo.domain.Order;
import com.example.demo.domain.PaySaPi;
import com.example.demo.domain.ShopCar;
import com.example.demo.service.GoodService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ShopCarService;
import com.example.demo.utils.PayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/pays")
public class PayController {

	private static Logger logger =  LoggerFactory.getLogger(PayUtil.class);

	private final OrderService orderService;
	private final GoodService goodService ;
	private final ShopCarService shopCarService;

	public PayController(OrderService orderService, GoodService goodService, ShopCarService shopCarService) {
		this.orderService = orderService;
		this.goodService = goodService;
		this.shopCarService = shopCarService;
	}

	@RequestMapping("/pay")
	@ResponseBody
	public Map<String, Object> pay(HttpServletRequest request, float price, int istype,long uid,Long goodId,int number) throws UnsupportedEncodingException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> remoteMap = new HashMap<String, Object>();
		remoteMap.put("price", price);
		remoteMap.put("istype", istype);
		Order order = new Order();
		order.setGoodId(goodId);
		order.setUserId(uid);
		order.setTime(ZonedDateTime.now());
		order.setNumber(number);
		order.setId(PayUtil.getOrderIdByUUId());
		orderService.insert(order);
		remoteMap.put("orderid", order.getId());
		remoteMap.put("orderuid", uid);
		remoteMap.put("goodsname", goodService.selectOne(new EntityWrapper<Good>().eq("id",goodId)).getGood_name());
		resultMap.put("data", PayUtil.payOrder(remoteMap));
		return resultMap;
	}

	@RequestMapping("/notifyPay")
	public void notifyPay(HttpServletRequest request, HttpServletResponse response, PaySaPi paySaPi) throws UnsupportedEncodingException {
		// 保证密钥一致性
		if (PayUtil.checkPayKey(paySaPi)) {
			//密钥一致，删除当前登陆人的购物车
			paySaPi.getOrderuid();
			shopCarService.delete(new EntityWrapper<ShopCar>().eq("user_id",paySaPi.getOrderuid()));
		} else {
			// TODO 该怎么做就怎么做
		}
	}

	@RequestMapping("/returnPay")
	public ModelAndView returnPay(HttpServletRequest request, HttpServletResponse response, String orderid) {
		boolean isTrue = false;
		ModelAndView view = null;
		// 根据订单号查找相应的记录:根据结果跳转到不同的页面
		if(orderService.selectById(orderid)!=null){
			isTrue = true;
		}
		if (isTrue) {
			logger.debug("啊啊啊啊啊啊啊啊啊啊啊啊啊------这里应该跳转到订单页面啊");
			view = new ModelAndView("/index.html");
		} else {
			logger.debug("啊啊啊啊啊啊啊啊啊啊啊啊啊---这里跳转到支付失败页面啊");
			view = new ModelAndView("/mylogin.html");
		}
		return view;
	}
}
