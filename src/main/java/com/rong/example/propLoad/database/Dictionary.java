package com.rong.example.propLoad.database;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class Dictionary {
    private String id;

    private String type;

    private String name;

    private String value;

    private BigDecimal status;

    private Date orderId;

    private String createAccount;

    private Date createTime;

    private String modifyAccount;

    private Date modifyTime;
}
