package com.egr.drillinghelper.bean.response;

import java.io.Serializable;

/**
 * author lzd
 * date 2017/9/28 16:21
 * 类描述：
 */

public class UserInfo implements Serializable{
    String name;
    String phone;
    String company;
    String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
