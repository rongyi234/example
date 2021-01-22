package com.rong.example.service;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.rong.example.bean.bo.UserMessage;
import com.rong.example.constant.UITypeEnum;
import com.rong.example.mapper.UserMessagePoMapper;
import com.rong.example.mapper.pojo.UserMessagePo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统配置服务
 * 
 * @author rongyi
 */
@Slf4j
@Service
public class UserMessageService {

	@Autowired
	private UserMessagePoMapper userMessagePoMapper;



	public  List<UserMessage> selectMsgInfo(String userId){
		UserMessagePo reqPo=new UserMessagePo();
		reqPo.setAccountId(userId);
		List<UserMessagePo> polist=userMessagePoMapper.selectMsgInfo( reqPo);

		if(CollUtil.isEmpty(polist)){
			log.info("用户消息不存在，userId="+userId);
			return null;
		}

		//封装转换
		List<UserMessage> msgList=polist.stream().map(x -> {
			UserMessage userMessage=new UserMessage();
			BeanUtil.copyProperties(x,userMessage);

			//获取枚举值描述
			userMessage.setUiTypeDesc(UITypeEnum.getNameByOpenType(userMessage.getUiType()));
			return userMessage;
		}).collect(Collectors.toList());

		return msgList;
	}
}
