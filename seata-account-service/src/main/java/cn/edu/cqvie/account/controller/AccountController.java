package cn.edu.cqvie.account.controller;

import cn.edu.cqvie.account.servcie.AccountService;
import cn.edu.cqvie.common.domain.CommonResult;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/account/decrease")
    public CommonResult decrease(@RequestParam("userId") Long userId,
                          @RequestParam("money") BigDecimal money ) {

        accountService.decrease(userId, money);
        return new CommonResult(200, "账户扣减成功");
    }
}
