package cn.edu.cqvie.order.dao;

import cn.edu.cqvie.order.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {

    /**
     * 创建订单
     *
     * @param order
     */
    void create(Order order);

    /**
     * 修改订单状态 0 -> 1
     *
     * @param id  订单ID
     * @param userId 用户ID
     * @param status 状态
     */
    void update(@Param("id") Long id, @Param("userId") Long userId, @Param("status") Integer status);
}
