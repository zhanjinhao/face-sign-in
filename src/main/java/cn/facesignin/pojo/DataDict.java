package cn.facesignin.pojo;

public class DataDict {
    private String dtype;

    private String dname;

    private String ddata;

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype == null ? null : dtype.trim();
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname == null ? null : dname.trim();
    }

    public String getDdata() {
        return ddata;
    }

    public void setDdata(String ddata) {
        this.ddata = ddata == null ? null : ddata.trim();
    }
}