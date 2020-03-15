package cn.edu.cqvie.order.service.impl;

import cn.edu.cqvie.order.dao.OrderDao;
import cn.edu.cqvie.order.domain.Order;
import cn.edu.cqvie.order.service.AccountService;
import cn.edu.cqvie.order.service.OrderService;
import cn.edu.cqvie.order.service.StorageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private AccountService accountService;
    @Autowired
    private StorageService storageService;

    @Transactional
    @Override
    public void create(Order order) {
        log.info("====> 开始创建订单: {}", order);
        // 1.新建订单
        orderDao.create(order);

        log.info("====> 订单微服务开始调用【库存扣减 Count】操作");
        // 2.扣减库存
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("====> 订单微服务开始调用【库存扣减】操作END");

        log.info("====> 订单微服务开始调用【账户扣减 Money】操作");
        // 3.扣减账户
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("====> 订单微服务开始调用【账户扣减】END");

        log.info("====> 修改订单状态Status 开始");
        // 4.修改订单状态
        orderDao.update(order.getId(), order.getUserId(), order.getStatus());
        log.info("====> 修改订单状态 END");

        log.info("====> 下订单结束了");
    }

    @Override
    public void update(Long id, Long userId, Integer status) {
        log.info("order.update: id={}, userId={}, status={}", id, userId, status);
        orderDao.update(id, userId, status);
    }
}
