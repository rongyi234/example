package com.rong.example.mapper;

import com.rong.example.mapper.pojo.UserMessagePo;

import java.util.List;

public interface UserMessagePoMapper {
    int insert(UserMessagePo record);

    List<UserMessagePo> selectMsgInfo(UserMessagePo po);
}