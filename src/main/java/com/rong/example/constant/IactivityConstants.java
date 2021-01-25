package com.rong.example.constant;

/**
 *  常量
 * 
 * @author rongyi
 *
 */
public interface IactivityConstants {

    int SYSTEM_ERROR_CODE = 525000;
    String SYSTEM_ERROR_MSG = "操作失败,系统内部异常";

    int REQUEST_ERROR_CODE = 625001;
    String REQUEST_ERROR_MSG = "接口请求参数错误";

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



}
