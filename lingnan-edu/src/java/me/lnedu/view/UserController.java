package me.lnedu.view;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class UserController implements Serializable {

  private static final Logger logger = Logger.getLogger(UserController.class.getName());

  private String userName;
  private String userPassword;
  private boolean staySignin;

  public UserController() {
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  public boolean isStaySignin() {
    return staySignin;
  }

  public void setStaySignin(boolean staySignin) {
    this.staySignin = staySignin;
  }

  public String signin() {
    if (userName.equals("黄灏")) {
      return "userInfo?faces-redirect=true";
    } else {
      return "failure?faces-redirect=true";
    }
  }
}
