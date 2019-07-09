package cn.facesignin.pojo;

public class User {

    @Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", upwd=" + upwd + ", uemail=" + uemail + ", ufaceToken="
				+ ufaceToken + ", uimgPath=" + uimgPath + ", ustatus=" + ustatus + "]";
	}

	private String uid;

    private String uname;

    private String upwd;

    private String uemail;

    private String ufaceToken;

    private String uimgPath;

    private String ustatus;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd == null ? null : upwd.trim();
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail == null ? null : uemail.trim();
    }

    public String getUfaceToken() {
        return ufaceToken;
    }

    public void setUfaceToken(String ufaceToken) {
        this.ufaceToken = ufaceToken == null ? null : ufaceToken.trim();
    }

    public String getUimgPath() {
        return uimgPath;
    }

    public void setUimgPath(String uimgPath) {
        this.uimgPath = uimgPath == null ? null : uimgPath.trim();
    }

    public String getUstatus() {
        return ustatus;
    }

    public void setUstatus(String ustatus) {
        this.ustatus = ustatus == null ? null : ustatus.trim();
    }
    
    @Override
    public boolean equals(Object obj) {
    	User user = (User)obj;
    	if(user.uid == this.uid)
    		return true;
    	return false;
    }
    
}