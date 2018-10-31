package com.openmind.service.impl;

import com.openmind.dao.OrderDao;
import com.openmind.domain.Order;
import com.openmind.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderDao dao;

    @Override
    public Order get(Long id) {
        return dao.getOrder(id);
    }

    @Override
    public void update(Order o) {
        if (o == null) {
            return ;
        }

        if(o.getDesc() == null){
            return ;
        }
        dao.update(o);
    }
}
