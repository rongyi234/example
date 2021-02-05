package com.rong.example.constant;

import cn.hutool.core.util.StrUtil;

/**
 * 错误码枚举值
 */
public enum ErrorCodeEnum {
    SYSTEM_ERROR(5000, "系统内部异常"),

    REQUEST_PARAMS_ERROR(5001,"必传参数校验异常"),

    APP_VERSION_ERROR(5002,"app版本过低"),

    RATELIMIT_ERROR(5003,"频控校验失败，超过系统允许阈值");

    /**
     * 根据code获取msg
     */
    public static String getMsgByCode(String code) {
        String msg = StrUtil.EMPTY;
        if (code != null) {
            ErrorCodeEnum[] items = ErrorCodeEnum.values();
            for (ErrorCodeEnum codeEnum : items) {
                if (codeEnum.getCode().toString().equals(code)) {
                    msg = codeEnum.getMessage();
                    break;
                }
            }
        }
        return msg;
    }

    private Integer code;

    private String message;

    private ErrorCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
