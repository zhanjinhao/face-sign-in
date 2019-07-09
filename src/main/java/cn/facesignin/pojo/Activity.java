package cn.facesignin.pojo;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Activity {
	
    @Override
	public String toString() {
		return "Activity [aid=" + aid + ", aname=" + aname + ", astartTime=" + astartTime + ", aendTime=" + aendTime
				+ ", oid=" + oid + ", gid=" + gid + ", achargeMan=" + achargeMan + ", acontact=" + acontact
				+ ", astatus=" + astatus + "]";
	}

	private Integer aid;

    private String aname;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date astartTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date aendTime;

    private Integer oid;

    private Integer gid;

    private String achargeMan;

    private String acontact;

    private String astatus;

    public Activity() {
    	
    	gid = 10000;
    	
	}
    
    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname == null ? null : aname.trim();
    }

    public Date getAstartTime() {
        return astartTime;
    }

    public void setAstartTime(Date astartTime) {
        this.astartTime = astartTime;
    }

    public Date getAendTime() {
        return aendTime;
    }

    public void setAendTime(Date aendTime) {
        this.aendTime = aendTime;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getAchargeMan() {
        return achargeMan;
    }

    public void setAchargeMan(String achargeMan) {
        this.achargeMan = achargeMan == null ? null : achargeMan.trim();
    }

    public String getAcontact() {
        return acontact;
    }

    public void setAcontact(String acontact) {
        this.acontact = acontact == null ? null : acontact.trim();
    }

    public String getAstatus() {
        return astatus;
    }

    public void setAstatus(String astatus) {
        this.astatus = astatus == null ? null : astatus.trim();
    }
}