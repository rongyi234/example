package com.rong.example.staticFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 方法校验服务记录，需要校验的方法，实现该类
 */
public interface MethodCheckService {

    boolean accept(HttpServletRequest request, HttpServletResponse response);

    void execute(HttpServletRequest request, HttpServletResponse response);
}
