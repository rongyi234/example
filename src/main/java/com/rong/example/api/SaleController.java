package com.rong.example.api;

import com.rong.example.bean.bo.UserMessage;
import com.rong.example.bean.req.SelectUserMessagesReq;
import com.rong.example.constant.ExampleConstants;
import com.rong.example.feign.SaleClient;
import com.rong.example.service.UserMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * sale
 */
@Slf4j
@RestController
@RequestMapping("/sale")
public class SaleController {
    @Autowired
    private SaleClient saleClient;

    @Autowired
    private HttpServletResponse response;


    @RequestMapping(value = "/voucher/find", method = RequestMethod.POST)
    public Object voucherFind(@RequestParam String accountId) throws Exception {

        Map<String,Object> map=new HashMap<>();
        map.put("accountId",accountId);
        Object saleRsp=saleClient.voucherFind(map);

        //设置消息头标志位，通知切面，不需要封装http响应
        response.addHeader(ExampleConstants.HTTP_FLAG_ORIGIN,"1");

        return saleRsp;
    }
}
