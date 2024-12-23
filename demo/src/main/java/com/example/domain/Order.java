package com.example.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName Order
 */
@TableName(value ="Order")
@Data
public class Order implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer oid;

    /**
     * 
     */
    private Integer gid;

    /**
     * 
     */
    private String uaccount;

    /**
     * 
     */
    private String ophone;

    /**
     * 
     */
    private String oaddress;

    /**
     * 
     */
    private String oremark;

    /**
     * 
     */
    private Integer ostate;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getOphone() {
        return ophone;
    }

    public void setOphone(String ophone) {
        this.ophone = ophone;
    }

    public String getUaccount() {
        return uaccount;
    }

    public void setUaccount(String uaccount) {
        this.uaccount = uaccount;
    }

    public String getOremark() {
        return oremark;
    }

    public void setOremark(String oremark) {
        this.oremark = oremark;
    }

    public String getOaddress() {
        return oaddress;
    }

    public void setOaddress(String oaddress) {
        this.oaddress = oaddress;
    }

    public Integer getOstate() {
        return ostate;
    }

    public void setOstate(Integer ostate) {
        this.ostate = ostate;
    }

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Order other = (Order) that;
        return (this.getOid() == null ? other.getOid() == null : this.getOid().equals(other.getOid()))
            && (this.getGid() == null ? other.getGid() == null : this.getGid().equals(other.getGid()))
            && (this.getUaccount() == null ? other.getUaccount() == null : this.getUaccount().equals(other.getUaccount()))
            && (this.getOphone() == null ? other.getOphone() == null : this.getOphone().equals(other.getOphone()))
            && (this.getOaddress() == null ? other.getOaddress() == null : this.getOaddress().equals(other.getOaddress()))
            && (this.getOremark() == null ? other.getOremark() == null : this.getOremark().equals(other.getOremark()))
            && (this.getOstate() == null ? other.getOstate() == null : this.getOstate().equals(other.getOstate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOid() == null) ? 0 : getOid().hashCode());
        result = prime * result + ((getGid() == null) ? 0 : getGid().hashCode());
        result = prime * result + ((getUaccount() == null) ? 0 : getUaccount().hashCode());
        result = prime * result + ((getOphone() == null) ? 0 : getOphone().hashCode());
        result = prime * result + ((getOaddress() == null) ? 0 : getOaddress().hashCode());
        result = prime * result + ((getOremark() == null) ? 0 : getOremark().hashCode());
        result = prime * result + ((getOstate() == null) ? 0 : getOstate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", oid=").append(oid);
        sb.append(", gid=").append(gid);
        sb.append(", uaccount=").append(uaccount);
        sb.append(", ophone=").append(ophone);
        sb.append(", oaddress=").append(oaddress);
        sb.append(", oremark=").append(oremark);
        sb.append(", ostate=").append(ostate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}