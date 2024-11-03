package com.example.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName Goods
 */
@TableName(value ="Goods")
@Data
public class Goods implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer gid;

    /**
     * 
     */
    private String gname;

    /**
     * 
     */
    private String gdesc;

    /**
     * 
     */
    private Integer gstock;

    /**
     * 
     */
    private Integer gvalue;

    /**
     * 
     */
    private Integer cid;

    /**
     * 
     */
    private Integer gshelf;

    /**
     * 
     */
    private Float gstate;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getGdesc() {
        return gdesc;
    }

    public void setGdesc(String gdesc) {
        this.gdesc = gdesc;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public Integer getGstock() {
        return gstock;
    }

    public void setGstock(Integer gstock) {
        this.gstock = gstock;
    }

    public Integer getGvalue() {
        return gvalue;
    }

    public void setGvalue(Integer gvalue) {
        this.gvalue = gvalue;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getGshelf() {
        return gshelf;
    }

    public void setGshelf(Integer gshelf) {
        this.gshelf = gshelf;
    }

    public Float getGstate() {
        return gstate;
    }

    public void setGstate(Float gstate) {
        this.gstate = gstate;
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
        Goods other = (Goods) that;
        return (this.getGid() == null ? other.getGid() == null : this.getGid().equals(other.getGid()))
            && (this.getGname() == null ? other.getGname() == null : this.getGname().equals(other.getGname()))
            && (this.getGdesc() == null ? other.getGdesc() == null : this.getGdesc().equals(other.getGdesc()))
            && (this.getGstock() == null ? other.getGstock() == null : this.getGstock().equals(other.getGstock()))
            && (this.getGvalue() == null ? other.getGvalue() == null : this.getGvalue().equals(other.getGvalue()))
            && (this.getCid() == null ? other.getCid() == null : this.getCid().equals(other.getCid()))
            && (this.getGshelf() == null ? other.getGshelf() == null : this.getGshelf().equals(other.getGshelf()))
            && (this.getGstate() == null ? other.getGstate() == null : this.getGstate().equals(other.getGstate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getGid() == null) ? 0 : getGid().hashCode());
        result = prime * result + ((getGname() == null) ? 0 : getGname().hashCode());
        result = prime * result + ((getGdesc() == null) ? 0 : getGdesc().hashCode());
        result = prime * result + ((getGstock() == null) ? 0 : getGstock().hashCode());
        result = prime * result + ((getGvalue() == null) ? 0 : getGvalue().hashCode());
        result = prime * result + ((getCid() == null) ? 0 : getCid().hashCode());
        result = prime * result + ((getGshelf() == null) ? 0 : getGshelf().hashCode());
        result = prime * result + ((getGstate() == null) ? 0 : getGstate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", gid=").append(gid);
        sb.append(", gname=").append(gname);
        sb.append(", gdesc=").append(gdesc);
        sb.append(", gstock=").append(gstock);
        sb.append(", gvalue=").append(gvalue);
        sb.append(", cid=").append(cid);
        sb.append(", gshelf=").append(gshelf);
        sb.append(", gstate=").append(gstate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}