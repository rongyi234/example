package com.rong.example.cache;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 
 *  redis工具类（自定义对象类型默认json序列化后存储）
 *  
 *  key命名格式：模块名:: key类型 _ key值
 *
 * @author rongyi
 */
@Component
public class RedisClient {

    private final static Logger log = LoggerFactory.getLogger(RedisClient.class);

    @Autowired
    private  RedisTemplate<String,?> redis;

    @Autowired
    private StringRedisTemplate stringRedis;

    private HashOperations hashOperations;

    /**
     * 拼接key值前缀：模块名::
     */
    String addPrefix(String key) {
        String prefix=RedisKeyConstant.MODULE+"::";
        return prefix+key;
    }


    /**
     * 设置String类型缓存
     */
    public boolean setString(String key, String value) {
        stringRedis.opsForValue().set(addPrefix(key), value);
        return true;
    }

    /**
     * 设置String类型缓存，同时设置失效时间
     */
    public boolean setString(String key, String value, long timeout, TimeUnit unit) {
        stringRedis.opsForValue().set(addPrefix(key), value, timeout, unit);
        return true;
    }
    
    /**
     * 获取String类型缓存
     */
    public String getString(String key) {
        String value = null;
        Object val = stringRedis.opsForValue().get(addPrefix(key));
        if(val!=null){
            value = val.toString();
        }
        return value;
    }
    

    /**
     * 设置自定义类型缓存（json转换）
     */
    public boolean setObject(String key, Object value) {
        return setString(key, JSON.toJSONString(value));
    }

    /**
     * 设置自定义类型缓存（json转换），同时设置失效时间
     */
    public boolean setObject(String key, Object value, long timeout, TimeUnit unit) {
        return setString(key, JSON.toJSONString(value), timeout, unit);
    }

    /**
     * 获取自定义类型缓存（json转换）
     */
    public <T> T getObject(String key, Class<T> cls) {
        T value = null;
        try {
            String str = getString(key);
            if(str!=null) {
                value = JSON.parseObject(str, cls);
            }
        } catch (Exception e) {
            log.error("redis get object value error", e);
        }
        return value;
    }


    /***
     * 并发控制，获取锁： 如果存在返回false，否则返回true
     */
    public  boolean getLock(String lockId, long expire, TimeUnit unit) {
        String key = addPrefix(lockId);
        boolean result = stringRedis.boundValueOps(key).setIfAbsent("1");
        if (result) {
            stringRedis.expire(key, expire, unit);
        }
        return result;
    }

    /**
     * 并发控制，释放锁
     */
    public  void releaseLock(String lockId) {
        delKey(lockId);
    }


    /**
     * 删除
     */
    public void delKey(final String key){
        stringRedis.delete(addPrefix(key));
    }

    /**
     * 判断key是否存在
     */
    public boolean hasKey(String key) {
        return stringRedis.hasKey(addPrefix(key));
    }

    /**
     * 设置过期日期
     */
	public void expireAt(String key, Date toDate) {
        stringRedis.expireAt(addPrefix(key),toDate);
    }
    
    /**
     * 设置有效时间
     */
	public void expire(String key, long timeout, TimeUnit unit){
        stringRedis.expire(addPrefix(key),timeout,unit);
    }

    /***
     * 计数
     */
    public  Long getAtomicLong(String key) {
        return stringRedis.boundValueOps(addPrefix(key)).increment(0L);
    }



    /***
     * 哈希类型操作
     */
    private HashOperations getHashOperations(){
        if(this.hashOperations == null){
            this.hashOperations = redis.opsForHash();
        }
        return this.hashOperations;
    }

    /***
     * 以map集合的形式添加键值对
     */
    public  void hashPutMap(String key, Map<String, Object> map,Long expire,TimeUnit unit) {
        getHashOperations().putAll(addPrefix(key), map);
        if (expire != null) {
            redis.expire(addPrefix(key), (long)expire, unit);
        }
    }

    /***
     * 获取map集合键值对
     */
    public Map<String, Object>  hashGetMap(String key) {
        return this.hashOperations.entries(addPrefix(key));
    }

    /***
     * 新增单个hashkey
     */
    public void  hashPut(String key, String hashkey, Object value) {
         this.hashOperations.put(addPrefix(key),hashkey,value);
    }

    /***
     * 删除一个或者多个hash表字段
     */
    public Object  hashRemove(String key, String... hashkeys) {
        return this.hashOperations.delete(addPrefix(key), hashkeys);
    }

    /***
     * 查看hash表中指定字段是否存在
     */
    public Object  hashExist(String key, String hashkey) {
        return this.hashOperations.hasKey(addPrefix(key), hashkey);
    }


}