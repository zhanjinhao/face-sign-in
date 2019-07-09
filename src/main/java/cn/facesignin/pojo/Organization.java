package cn.facesignin.pojo;

import java.util.Date;

public class Organization {
	
    @Override
	public String toString() {
		return "Organization [oid=" + oid + ", opwd=" + opwd + ", oemail=" + oemail + ", oname=" + oname
				+ ", ocreateTime=" + ocreateTime + ", oowner=" + oowner + ", oownerEmail=" + oownerEmail + ", ostatus="
				+ ostatus + "]";
	}

	private Integer oid;

    private String opwd;

    private String oemail;

    private String oname;

    private Date ocreateTime;

    private String oowner;

    private String oownerEmail;

    private String ostatus;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public String getOpwd() {
        return opwd;
    }

    public void setOpwd(String opwd) {
        this.opwd = opwd == null ? null : opwd.trim();
    }

    public String getOemail() {
        return oemail;
    }

    public void setOemail(String oemail) {
        this.oemail = oemail == null ? null : oemail.trim();
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname == null ? null : oname.trim();
    }

    public Date getOcreateTime() {
        return ocreateTime;
    }

    public void setOcreateTime(Date ocreateTime) {
        this.ocreateTime = ocreateTime;
    }

    public String getOowner() {
        return oowner;
    }

    public void setOowner(String oowner) {
        this.oowner = oowner == null ? null : oowner.trim();
    }

    public String getOownerEmail() {
        return oownerEmail;
    }

    public void setOownerEmail(String oownerEmail) {
        this.oownerEmail = oownerEmail == null ? null : oownerEmail.trim();
    }

    public String getOstatus() {
        return ostatus;
    }

    public void setOstatus(String ostatus) {
        this.ostatus = ostatus == null ? null : ostatus.trim();
    }
}