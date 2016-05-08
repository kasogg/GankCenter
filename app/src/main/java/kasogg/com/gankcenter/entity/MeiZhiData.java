package kasogg.com.gankcenter.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by KasoGG on 2016/5/8.
 */
public class MeiZhiData {

    /**
     * _id : 572c146a67765974f885c01e
     * createdAt : 2016-05-06T11:50:02.319Z
     * desc : 无版权
     * publishedAt : 2016-05-06T12:04:47.203Z
     * source : chrome
     * type : 福利
     * url : http://ww4.sinaimg.cn/large/610dc034jw1f3litmfts1j20qo0hsac7.jpg
     * used : true
     * who : 代码家
     */

    @SerializedName("_id")
    public String id;
    @SerializedName("createdAt")
    public String createdAt;
    @SerializedName("desc")
    public String desc;
    @SerializedName("publishedAt")
    public String publishedAt;
    @SerializedName("source")
    public String source;
    @SerializedName("type")
    public String type;
    @SerializedName("url")
    public String url;
    @SerializedName("used")
    public boolean used;
    @SerializedName("who")
    public String who;
}
