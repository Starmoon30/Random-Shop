package org.random_shop.vo;

public class Order {
	int Oid;
	String oDesc;
	String gName;
	String gDesc;
	int gvalue;
	int Ostate;
	public int getOid() {
		return Oid;
	}
	public void setOid(int oid) {
		Oid = oid;
	}
	public String getoDesc() {
		return oDesc;
	}
	public void setoDesc(String oDesc) {
		this.oDesc = oDesc;
	}
	public String getgName() {
		return gName;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}
	public String getgDesc() {
		return gDesc;
	}
	public void setgDesc(String gDesc) {
		this.gDesc = gDesc;
	}
	public int getGvalue() {
		return gvalue;
	}
	public void setGvalue(int gvalue) {
		this.gvalue = gvalue;
	}
	public int getOstate() {
		return Ostate;
	}
	public void setOstate(int ostate) {
		Ostate = ostate;
	}

	public Order(int oid, String oDesc, String gName, String gDesc, int gvalue, int ostate) {
		super();
		Oid = oid;
		this.oDesc = oDesc;
		this.gName = gName;
		this.gDesc = gDesc;
		this.gvalue = gvalue;
		Ostate = ostate;
	}
	public Order() {
		super();
	}
}