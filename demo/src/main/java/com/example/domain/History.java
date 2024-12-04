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
    @TableId(value = "HID", type = IdType.AUTO)
    private Integer hid;

    private Date htime;

    private String hreason;

    private Integer gid;

    private String hnameO;

    private String hnameN;

    private String hdescO;

    private String hdescN;

    private Integer hstockO;

    private Integer hstockN;

    private Integer hvalueO;

    private Integer hvalueN;

    private Integer hcidO;

    private Integer hcidN;

    private Integer hshelfO;

    private Integer hshelfN;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    public Date getHtime() {
        return htime;
    }

    public void setHtime(Date htime) {
        this.htime = htime;
    }

    public String getHnameO() {
        return hnameO;
    }

    public void setHnameO(String hnameO) {
        this.hnameO = hnameO;
    }

    public String getHreason() {
        return hreason;
    }

    public void setHreason(String hreason) {
        this.hreason = hreason;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getHdescO() {
        return hdescO;
    }

    public void setHdescO(String hdescO) {
        this.hdescO = hdescO;
    }

    public String getHnameN() {
        return hnameN;
    }

    public void setHnameN(String hnameN) {
        this.hnameN = hnameN;
    }

    public Integer getHstockO() {
        return hstockO;
    }

    public void setHstockO(Integer hstockO) {
        this.hstockO = hstockO;
    }

    public String getHdescN() {
        return hdescN;
    }

    public void setHdescN(String hdescN) {
        this.hdescN = hdescN;
    }

    public Integer getHstockN() {
        return hstockN;
    }

    public void setHstockN(Integer hstockN) {
        this.hstockN = hstockN;
    }

    public Integer getHvalueN() {
        return hvalueN;
    }

    public void setHvalueN(Integer hvalueN) {
        this.hvalueN = hvalueN;
    }

    public Integer getHcidO() {
        return hcidO;
    }

    public void setHcidO(Integer hcidO) {
        this.hcidO = hcidO;
    }

    public Integer getHvalueO() {
        return hvalueO;
    }

    public void setHvalueO(Integer hvalueO) {
        this.hvalueO = hvalueO;
    }

    public Integer getHcidN() {
        return hcidN;
    }

    public void setHcidN(Integer hcidN) {
        this.hcidN = hcidN;
    }

    public Integer getHshelfO() {
        return hshelfO;
    }

    public void setHshelfO(Integer hshelfO) {
        this.hshelfO = hshelfO;
    }

    public Integer getHshelfN() {
        return hshelfN;
    }

    public void setHshelfN(Integer hshelfN) {
        this.hshelfN = hshelfN;
    }
}