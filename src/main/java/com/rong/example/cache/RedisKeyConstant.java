package com.rong.example.cache;

/**
 * 存放所有reids key
 *
 */
public interface RedisKeyConstant {

    /**
     * 模块名
     */
    String MODULE= "EXAMPLE";

    /**
     * 拼接符，下划线
     */
    String UNDERLINE ="_";

    /**
     * key类型：用户
     */
    String CACHE_TYPE_USER ="USER";

    /**
     * 缓存类型：用户    有效期，单位：秒
     */
    int USER_EXPIRE_SECONDS = 10;




}
