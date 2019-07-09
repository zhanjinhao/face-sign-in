package cn.facesignin.pojo;

import java.util.Date;

public class MyGroup {
    private Integer gid;

    private String gname;

    private Integer oid;

    private String gstatus;

    private Date gcreatetime;

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
        this.gname = gname == null ? null : gname.trim();
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public String getGstatus() {
        return gstatus;
    }

    public void setGstatus(String gstatus) {
        this.gstatus = gstatus == null ? null : gstatus.trim();
    }

    public Date getGcreatetime() {
        return gcreatetime;
    }

    public void setGcreatetime(Date gcreatetime) {
        this.gcreatetime = gcreatetime;
    }
}