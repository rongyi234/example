package com.rong.example.constant;

/**
 *  常量
 * 
 * @author rongyi
 *
 */
public interface ExampleConstants {

    /***
     * 互动活动类型：grantCoupons：领券活动
     */
    String ACTIVITY_GRANTCOUPONS="grantCoupons";

    /**
     * 互动活动类型：raffle：抽奖活动
     */
    String ACTIVITY_RAFFLE = "raffleActivity";

    /**
     * 活动参与类型: 1:品牌活动
     */
    Short ACTIVITY_FROM_BRAND = 1;

    /**
     * 活动参与类型: 2:门店活动（默认）
     */
    Short ACTIVITY_FROM_SITE = 2;


    /**
     * HTTP响应 错误码
     */
    String HTTP_FLAG_CODE="err-code";

    /**
     * HTTP响应 错误描述
     */
    String HTTP_FLAG_MSG="err-msg";

    /**
     * HTTP响应 错误描述
     */
    String HTTP_FLAG_ORIGIN="keep-origin";

    /**
     * 最低app版本
     */
    String APP_VERSION = "1.5.0";


}
