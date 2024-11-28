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
 * @TableName History
 */
@TableName(value ="History")
@Data
public class History implements Serializable {
    /**
     * 
     */
    @TableId(value = "HID", type = IdType.AUTO)
    private Integer HID;

    /**
     * 
     */
    @TableField(value = "HTime")
    private Date HTime;

    /**
     * 
     */
    @TableField(value = "HReason")
    private String HReason;

    /**
     * 
     */
    @TableField(value = "GID")
    private Integer GID;

    /**
     * 
     */
    @TableField(value = "HName_O")
    private String HName_O;

    /**
     * 
     */
    @TableField(value = "HName_N")
    private String HName_N;

    /**
     * 
     */
    @TableField(value = "HDesc_O")
    private String HDesc_O;

    /**
     * 
     */
    @TableField(value = "HDesc_N")
    private String HDesc_N;

    /**
     * 
     */
    @TableField(value = "HStock_O")
    private Integer HStock_O;

    /**
     * 
     */
    @TableField(value = "HStock_N")
    private Integer HStock_N;

    /**
     * 
     */
    @TableField(value = "HValue_O")
    private Integer HValue_O;

    /**
     * 
     */
    @TableField(value = "HValue_N")
    private Integer HValue_N;

    /**
     * 
     */
    @TableField(value = "HCID_O")
    private Integer HCID_O;

    /**
     * 
     */
    @TableField(value = "HCID_N")
    private Integer HCID_N;

    /**
     * 
     */
    @TableField(value = "HShelf_O")
    private Integer HShelf_O;

    /**
     * 
     */
    @TableField(value = "HShelf_N")
    private Integer HShelf_N;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}