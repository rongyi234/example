package com.rong.example.enumService;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.rong.example.bean.bo.UserMessage;
import com.rong.example.constant.UITypeEnum;
import com.rong.example.service.UserMessageService;
import com.rong.example.utils.SpringUtils;

import java.util.List;

public enum MessageEnum {

    /**
     * 1 充电信息推送
     */
    CHARGE_MSG(1, "充电信息推送"){

        @Override
        public UserMessage packageMsg(String userId) throws Exception {

            UserMessage example = getUserMessage(userId);

            UserMessage record=new UserMessage();
            BeanUtil.copyProperties(example,record);

            record.setUiType(getType());
            record.setInfo(getName());
            return record;
        }
    },

    /**
     * 2 卡券信息推送
     */
    VOUCHER_MSG(2, "卡券信息推送"){

        @Override
        public UserMessage packageMsg(String userId) throws Exception {
            UserMessage example = getUserMessage(userId);

            UserMessage record=new UserMessage();
            BeanUtil.copyProperties(example,record);

            record.setUiType(getType());
            record.setInfo(getName());
            return record;
        }
    };




    /**
     * 根据type获取对应enum
     */
    public static MessageEnum getEnumOf(Integer type) {
        String name = StrUtil.EMPTY;
        if (type != null) {
            MessageEnum[] enums = MessageEnum.values();
            for (MessageEnum e : enums) {
                if (e.getType().equals(type)) {
                    return e;
                }
            }
        }
        return null;
    }

    /**
     * 重写的方法
     */
    public abstract UserMessage packageMsg(String userId) throws Exception;



    private Integer type;

    private String name;

    MessageEnum(Integer type, String name) {
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



    /**
     * 获取容器中的服务，查询数据库
     */
    private static UserMessage getUserMessage(String userId) throws Exception {
        UserMessageService userMessageService = SpringUtils.getBean(UserMessageService.class);
        List<UserMessage> list= userMessageService.selectMsgInfo(userId);
        if(CollUtil.isNotEmpty(list)){
            return list.get(0);
        }
        return null;
    }




}
