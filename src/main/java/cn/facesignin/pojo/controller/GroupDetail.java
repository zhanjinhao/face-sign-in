package cn.facesignin.pojo.controller;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class GroupDetail {
	private Integer gid;
	private String gname;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date gcreateTime;
	private Integer num;
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public Date getGcreateTime() {
		return gcreateTime;
	}
	public void setGcreateTime(Date gcreateTime) {
		this.gcreateTime = gcreateTime;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
}
