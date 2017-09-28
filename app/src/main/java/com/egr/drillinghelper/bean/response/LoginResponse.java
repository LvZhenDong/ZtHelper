package com.egr.drillinghelper.bean.response;

/**
 * author lzd
 * date 2017/9/28 10:30
 * 类描述：
 */

public class LoginResponse {

    /**
     * name : 王伍
     * phone : 13207077777
     * company : xxx机械租赁公司
     * token : ea0b2ed8-efb3-4bb3-bc0b-0508c9ea3206
     */

    private String name;
    private String phone;
    private String company;
    private String token;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
