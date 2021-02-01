package com.rong.example.http.sync;

import com.rong.example.http.ResponseHandler;
import org.apache.http.HttpException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * 同步通用httpclient
 * <p>
 * date: 2020年4月26日 下午1:24:15
 *
 * @author rongyi
 */
@Component
public class CommonHttpClient {
    private final static Logger logger = LoggerFactory.getLogger(CommonHttpClient.class);

    @Resource(name = "commonHttpclient")
    private CloseableHttpClient httpClient;


    public <T> T httpPost(String url, String data, Class<T> clz) throws Exception {
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-type", "application/json;charset=UTF-8");

        post.setEntity(new StringEntity(data, "UTF-8"));
        logger.info("http request content = {}", data);
        return httpExecute(post, clz);
    }

    public <T> T httpExecute(HttpUriRequest request, Class<T> clz) throws Exception {
        CloseableHttpResponse resp = httpClient.execute(request);
        T t = ResponseHandler.handle(resp, clz);
        return t;
    }

}
