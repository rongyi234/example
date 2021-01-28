package com.rong.example.bean.bo;

import cn.hutool.core.util.StrUtil;
import com.rong.example.constant.ErrorCodeEnum;
import com.rong.example.constant.ExampleConstants;
import com.rong.example.filter.PageLimitHolderFilter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

public class CommonHttpResponse implements Serializable {

    private static final long serialVersionUID = -115509026625589704L;
    public static final String RESULT_SUCCESS = "200";
    public static final String RESULT_WARN = "300";
    public static final String RESULT_LOGIC_ERROR = "400";
    public static final String RESULT_EXCEPTION = "401";
    public static final String RESULT_SESSION_ERROR = "402";
    public static final String REQUEST_SIGN_ERROR = "403";
    public static final String REQUEST_SIGN_TIME_ERROR = "404";
    public static final String BIZ_BUSY = "405";
    private String code = "200";
    private String text;
    private Object data;
    private PageLimit pageLimit;

    public PageLimit getPageLimit() {
        return this.pageLimit;
    }

    public void setPageLimit(PageLimit pageLimit) {
        this.pageLimit = pageLimit;
    }

    public CommonHttpResponse() {
    }

    public CommonHttpResponse(String code) {
        this.code = code;
    }

    public CommonHttpResponse(Object data) {
        this.data = data;
    }

    public CommonHttpResponse(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public CommonHttpResponse(String code, String text, Object data) {
        this.code = code;
        this.text = text;
        this.data = data;
    }

    public String getCode() {
        return this.code;
    }

    public Object getData() {
        return this.data;
    }

    public String getText() {
        return this.text;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setText(String text) {
        this.text = text;
    }


    public static CommonHttpResponse deserialize(Object body) {

        CommonHttpResponse result = new CommonHttpResponse();
        result.setData(body);

        HttpServletResponse httpServletResponse = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        String code=httpServletResponse.getHeader(ExampleConstants.HTTP_FLAG_CODE);

        //异常
        if(StrUtil.isNotEmpty(code)){
            result.setCode(code);
            result.setText(ErrorCodeEnum.getMsgByCode(code));
            return result;
        }

        if(PageLimitHolderFilter.getContext().getPageNum() != null){
            result.setPageLimit(PageLimitHolderFilter.getContext());
        }
        return result;

    }
}
