package cn.facesignin.pojo.controller;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class AdminOrg {
	private Integer oid;
	private String oname;
	private String oemail;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date ocreateTime;
	private String ostatus;
	private Integer num;
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public String getOname() {
		return oname;
	}
	public void setOname(String oname) {
		this.oname = oname;
	}
	public String getOemail() {
		return oemail;
	}
	public void setOemail(String oemail) {
		this.oemail = oemail;
	}
	public Date getOcreateTime() {
		return ocreateTime;
	}
	public void setOcreateTime(Date ocreateTime) {
		this.ocreateTime = ocreateTime;
	}
	public String getOstatus() {
		return ostatus;
	}
	public void setOstatus(String ostatus) {
		this.ostatus = ostatus;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}

	
}
