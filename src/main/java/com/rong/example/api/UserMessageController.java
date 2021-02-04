package com.rong.example.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.rong.example.bean.bo.UserMessage;
import com.rong.example.bean.req.SelectUserMessagesReq;
import com.rong.example.cache.RedisKeyConstant;
import com.rong.example.constant.UITypeEnum;
import com.rong.example.mapper.pojo.UserMessagePo;
import com.rong.example.propLoad.cache.UserMessageCache;
import com.rong.example.service.UserMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 用户消息
 */
@Slf4j
@RestController
@RequestMapping("/userMsg")
public class UserMessageController {

    @Autowired
    private UserMessageService userMessageService;

    @Autowired
    private UserMessageCache userMessageCache;


    @RequestMapping(value = "/select", method = RequestMethod.POST)
    public List<UserMessage> selectUserMessages(@RequestBody SelectUserMessagesReq req) throws Exception {
        List<UserMessage> msgList = userMessageService.selectMsgInfo(req.getUserId());
        return msgList;
    }

    @RequestMapping(value = "/selectByUserId", method = RequestMethod.POST)
    public List<UserMessage> selectUserMessagesByUserId(@Validated @RequestBody SelectUserMessagesReq req)
    throws Exception{
        List<UserMessage> msgList = userMessageService.selectMsgInfo(req.getUserId());
        return msgList;
    }

    @RequestMapping(value = "/selectCache", method = RequestMethod.POST)
    public List<UserMessage> selectCache(@Validated @RequestBody SelectUserMessagesReq req)
            throws Exception{
        UserMessagePo reqPo=new UserMessagePo();
        reqPo.setAccountId(req.getUserId());
        List<UserMessagePo> polist = userMessageCache.selectMsgInfo(reqPo);

        if(CollUtil.isEmpty(polist)){
            log.info("用户消息不存在，userId="+req.getUserId());
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

    @RequestMapping(value = "/removeCache", method = RequestMethod.POST)
    public String removeCache(@Validated @RequestBody SelectUserMessagesReq req)
            throws Exception{
        UserMessagePo reqPo=new UserMessagePo();
        reqPo.setAccountId(req.getUserId());
        userMessageCache.removeCache(reqPo);

        return "success";
    }
}
