package com.example.manojkumar.collegeguide;

public class UserProfile {
      public String userEmail;
      public String userPassword;
      public String userName;
      public String userPhone;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserSports() {
        return userSports;
    }

    public void setUserSports(String userSports) {
        this.userSports = userSports;
    }

    public String getUserAcademics() {
        return userAcademics;
    }

    public void setUserAcademics(String userAcademics) {
        this.userAcademics = userAcademics;
    }

    public String getUserService() {
        return userService;
    }

    public void setUserService(String userService) {
        this.userService = userService;
    }

    public String getUserPlacements() {
        return userPlacements;
    }

    public void setUserPlacements(String userPlacements) {
        this.userPlacements = userPlacements;
    }

    public String userSports;
      public String userAcademics;
      public String userService;
      public String userPlacements;

    public UserProfile(String userEmail, String userPassword, String userName, String userPhone, String userSports, String userAcademics, String userService, String userPlacements) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userSports = userSports;
        this.userAcademics = userAcademics;
        this.userService = userService;
        this.userPlacements = userPlacements;
    }
    public UserProfile(){

    }

}
