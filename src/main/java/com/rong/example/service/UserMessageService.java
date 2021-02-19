package com.rong.example.service;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.rong.example.advice.filter.PageLimitHolderFilter;
import com.rong.example.bean.bo.UserMessage;
import com.rong.example.cache.RedisKeyConstant;
import com.rong.example.cache.RedisClient;
import com.rong.example.constant.UITypeEnum;
import com.rong.example.mapper.UserMessagePoMapper;
import com.rong.example.mapper.pojo.UserMessagePo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
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

	@Autowired
	private RedisClient redisClient;


	public  List<UserMessage> selectMsgInfo(String userId) throws Exception{

		//userId不为空时，优先查询缓存
		if(StrUtil.isNotEmpty(userId)){
			String key = RedisKeyConstant.CACHE_TYPE_USER+RedisKeyConstant.UNDERLINE+userId;

			UserMessage userMessage= redisClient.getObject(key,UserMessage.class);
			if(userMessage != null){
				log.info("查询redis: userMessage="+userMessage);
				List<UserMessage> list= new ArrayList<>();
				list.add(userMessage);
				return list;
			}
		}


		UserMessagePo reqPo=new UserMessagePo();
		reqPo.setAccountId(userId);
		log.info("获取分页参数："+ PageLimitHolderFilter.getContext());
		List<UserMessagePo> polist=userMessagePoMapper.selectMsgInfo( reqPo, PageLimitHolderFilter.getContext());

		if(CollUtil.isEmpty(polist)){
			log.info("用户消息不存在，userId="+userId);
			return null;
		}

		//存储redis
		if(StrUtil.isNotEmpty(userId)){
			String key = RedisKeyConstant.CACHE_TYPE_USER+RedisKeyConstant.UNDERLINE+userId;
			UserMessage userMessage=new UserMessage();
			BeanUtil.copyProperties(polist.get(0),userMessage);
			log.info("存储redis: userMessage="+userMessage);
			redisClient.setObject(key,userMessage,10, TimeUnit.SECONDS);
		}

		//封装转换
		List<UserMessage> msgList=polist.stream().map(x -> {
			UserMessage userMessage=new UserMessage();
			BeanUtil.copyProperties(x,userMessage);

			//获取枚举值描述
			userMessage.setUiTypeDesc(UITypeEnum.getNameByOpenType(userMessage.getUiType()));
			return userMessage;
		}).collect(Collectors.toList());

		//分页查询: totalCount
		if(PageLimitHolderFilter.getContext() != null){
			PageLimitHolderFilter.getContext().setTotalCount(userMessagePoMapper.selectTotalCount(reqPo));
		}

		return msgList;
	}
}
