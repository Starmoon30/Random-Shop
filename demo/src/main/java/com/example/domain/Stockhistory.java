package com.example.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName StockHistory
 */
@TableName(value ="StockHistory")
@Data
public class Stockhistory implements Serializable {
    /**
     * 
     */
    @TableId(value = "SHID", type = IdType.AUTO)
    private Integer SHID;

    @TableField(value = "SHTime")
    private Date SHTime;


    @TableField(value = "SHReason")
    private Integer SHReason;


    @TableField(value = "GID")
    private Integer GID;

    @TableField(value = "SHStock_O")
    private Integer SHStockO;


    @TableField(value = "SHStock_N")
    private Integer SHStockN;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}