package com.example.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName Seller
 */
@TableName(value ="Seller")
@Data
public class Seller implements Serializable {
    public String getSpassword() {
        return spassword;
    }

    public void setSpassword(String spassword) {
        this.spassword = spassword;
    }

    public String getSaccount() {
        return saccount;
    }

    public void setSaccount(String saccount) {
        this.saccount = saccount;
    }

    /**
     * 
     */
    @TableId
    private String saccount;

    /**
     * 
     */
    private String spassword;

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
        Seller other = (Seller) that;
        return (this.getSaccount() == null ? other.getSaccount() == null : this.getSaccount().equals(other.getSaccount()))
            && (this.getSpassword() == null ? other.getSpassword() == null : this.getSpassword().equals(other.getSpassword()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSaccount() == null) ? 0 : getSaccount().hashCode());
        result = prime * result + ((getSpassword() == null) ? 0 : getSpassword().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", saccount=").append(saccount);
        sb.append(", spassword=").append(spassword);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}