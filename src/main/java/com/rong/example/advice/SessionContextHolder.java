package com.rong.example.advice;

import cn.hutool.core.util.StrUtil;
import com.rong.example.bean.bo.SessionContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 线程上下文设置
 */
@Slf4j
public class SessionContextHolder {

    /**
     * 通用线程上下文
     */
    private static final ThreadLocal<SessionContext> threadLocal = new ThreadLocal();

    public SessionContextHolder() {
    }

    public static SessionContext getContext() {
        SessionContext context = (SessionContext) threadLocal.get();
        if(context == null){
            context = new SessionContext();
            threadLocal.set(context);
        }

        //默认设置线程名称
        if(StrUtil.isEmpty(context.getSysThreadName())){
            context.setSysThreadName(Thread.currentThread().getName());
        }
        return  context;
    }

    public static void setContext(SessionContext context) {

        //创建异步线程时：新线程沿用主线程名称，方便日志查询
        if(context != null && StrUtil.isNotEmpty(context.getSysThreadName())){
            Thread.currentThread().setName(context.getSysThreadName());
        }

        threadLocal.set(context);
    }
}
