package com.rong.example.propLoad.cache;

import com.rong.example.mapper.UserMessagePoMapper;
import com.rong.example.mapper.pojo.UserMessagePo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户消息缓存服务 （实际使用时常用于配置表）
 */
@Component
@Slf4j
public class UserMessageCache {

    @Autowired
    private UserMessagePoMapper userMessagePoMapper;


    @CacheEvict(value = "EXAMPLE", key = "'USER_PO_'+#po.accountId")
    public void removeCache(UserMessagePo po) {
        log.info("清除用户消息缓存，userId="+ po.getAccountId());
    }


    @Cacheable(value = "EXAMPLE", key = "'USER_PO_'+#po.accountId", unless = "#result == null")
    public List<UserMessagePo> selectMsgInfo(UserMessagePo po){

        return userMessagePoMapper.selectMsgInfo(po,null);
    }

    /**
     * 注: Spring Cache是采用AOP来管理缓存，所有通过this调用的方法多不会触发缓存
     *
     *     带有@Cache* 注解的方法不能被定义在被调用的方法的类里, 比如 UserController 要调用 findUserByName(String name),
     *     且该方法有 Cacheabele注解, 那么该方法就不能被定义在 UserController中;
     *     解决方法就是使用代理，如下
     *
     *     Cache*注解要加在 public 方法上
     */
    public List<UserMessagePo> selectMsgInfoRefer(UserMessagePo po){

        return ((UserMessageCache) AopContext.currentProxy()).selectMsgInfo(po);
    }

}
