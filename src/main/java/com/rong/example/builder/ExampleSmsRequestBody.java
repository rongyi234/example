package com.rong.example.builder;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * builder模式使用样例
 *
 */
public class ExampleSmsRequestBody{

    private String format;

    private String timestamp;

    /**
     * @param builder 为参数，builder就是一个属性字段一模一样的类，就是set方法返回this，从而支持链式，原类格式不变
     *
     *  调用:
     * ExampleSmsRequestBody request = ExampleSmsRequestBody.builder()
     *                 .setFormat(Constants.JSON_HTTP_TYPE)
     *                 .setTimestamp(new Date() )
     *                 .build();
     *
     */
    private ExampleSmsRequestBody(Builder builder) {
        this.format = builder.format;
        this.timestamp = builder.timestamp;
    }

    public ExampleSmsRequestBody(){}

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * 一样属性字段的类
     */
    public static final class Builder {
        private String format;
        private String timestamp;

        private Builder() {
        }

        public Builder setFormat(String format) {
            this.format = format;
            return this;
        }

        public Builder setTimestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ExampleSmsRequestBody build() {
            return new ExampleSmsRequestBody(this);
        }
    }


}
