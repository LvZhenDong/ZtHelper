package com.egr.drillinghelper.bean.response;

import java.util.List;

/**
 * Created by Administrator on 2017/11/14.
 */

public class StoreDetail extends Store {
    public class Parts{
        String id;
        String no;
        String name;
        String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
    String partsPicture;

    public String getPartsPicture() {
        return partsPicture;
    }

    public void setPartsPicture(String partsPicture) {
        this.partsPicture = partsPicture;
    }

    List<Parts> parts;

    public List<Parts> getParts() {
        return parts;
    }

    public void setParts(List<Parts> parts) {
        this.parts = parts;
    }
}
