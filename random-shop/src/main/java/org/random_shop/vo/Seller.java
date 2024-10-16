package org.random_shop.vo;

public class Seller {
    private String account;
    private String pwd;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Seller(String account, String pwd) {
        this.account = account;
        this.pwd = pwd;
    }

    public Seller() {
    }
}
