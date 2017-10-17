package com.egr.drillinghelper.bean.response;

import java.util.List;

/**
 * author lzd
 * date 2017/9/28 17:19
 * 类描述：
 */

public class ContactUs {

    /**
     * contactList : [{"id":"58d6c5acaa8841b38cbb4a11131ca166","name":"英格尔成都基地西南培训中心西南维修站","address":"中国 四川 成都市温江区海峡工业园","tel":"(+86 28) 8268 2236","createtime":"2017-09-26 16:23:33","updatetime":"2017-09-26 16:23:33"},{"id":"62d6c5acaa8841b38cbb4a11131ca166","name":"英格尔特种钻探设备有限公司总部","address":"中国 广东 珠海金鑫工业园","tel":"(+86 756) 363 5829","email":"service@egrcn.com","website":"http://www.egrcn.com","createtime":"2017-09-26 16:23:33","updatetime":"2017-09-26 16:23:33"},{"id":"777d6cacaa8841b38cbb4a11131ca166","name":"英格尔(香港)有限公司","address":"香港湾仔皇后大道东213号胡忠大厦22楼2209室","tel":"（+852）3973 7633","email":"service@egrcn.com","createtime":"2017-09-26 16:23:33","updatetime":"2017-09-26 16:23:33"}]
     * aboutUs : {"salesTel":"139 0980 9229","serviceTel":"400-878-2638","createtime":"2017-09-26 16:23:33","updatetime":"2017-09-26 16:23:33"}
     */

    private AboutUsBean aboutUs;
    private List<ContactListBean> contactList;

    public AboutUsBean getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(AboutUsBean aboutUs) {
        this.aboutUs = aboutUs;
    }

    public List<ContactListBean> getContactList() {
        return contactList;
    }

    public void setContactList(List<ContactListBean> contactList) {
        this.contactList = contactList;
    }

    public static class AboutUsBean {
        /**
         * salesTel : 139 0980 9229
         * serviceTel : 400-878-2638
         * createtime : 2017-09-26 16:23:33
         * updatetime : 2017-09-26 16:23:33
         */

        private String qrcode;
        private String salesTel;
        private String serviceTel;
        private String createtime;
        private String updatetime;

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }

        public String getSalesTel() {
            return salesTel;
        }

        public void setSalesTel(String salesTel) {
            this.salesTel = salesTel;
        }

        public String getServiceTel() {
            return serviceTel;
        }

        public void setServiceTel(String serviceTel) {
            this.serviceTel = serviceTel;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }
    }

    public static class ContactListBean {
        /**
         * id : 58d6c5acaa8841b38cbb4a11131ca166
         * name : 英格尔成都基地西南培训中心西南维修站
         * address : 中国 四川 成都市温江区海峡工业园
         * tel : (+86 28) 8268 2236
         * createtime : 2017-09-26 16:23:33
         * updatetime : 2017-09-26 16:23:33
         * email : service@egrcn.com
         * website : http://www.egrcn.com
         */

        private String id;
        private String name;
        private String address;
        private String tel;
        private String createtime;
        private String updatetime;
        private String email;
        private String website;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }
    }
}
