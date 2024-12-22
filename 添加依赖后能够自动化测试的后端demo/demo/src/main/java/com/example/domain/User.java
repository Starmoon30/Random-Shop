package com.example.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName User
 */
@TableName(value ="User")
@Data
public class User implements Serializable {
    /**
     * 
     */
    @TableId
    private String uaccount;

    /**
     * 
     */
    private String upassword;

    /**
     * 
     */
    private String uphone;

    /**
     * 
     */
    private String uaddress;

    /**
     * 
     */
    private Integer ucategory;

    public String getUaccount() {
        return uaccount;
    }

    public void setUaccount(String uaccount) {
        this.uaccount = uaccount;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public String getUaddress() {
        return uaddress;
    }

    public void setUaddress(String uaddress) {
        this.uaddress = uaddress;
    }

    public Integer getUcategory() {
        return ucategory;
    }

    public void setUcategory(Integer ucategory) {
        this.ucategory = ucategory;
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
        User other = (User) that;
        return (this.getUaccount() == null ? other.getUaccount() == null : this.getUaccount().equals(other.getUaccount()))
            && (this.getUpassword() == null ? other.getUpassword() == null : this.getUpassword().equals(other.getUpassword()))
            && (this.getUphone() == null ? other.getUphone() == null : this.getUphone().equals(other.getUphone()))
            && (this.getUaddress() == null ? other.getUaddress() == null : this.getUaddress().equals(other.getUaddress()))
            && (this.getUcategory() == null ? other.getUcategory() == null : this.getUcategory().equals(other.getUcategory()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUaccount() == null) ? 0 : getUaccount().hashCode());
        result = prime * result + ((getUpassword() == null) ? 0 : getUpassword().hashCode());
        result = prime * result + ((getUphone() == null) ? 0 : getUphone().hashCode());
        result = prime * result + ((getUaddress() == null) ? 0 : getUaddress().hashCode());
        result = prime * result + ((getUcategory() == null) ? 0 : getUcategory().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uaccount=").append(uaccount);
        sb.append(", upassword=").append(upassword);
        sb.append(", uphone=").append(uphone);
        sb.append(", uaddress=").append(uaddress);
        sb.append(", ucategory=").append(ucategory);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}