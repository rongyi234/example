package com.rong.example.bean.bo;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Data
public class PageLimit {
    private static final Integer DEFAULTPAGESIZE= 10;

    private Integer pageNum;
    private Integer pageSize;
    private Integer totalCount;

    /**
     * sql查询起始index
     */
    private Integer startIndex;


    public PageLimit() {
    }

    public PageLimit(HttpServletRequest request) {
        this.pageNum = StrUtil.isEmpty(request.getParameter("pageNum"))? null: Integer.valueOf(request.getParameter("pageNum"));
        this.pageSize = StrUtil.isEmpty(request.getParameter("pageSize"))? DEFAULTPAGESIZE: Integer.valueOf(request.getParameter("pageSize"));

        if (pageNum != null) {
            this.startIndex = (this.pageNum - 1) * this.pageSize;
        }

    }

}
