package org.frisbeemall.domain;

public class Shelf {
    private int shid;
    private int gid;
    private double shstate;

    // Getters and setters
    public int getShid() {
        return shid;
    }

    public void setShid(int shid) {
        this.shid = shid;
    }

    public int getGid() {
        return gid;
    }
    public void setGid(int gid) {
        this.gid = gid;
    }
    
    public double getShstate() {
        return shstate;
    }
    
    public void setShstate(double shstate) {
        this.shstate = shstate;
    }
}