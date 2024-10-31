package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@TableName("Seller")
public class Seller {
    @TableId("SAccount")
    private String saccount;
    @TableField("SPassword")
    private String spassword;
}
