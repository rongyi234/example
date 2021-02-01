package com.rong.example.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * sale服务调用
 **/
@FeignClient(name = "${feign.service.sale.name}",url = "${feign.service.sale.url}")
public interface SaleClient {

    @PostMapping(value = "/mts/voucher/find", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            headers = {"s-verion=v2","s-clt=123"})
    Object voucherFind(@RequestParam Map<String,Object> map);
}
