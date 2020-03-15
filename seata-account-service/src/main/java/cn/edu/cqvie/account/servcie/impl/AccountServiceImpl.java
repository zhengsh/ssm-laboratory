package cn.edu.cqvie.account.servcie.impl;

import cn.edu.cqvie.account.dao.AccountDao;
import cn.edu.cqvie.account.servcie.AccountService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @SneakyThrows
    @Override
    public void decrease(Long userId, BigDecimal money) {
        //TimeUnit.SECONDS.sleep(5);
        accountDao.decrease(userId, money);
    }
}
