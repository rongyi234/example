package com.rong.example.utils;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * 限流工具类 (不同业务场景，可以建立自己专用的限流工具类)
 *
 */
public class RateLimitUtils {

    //每秒只发出80个令牌
    private static RateLimiter rateLimiter = RateLimiter.create(80.0);

    /**
     * 获取令牌
     * 如果该许可可以在无延迟下的情况下立即获取得到的话
     */
    public static boolean tryAcquire(){
        return rateLimiter.tryAcquire();
    }

    /**
     * 获取许可,如果该许可可以在不超过timeout的时间内获取得到的话，
     * 或者如果无法在timeout 过期之前获取得到许可的话，那么立即返回false（无需等待）
     */
    public static boolean tryAcquire(long timeout, TimeUnit unit){
        return rateLimiter.tryAcquire(timeout,unit);
    }


}
