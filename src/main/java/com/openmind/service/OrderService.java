package com.openmind.service;

import com.openmind.domain.Order;

public interface OrderService {

    /**
     * get order
     * @param id
     * @return
     */
    Order get(Long id);

    /**
     * update order
     * @param o
     */
    void update(Order o);
}
