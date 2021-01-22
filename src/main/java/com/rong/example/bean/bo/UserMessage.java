package com.rong.example.bean.bo;


import lombok.Data;

import java.util.Date;

@Data
public class UserMessage {
    private String id;

    private String info;

    private Integer type;

    private String refId;

    private Integer status;

    private String accountId;

    private Date createTime;

    private String url;

    private Integer messageType;

    private String title;

    private Integer refType;

    private String city;

    private Integer uiType;

    //用户推送类型描述：前端展示
    private String uiTypeDesc;

    private Integer voucherType;

    private String voucherValue;

    private Integer voucherNum;

    private String giftId;

    private Integer giftType;

    private String refInfo;
}
