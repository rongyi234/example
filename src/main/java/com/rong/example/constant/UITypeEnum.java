package com.rong.example.constant;


import cn.hutool.core.util.StrUtil;

/**
 * 用户推送消息类型枚举
 */
public enum UITypeEnum {

    /**
     * 1 充电信息推送
     */
    CHARGE_MSG(1, "充电信息推送"),

    /**
     * 2 卡券信息推送
     */
    VOUCHER_MSG(2, "卡券信息推送"),

    /**
     * 3 占位信息推送
     */
    SEAT_MSG(3, "占位信息推送");


    /**
     * 根据ype获取价格名称
     */
    public static String getNameByOpenType(Integer openType) {
        String name = StrUtil.EMPTY;
        if (openType != null) {
        	UITypeEnum[] types = UITypeEnum.values();
            for (UITypeEnum memberPriceEnum : types) {
                if (memberPriceEnum.getType().equals(openType)) {
                	name = memberPriceEnum.getName();
                    break;
                }
            }
        }
        return name;
    }

    private Integer type;

    private String name;

    private UITypeEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

	public Integer getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}
    
}
