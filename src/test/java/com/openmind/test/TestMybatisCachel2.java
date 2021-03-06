package com.openmind.test;

import com.openmind.domain.Order;
import com.openmind.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMybatisCachel2 {

    @Autowired
    private OrderService service;

    @Test
    public void test() {
        final long start = System.currentTimeMillis();
        Order order = null;
        for (int i = 0; i < 10000; i++) {
            order = service.get(1l);
        }
        if (order != null) {
            System.out.println(order.getId());
            System.out.println(order.getAmount());
            System.out.println(order.getCreatedTime());
            System.out.println(order.getDesc());
        }

        System.out.println("time elapse: " + (System.currentTimeMillis() - start));
    }

    @Test
    public void update() {
        final Order order = new Order();
        order.setId(1l);
        order.setDesc("what is the fuck");
        service.update(order);
    }
}
