package cn.edu.cqvie.order.service;

import cn.edu.cqvie.order.domain.Order;
import org.apache.ibatis.annotations.Param;

public interface OrderService {

    void create(Order order);

    void update(Long id, Long userId, Integer status);
}
