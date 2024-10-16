package org.frisbeemall.domain;

public class Goods {
    private int gid;
    private String gname;
    private String gdesc;
    private int gvalue;
    private String gpic;

    // Getters and setters
    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getGdesc() {
        return gdesc;
    }

    public void setGdesc(String gdesc) {
        this.gdesc = gdesc;
    }

    public int getGvalue() {
        return gvalue;
    }

    public void setGvalue(int gvalue) {
        this.gvalue = gvalue;
    }

    public String getGpic() {
        return gpic;
    }

    public void setGpic(String gpic) {
        this.gpic = gpic;
    }
}