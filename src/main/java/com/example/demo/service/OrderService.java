package com.example.demo.service;

import com.cloudhelios.atlantis.service.BaseService;
import com.example.demo.domain.Order;
import com.example.demo.mapper.OrderMapper;
import org.springframework.stereotype.Service;

/**
 * Created by 刘亮 on 2018/1/2.
 */
@Service
public class OrderService extends BaseService<OrderMapper,Order> {
    private final OrderMapper orderMapper;

    public OrderService(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }



}
