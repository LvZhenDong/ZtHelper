package com.egr.drillinghelper.bean.response;

import com.google.gson.annotations.SerializedName;

/**
 * author lzd
 * date 2017/10/9 18:17
 * 类描述：
 */

public class KnowCatalog {

    /**
     * id : 5ea54bcd9dad474fb6e0de14e1f45b56
     * createtime : 2017-10-09 17:33:45
     * updatetime : 2017-10-09 17:33:45
     * title : 1.EP600的技术参数？
     * content : <pclass="MsoNormal">答案：</p><pclass="MsoNormal"style="margin-left:21pt;text-indent:-21pt;"><!--[if!supportLists]-->1)&nbsp;<!--[endif]--><span>动力单元：</span>3台原装进口久保田涡轮增压柴油发动机，单台功率23.5kw/3000rpm</p><pclass="MsoNormal"style="margin-left:21pt;text-indent:-21pt;"><!--[if!supportLists]-->2)&nbsp;<!--[endif]--><span>给进梁单元</span>:桅杆式结构，无钻塔设计，<span>总高</span>5m<span>，可</span>45°~90°调节钻进角度；</p><pclass="MsoNormal"style="margin-left:21pt;text-indent:-21pt;"><!--[if!supportLists]-->3)&nbsp;<!--[endif]--><span>液压系统单元：最高压力</span>21MPa,最大流量160L/min；冷却方式可选风冷与水冷；</p><pclass="MsoNormal"style="margin-left:21pt;text-indent:-21pt;"><!--[if!supportLists]-->4)&nbsp;<!--[endif]--><span>旋转单元：顶驱式动力头，最大扭矩</span>650NM，最大转速1300rpm,行程1.8m，进给力30KN,最大提升力可达100KN（50KN*2）</p><pclass="MsoNormal"style="margin-left:21pt;text-indent:-21pt;"><!--[if!supportLists]-->5)&nbsp;<!--[endif]--><span>绳索取芯打捞绞车：最大提升力</span>7KN，最大提升速度150m/min，钢丝绳直径5mm，满鼓钢丝绳长度650m</p><pclass="MsoNormal"style="margin-left:21pt;text-indent:-21pt;"><!--[if!supportLists]-->6)&nbsp;<!--[endif]-->泥浆系统单元：泥浆泵<span>最大流量</span>75L/min，最高压力7MPa（1015psi）；泥浆池容量1吨</p><pclass="MsoNormal"style="margin-left:21pt;text-indent:-21pt;"><!--[if!supportLists]-->7)&nbsp;<!--[endif]--><span>钻机能源单元</span>:90L铝制柴油箱；2个85AH免维护大电瓶，电压12V；</p><pclass="MsoNormal"style="margin-left:21pt;text-indent:-21pt;"><!--[if!supportLists]-->8)&nbsp;<!--[endif]--><span>钻机重量参数：整机</span>1.2吨</p>
     * knowId : 74db3610acdd43cba0306fd4dbc63741
     */

    private String id;
    @SerializedName("createtime")
    private String createTime;
    @SerializedName("updatetime")
    private String updateTime;
    private String title;
    private String content;
    private String knowId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getKnowId() {
        return knowId;
    }

    public void setKnowId(String knowId) {
        this.knowId = knowId;
    }
}
