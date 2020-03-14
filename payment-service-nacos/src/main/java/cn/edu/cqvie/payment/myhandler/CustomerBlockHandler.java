package cn.edu.cqvie.payment.myhandler;

import cn.edu.cqvie.common.domain.CommonResult;
import cn.edu.cqvie.payment.domain.Payment;
import com.alibaba.csp.sentinel.slots.block.BlockException;

public class CustomerBlockHandler {

    public static CommonResult customerBlockHandler(BlockException ex) {
        return new CommonResult(4444, "按照用户自定义, global handlerException-------1",
                new Payment(2020L, "serial0003"));
    }


    public static CommonResult customerBlockHandler2(BlockException ex) {
        return new CommonResult(4444, "按照用户自定义, global handlerException----2",
                new Payment(2020L, "serial0003"));
    }
}
