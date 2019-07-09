package cn.facesignin.pojo.controller;

public class AdminUser {
	private String uid;
	private String uname;
	private Integer num;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "AdminUser [uid=" + uid + ", uname=" + uname + ", num=" + num + "]";
	}
	
}
