package cn.facesignin.pojo;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import cn.facesignin.constant.Type;

public class SigninRecord {
    private Integer aid;

    private String uid;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date sinTime;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date soutTime;

    private String simgPath;

    private String scheckType;

    private String sstatus;

    private Double confidence;

    public Integer getAid() {
        return aid;
    }
    
    @Override
	public String toString() {
		return "SigninRecord [aid=" + aid + ", uid=" + uid + ", sinTime=" + sinTime + ", soutTime=" + soutTime
				+ ", simgPath=" + simgPath + ", scheckType=" + scheckType + ", sstatus=" + sstatus + ", confidence="
				+ confidence + "]";
	}

    public SigninRecord() {
	}
    
    /**
     * 手动录入构造
     * @param uid
     * @param aid
     */
    public SigninRecord(String uid, Integer aid) {
    	this.uid = uid;
    	this.aid = aid;
		this.confidence = 100.0;
		this.scheckType = Type.SIGNIN_RECORD_SCHECK_TYPE_MANUAL;
		this.sstatus = Type.SIGNIN_RECORD_SSTATUS_NORMAL;
		this.sinTime = new Date();
		this.soutTime = new Date();
	}
    
	public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public Date getSinTime() {
        return sinTime;
    }

    public void setSinTime(Date sinTime) {
        this.sinTime = sinTime;
    }

    public Date getSoutTime() {
        return soutTime;
    }

    public void setSoutTime(Date soutTime) {
        this.soutTime = soutTime;
    }

    public String getSimgPath() {
        return simgPath;
    }

    public void setSimgPath(String simgPath) {
        this.simgPath = simgPath == null ? null : simgPath.trim();
    }

    public String getScheckType() {
        return scheckType;
    }

    public void setScheckType(String scheckType) {
        this.scheckType = scheckType == null ? null : scheckType.trim();
    }

    public String getSstatus() {
        return sstatus;
    }

    public void setSstatus(String sstatus) {
        this.sstatus = sstatus == null ? null : sstatus.trim();
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }
}