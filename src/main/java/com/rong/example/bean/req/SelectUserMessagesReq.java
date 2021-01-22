package com.rong.example.bean.req;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

/**
 * 查询用户消息请求对象
 */
@Data
public class SelectUserMessagesReq {

    /**
     * 用户id
     */
    @NotNull(message = "userId 不能为空")
    private String userId;
}
