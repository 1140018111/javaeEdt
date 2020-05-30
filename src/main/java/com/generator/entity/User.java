package com.generator.entity;

import java.util.Date;

public class User {
    private String userid;

    private String password;

    private String username;

    private Date creatdate;

    private Date updatedate;

    private String satatus;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Date getCreatdate() {
        return creatdate;
    }

    public void setCreatdate(Date creatdate) {
        this.creatdate = creatdate;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public String getSatatus() {
        return satatus;
    }

    public void setSatatus(String satatus) {
        this.satatus = satatus == null ? null : satatus.trim();
    }
}