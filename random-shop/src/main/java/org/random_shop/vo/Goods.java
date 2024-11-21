package org.random_shop.vo;

public class Goods {
    private int gid;
    private String gname;
    private String gdesc;
    private int gvalue;
    private byte[] gpic;
    private int gshelf;
    private int gstate;

    public int getGshelf() {
        return gshelf;
    }

    public void setGshelf(int gshelf) {
        this.gshelf = gshelf;
    }

    public int getGstate() {
        return gstate;
    }

    public void setGstate(int gstate) {
        this.gstate = gstate;
    }

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

    public byte[] getGpic() {
        return gpic;
    }

    public void setGpic(byte[] gpic) {
        this.gpic = gpic;
    }

    public Goods(int gid, String gname, String gdesc, int gvalue, byte[] gpic, int gshelf, int gstate) {
        this.gid = gid;
        this.gname = gname;
        this.gdesc = gdesc;
        this.gvalue = gvalue;
        this.gpic = gpic;
        this.gshelf = gshelf;
        this.gstate = gstate;
    }

    public Goods() {

    }

}