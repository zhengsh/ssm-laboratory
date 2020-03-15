package cn.edu.cqvie.account.servcie;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface AccountService {

    void decrease(Long userId, BigDecimal money);
}
