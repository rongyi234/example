package com.rong.example.advice;

import com.rong.example.bean.bo.SessionContext;

/**
 *
 */
public class SessionContextHolder {

    /**
     * 通用线程上下文
     */
    private static final ThreadLocal<SessionContext> threadLocal = new ThreadLocal();

    public SessionContextHolder() {
    }

    public static SessionContext getContext() {
        return (SessionContext) threadLocal.get();
    }

    public static void setContext(SessionContext context) {
        threadLocal.set(context);
    }
}
