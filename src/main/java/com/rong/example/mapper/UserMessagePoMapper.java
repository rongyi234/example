package com.rong.example.mapper;

import com.rong.example.advice.filter.PageLimit;
import com.rong.example.mapper.pojo.UserMessagePo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMessagePoMapper {

    int insert(UserMessagePo record);

    List<UserMessagePo> selectMsgInfo(@Param("po")UserMessagePo po, @Param("page")PageLimit page);

    /**
     * 查询totalCount
     */
    Integer selectTotalCount(@Param("po")UserMessagePo po);

}