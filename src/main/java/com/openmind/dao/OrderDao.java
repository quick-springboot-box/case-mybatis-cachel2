package com.openmind.dao;

import com.openmind.domain.Order;

public interface OrderDao {

    /**
     * get order by id
     * @param id
     * @return
     */
    Order getOrder(Long id);


    /**
     * update order
     * @param o
     */
    void update(Order o);
}
