package com.mogul.gec.pojo;


import java.util.Date;

public class UserInf {

  public long id;
  public String loginname;
  public String password;
  public long status;
  public Date createdate;
  public String username;
  public String imgname;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getLoginname() {
    return loginname;
  }

  public void setLoginname(String loginname) {
    this.loginname = loginname;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }


  public Date getCreatedate() {
    return createdate;
  }

  public void setCreatedate(Date createdate) {
    this.createdate = createdate;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getImgname() {
    return imgname;
  }

  public void setImgname(String imgname) {
    this.imgname = imgname;
  }

}
