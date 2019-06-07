package com.drydock.entity;

import java.util.Date;

public class UserCurrentShip {
	private long userid;
	private long shipid;
	private Date createdate;
	
	
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public long getShipid() {
		return shipid;
	}
	public void setShipid(long shipid) {
		this.shipid = shipid;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
}
