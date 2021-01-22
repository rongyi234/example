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

    int EMPTY_ACTIVITY_CODE = 625002;
    String EMPTY_ACTIVITY_MSG = "无对应的互动营销活动";

    int EMPTY_AWARD_RULE_CODE = 625003;
    String EMPTY_AWARD_RULE_MSG = "无对应的奖品规则";

    int ERROR_AWARDS_ACCOUNT_CODE = 625004;
    String ERROR_AWARDS_ACCOUNT_MSG = "修改奖品库存数量失败，小于已发放数量";

    int ACTIVITY_SOLD_OUT_CODE = 625005;
    String ACTIVITY_SOLD_OUT_MSG = "活动已下架";

    int ACTIVITY_NOT_BEGIN_CODE = 625006;
    String ACTIVITY_NOT_BEGIN_MSG = "活动未开始";

    int ACTIVITY_EXPIRE_CODE = 625007;
    String ACTIVITY_EXPIRE_MSG = "活动已过期";

    int ACTIVITY_REPETTION_CODE = 625008;
    String ACTIVITY_REPETTION_MSG = "活动无法重复参与";

    int AWARDS_EXHAUST_CODE = 625009;
    String AWARDS_EXHAUST_MSG = "活动礼品已发放完毕";

    int CUSTOMER_NOT_EXIST_CODE = 625010;
    String CUSTOMER_NOT_EXIST_MSG = "crm中顾客不存在";

    int ACTIVITY_COUNT_LIMIT_COUNT = 625011;
    String ACTIVITY_COUNT_LIMIT_MSG = "参与次数达到上限";

    int EMPTY_COMPONENT_CODE = 625012;
    String EMPTY_COMPONENT_MSG = "组件列表为空";

    int EMPTY_PAGE_CODE = 625013;
    String EMPTY_PAGE_MSG = "页面数据不存在";

    int EMPTY_GRP_CODE = 625014;
    String EMPTY_GRP_MSG = "该活动下无网点组信息,无法预览,请绑定网点组!";

    int EMPTY_APPID_FOR_ACTIVITY_CODE = 625015;
    String EMPTY_APPID_FOR_ACTIVITY_MSG = "无对应的个人中心";

    int EMPTY_ACTIVITY_STYLE_CODE = 625016;
    String EMPTY_ACTIVITY_STYLE_CODE_MSG = "该模板样式为空";

    int EMPTY_ACTIVITY_WX_CODE = 625017;
    String EMPTY_ACTIVITY_WX_CODE_MSG = "调用微信返回二维码失败";

    int ERROR_ACTIVITY_OSS_CODE = 625018;
    String ERROR_ACTIVITY_OSS_MSG = "获取oss服务器参数失败";

    int ACTIVITY_STATE_ABNORMAL_CODE = 625019;
    String ACTIVITY_STATE_ABNORMAL_MSG = "活动状态异常";


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


    /***
     * 奖品类型：1：积分
     */
    short AWARD_TYPE_POINT=1;

    /***
     * 奖品类型：2：券
     */
    short AWARD_TYPE_COUPON=2;

    /**
     * 活动状态枚举值: 0:暂存
     */
    Short ACTIVITY_STATUS_TEMP_STORAGE = 0;

    /***
    * 活动状态枚举值：1：正常
    */
    Short ACTIVITY_STATUS_NORMAL=1;

    /***
     * 活动状态枚举值：2：下架
     */
    Short ACTIVITY_STATUS_SOLD_OUT=2;
    /***
     * 活动状态枚举值：3：过期
     */
    Short ACTIVITY_STATUS_EXPIRE=3;

    /**
     * 活动状态枚举值: 4: 作废
     */
    Short ACTIVITY_STATUS_NULLIFY = 4;

    /**
     * 数量限制类型枚举值  everyday: 每日
     */
    String NUM_LIMIT_TYPE_EVERYDAY = "everyday";

    /**
     * 数量限制类型枚举值  total: 总数
     */
    String NUM_LIMIT_TYPE_TOTAL = "total";

    /***
     * 互动活动发放礼品kafka消息topic
     */
    String AWARDS_GIVEN_KAFKA_TOPIC="CRM-IACTIVITY-AWARDS-GIVEN";

    /***
     * 抽奖活动kafka消息topic
     */
    String ACTIVITY_CONSUMER_KAFKA_TOPIC="CRM-THIRD-MESSAGE";

    /***
     * 互动活动应用模块名称（运维平台使用）
     */
    String IACTIVITY_MODULE_NAME="IACTIVITY";

    /***
     * 互动活动tag 前缀 （用于门户网点打标签）
     */
    String IACTIVITY_PREFIX_TAG="iActivity_";

    /***
     * crm接口通用响应码：成功
     */
    final int OK =20000;

    /***
     * 操作类型：删除
     */
    String OPERATE_DELETE="delete";

    /***
     * 操作类型：过期
     */
    String OPERATE_EXPIRE="expire";

    /**
     * 操作类型：作废
     */
    String OPERATE_NULLIFY="nullify";

    /***
     * 调用crm针对活动奖品操作类型：新增占用
     */
    String AWARDS_OPERATE_OCCUPY="1";

    /***
     * 调用crm针对活动奖品操作类型：修改占用
     */
    String AWARDS_OPERATE_UPDATE_OCCUPY="2";

    /***
     * 调用crm针对活动奖品操作类型：取消占用
     */
    String AWARDS_OPERATE_RELEASE="3";


    /***
     * 消息头 openid
     */
    String HTTP_SESSION_HEADER_TOKEN_OPENID    = "s-openid";

    /***
     * 消息头 appid
     */
    String HTTP_SESSION_HEADER_TOKEN_APPID    = "s-appid";

    /***
     * 消息头  unionid
     */
    String HTTP_SESSION_HEADER_TOKEN_UNIONID   = "s-unionid";

    /**
     * 活动规则奖品（如抽奖）
     */
    short AWARDS_GIVEN_TYPE_NORMAL = 1;

    /**
     * 阳光普照奖品
     */
    short AWARDS_GIVEN_TYPE_SUNSHINE = 2;

    /**
     * 分享奖品
     */
    short AWARDS_GIVEN_TYPE_SHARE = 3;

    /**
     * 消耗积分类型 -- 不消耗积分
     */
    short RAFFLE_RULES_POINTS_NOT_COST = 1;

    /**
     * 消耗积分类型 -- 消耗积分
     */
    short RAFFLE_RULES_POINTS_COST = 2;

    /**
     * 抽奖次数限制 -- 数量限制
     */
    short RAFFLE_LIMIT_TYPE_NUMBERS = 1;

    /**
     * 抽奖次数限制 -- 交易额控制
     */
    short RAFFLE_LIMIT_TYPE_AMOUNT  = 2;

    /**
     * 抽奖次数限制 -- 不限制
     */
    short RAFFLE_LIMIT_TYPE_NOT_LIMIT = 3;

    /**
     * 中奖机会 -- 不限制
     */
    short WIN_LIMIT_TYPE_NO_LIMIT = 1;

    /**
     * 中奖机会 --限制
     */
    short WIN_LIMIT_TYPE_LIMIT = 2;

    /**
     * 导出微信小程序抽奖活动页面URL前缀
     */
    String WX_LOADING_PAGE_URL = "pages/loading/index?scene=";

    /**
     * 小程序默认loading页面URL
     */
    String WX_LOADING_URL = "pages/loading/index";

    /**
     * 是首页
     */
    short PAGE_INFO_IS_INDEX = 1;

    /**
     * 页面操作场景：（2）正式场景：formal
     */
    String PAGE_SCENE_FORMAL = "formal";

    /**
     * 页面操作场景：（1）调试场景：debug；
     */
    String PAGE_SCENE_DEBUG = "debug";

    /**
     * 查询中奖记录 真实记录
     */
    Integer AWARDS_GIVEN_RECORDS_TYPE_TRUE = 1;

    /**
     * 中奖概率最大值
     */
    Integer WIN_PROBABILITY_MAX = 100;

    /**
     * 扣积分接口pnt_type
     */
    String POINTS_DECREASE_PNT_TYPE = "CONSUME";

    /**
     * 扣积分接口type
     */
    String POINTS_DECREASE_DECREASE_TYPE = "decrease";

    /**
     * 加积分接口type
     */
    String POINTS_DECREASE_INCREASE_TYPE = "increase";

    /**
     * 小程序码默认宽度
     */
    Integer XXCQRCODE_SIZE = 430;


    /**
     * 导出参数 activityId
     */
    String EXPORT_CONDITION_ACTIVITYID = "activityId";

    /**
     * 导出参数 appId
     */
    String EXPORT_CONDITION_APPID = "appId";

    /**
     * 导出参数 sheetName
     */
    String EXPORT_CONDITION_SHEETNAME = "sheetName";

    /**
     * 导出参数 siteId
     */
    String EXPORT_CONDITION_SITEID = "siteId";

    /**
     * PDF显示文本参数 活动名
     */
    String PDF_CONDITION_TEXT_ACTIVITY = "activity";

    /**
     * PDF显示文本参数 活动时间
     */
    String  PDF_CONDITION_TEXT_TIME = "time";

    /**
     * PDF显示文本参数时间的分隔符
     */
    String PDF_CONDITION_TEXT_TIME_SEPARATOR = " ～ ";

    /**
     * 时间格式，不包含秒
     */
    String DATE_FORMART_WITHOUT_SECONDS = "yyyy-MM-dd HH:mm";

    /**
     * 顾客来源 个人中心
     */
    String CST_SOURCE_PERSON_CENTER = "personCenter";

    /**
     * 参与门槛  2.条件
     */
    short THRESHOLD_TYPE_CONDITION = 2;

    /**
     * 字体文件路径
     */
    String FONT_PATH = "font/WRYH.ttf";

    /**
     * pdf文件后缀
     */
    String PDF_FILE_SUFFIX = ".pdf";

    String WX_PARAM_PAGE = "page";

    String WX_PARAM_SCENE = "scene";

    String FILE_JPG = ".jpg";

}
