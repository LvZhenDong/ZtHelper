package com.kklv.fragme.bean.response;

import java.io.Serializable;

/**
 * author 边凌
 * date 2017/6/15 10:55
 * 类描述：用户（住户，业主端） 实体
 */

public class Resident implements Serializable {


    /**
     * id : a770cecb-6b40-4862-a0ba-b6f9239c0e43
     * photo : http://101.201.222.160:8999/jinen/account/default.png
     * thumbnailPhoto : account/default.png
     * name : 15208460574
     * phone : 15208460574
     * account : 15208460574
     * loyaltyPoints : 0
     * token : e2425f87-a6b4-4494-b984-dc96b7d19595
     * hasPayPassword : false
     * house : {"estateId":"86d7e4a3-5571-438f-8ed7-8f3695be3964","estateName":"万科海悦汇城",
     * "houseId":"3731b668-5ccf-4804-9265-0521715867e7","houseName":"万科海悦汇城-4-1-2502",
     * "id":"38578991-2354-4b65-8982-a8c70d95c7be","updateDate":"2017-09-24 21:51:21",
     * "createDate":"2017-09-24 21:51:21"}
     * imUserInfo : {"houseId":"3731b668-5ccf-4804-9265-0521715867e7",
     * "accountId":"a770cecb-6b40-4862-a0ba-b6f9239c0e43",
     * "imId":"3731b668-5ccf-4804-9265-0521715867e7-15208460574",
     * "userName":"3731b668-5ccf-4804-9265-0521715867e7-15208460574","status":true,
     * "id":"b5b8976a-b839-4a99-bc04-2d44bac955fa","updateDate":"2017-09-19 15:27:04","createDate
     * ":"2017-09-19 15:27:04"}
     */

    private String id;
    private String photo;
    private String thumbnailPhoto;
    private String name;
    private String phone;
    private String account;
    private int loyaltyPoints;
    private String token;
    private boolean hasPayPassword;
    private HouseBean house;
    private ImUserInfoBean imUserInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getThumbnailPhoto() {
        return thumbnailPhoto;
    }

    public void setThumbnailPhoto(String thumbnailPhoto) {
        this.thumbnailPhoto = thumbnailPhoto;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isHasPayPassword() {
        return hasPayPassword;
    }

    public void setHasPayPassword(boolean hasPayPassword) {
        this.hasPayPassword = hasPayPassword;
    }

    public HouseBean getHouse() {
        return house;
    }

    public void setHouse(HouseBean house) {
        this.house = house;
    }

    public ImUserInfoBean getImUserInfo() {
        return imUserInfo;
    }

    public void setImUserInfo(ImUserInfoBean imUserInfo) {
        this.imUserInfo = imUserInfo;
    }

    public static class HouseBean {
        /**
         * estateId : 86d7e4a3-5571-438f-8ed7-8f3695be3964
         * estateName : 万科海悦汇城
         * houseId : 3731b668-5ccf-4804-9265-0521715867e7
         * houseName : 万科海悦汇城-4-1-2502
         * id : 38578991-2354-4b65-8982-a8c70d95c7be
         * updateDate : 2017-09-24 21:51:21
         * createDate : 2017-09-24 21:51:21
         */

        private String estateId;
        private String estateName;
        private String houseId;
        private String houseName;
        private String id;
        private String updateDate;
        private String createDate;

        public String getEstateId() {
            return estateId;
        }

        public void setEstateId(String estateId) {
            this.estateId = estateId;
        }

        public String getEstateName() {
            return estateName;
        }

        public void setEstateName(String estateName) {
            this.estateName = estateName;
        }

        public String getHouseId() {
            return houseId;
        }

        public void setHouseId(String houseId) {
            this.houseId = houseId;
        }

        public String getHouseName() {
            return houseName;
        }

        public void setHouseName(String houseName) {
            this.houseName = houseName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }

    public static class ImUserInfoBean {
        /**
         * houseId : 3731b668-5ccf-4804-9265-0521715867e7
         * accountId : a770cecb-6b40-4862-a0ba-b6f9239c0e43
         * imId : 3731b668-5ccf-4804-9265-0521715867e7-15208460574
         * userName : 3731b668-5ccf-4804-9265-0521715867e7-15208460574
         * status : true
         * id : b5b8976a-b839-4a99-bc04-2d44bac955fa
         * updateDate : 2017-09-19 15:27:04
         * createDate : 2017-09-19 15:27:04
         */

        private String houseId;
        private String accountId;
        private String imId;
        private String userName;
        private boolean status;
        private String id;
        private String updateDate;
        private String createDate;

        public String getHouseId() {
            return houseId;
        }

        public void setHouseId(String houseId) {
            this.houseId = houseId;
        }

        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }

        public String getImId() {
            return imId;
        }

        public void setImId(String imId) {
            this.imId = imId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }
}
