package com.kklv.fragme.bean.response;

/**
 * Created by Ymmmsick on 5/18/17.
 */

public class NearbyShop {

    /**
     * shopCode : 1170
     * bizCode : 1082
     */

    private int shopCode;
    private int bizCode;

    public NearbyShop() {
    }

    public NearbyShop(int shopCode) {
        this.shopCode = shopCode;
    }

    public int getShopCode() {
        return shopCode;
    }

    public void setShopCode(int shopCode) {
        this.shopCode = shopCode;
    }

    public int getBizCode() {
        return bizCode;
    }

    public void setBizCode(int bizCode) {
        this.bizCode = bizCode;
    }
}
