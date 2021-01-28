package com.rong.example.api;

import com.rong.example.bean.bo.UserMessage;
import com.rong.example.bean.req.SelectUserMessagesReq;
import com.rong.example.service.UserMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户消息
 */
@Slf4j
@RestController
@RequestMapping("/userMsg")
public class UserMessageController {

    @Autowired
    private UserMessageService userMessageService;


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
}
