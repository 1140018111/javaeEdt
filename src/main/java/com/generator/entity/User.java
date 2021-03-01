package com.generator.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
@Scope("prototype")
public class User {
    public String userid;

    public String password;

    public String username;

    public Date creatdate;

    public Date updatedate;

    public String satatus;



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

    @Override
    public String toString() {
        return "User{" +
                       "userid='" + userid + '\'' +
                       ", password='" + password + '\'' +
                       ", username='" + username + '\'' +
                       ", creatdate=" + creatdate +
                       ", updatedate=" + updatedate +
                       ", satatus='" + satatus + '\'' +
                       '}';
    }
}