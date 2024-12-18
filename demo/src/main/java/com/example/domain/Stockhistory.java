package com.example.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.Setter;

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
    @Setter
    @TableId(value = "SHID", type = IdType.AUTO)
    private Integer SHID;

    /**
     *
     */
    @Setter
    @TableField(value = "SHTime")
    private Date SHTime;

    /**
     *
     */
    @Setter
    @TableField(value = "SHReason")
    private Integer SHReason;

    /**
     *
     */
    @Setter
    @TableField(value = "GID")
    private Integer GID;

    /**
     *
     */
    @TableField(value = "SHStock_O")
    private Integer SHStock_O;

    /**
     *
     */
    @TableField(value = "SHStock_N")
    private Integer SHStock_N;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public void setSHStockO(Integer SHStock_O) {
        this.SHStock_O = SHStock_O;
    }

    public void setSHStockN(Integer SHStock_N) {
        this.SHStock_N = SHStock_N;
    }
}
