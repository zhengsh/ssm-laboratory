package cn.edu.cqvie.payment.dao;

import cn.edu.cqvie.payment.domain.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

    Integer create(Payment payment);

    Payment findById(@Param("id") Long id);
}
