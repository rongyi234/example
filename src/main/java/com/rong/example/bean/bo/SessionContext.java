package com.rong.example.bean.bo;

import lombok.Data;

/**
 * 线程上下文
 */
@Data
public class SessionContext {

    /**
     * 客户端版本
     */
    String appVersion;

    /**
     * 线程名称
     */
    String sysThreadName;


}
