package com.egr.drillinghelper.bean.request;

/**
 * Created by Ymmmsick on 5/11/17.
 */

public class LoginRequest {

    /**
     * loginName : 111121
     * password : 123456
     * xPoint : 104.055947
     * yPoint : 30.551002
     * lastLoginAddress :
     * rememberMe : true
     */

    private String loginName;
    private String password;
    private double xPoint;
    private double yPoint;
    private String lastLoginAddress;
    private boolean rememberMe;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getXPoint() {
        return xPoint;
    }

    public void setXPoint(double xPoint) {
        this.xPoint = xPoint;
    }

    public double getYPoint() {
        return yPoint;
    }

    public void setYPoint(double yPoint) {
        this.yPoint = yPoint;
    }

    public String getLastLoginAddress() {
        return lastLoginAddress;
    }

    public void setLastLoginAddress(String lastLoginAddress) {
        this.lastLoginAddress = lastLoginAddress;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
