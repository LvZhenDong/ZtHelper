package com.kklv.fragme.bean.request;

/**
 * Created by Ymmmsick on 6/8/17.
 */

public class NearbyRequest {
    private String startAge = "18";// 最小年龄默认18;
    private String endAge = "50";// 最大年龄默认50;
    private int pageNo = 1;// 默认第一页
    private int pageSize = 10;// 默认一页10个数据
    private int sex = 0;// 性别 默认0全部，1为女，2为男
    private int shopType = 0;// 商店类型 默认0为连锁店，1为单体店，2为诊所，3为补贴
    private int type = 0;// 用户类型 默认0为全部，1为普通，2为商家，3为店员
    private double xpoint;
    private double ypoint;
    private int minute = 0;// 出现时间0默认无时间限制，1为15分钟、2一小时、3一天、4三天
    private int userId;

    public String getEndAge() {
        return endAge;
    }

    public void setEndAge(String endAge) {
        this.endAge = endAge;
    }

    public String getStartAge() {
        return startAge;
    }

    public void setStartAge(String startAge) {
        this.startAge = startAge;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getShopType() {
        return shopType;
    }

    public void setShopType(int shopType) {
        this.shopType = shopType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getXpoint() {
        return xpoint;
    }

    public void setXpoint(double xpoint) {
        this.xpoint = xpoint;
    }

    public double getYpoint() {
        return ypoint;
    }

    public void setYpoint(double ypoint) {
        this.ypoint = ypoint;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "NearbyRequest{" +
                "startAge='" + startAge + '\'' +
                ", endAge='" + endAge + '\'' +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", sex=" + sex +
                ", shopType=" + shopType +
                ", type=" + type +
                ", xpoint=" + xpoint +
                ", ypoint=" + ypoint +
                ", minute=" + minute +
                ", userId=" + userId +
                '}';
    }
}
