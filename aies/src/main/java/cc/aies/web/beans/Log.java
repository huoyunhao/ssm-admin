package cc.aies.web.beans;

import java.util.Date;

public class Log {
    private String logId;

    private String logValue;

    private Date createTime;

    private String optionPersion;

    private String url;

    private String userAgent;

    private String gets;

    private String posts;

    private String ip;

    private Date updateTime;

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId == null ? null : logId.trim();
    }

    public String getLogValue() {
        return logValue;
    }

    public void setLogValue(String logValue) {
        this.logValue = logValue == null ? null : logValue.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOptionPersion() {
        return optionPersion;
    }

    public void setOptionPersion(String optionPersion) {
        this.optionPersion = optionPersion == null ? null : optionPersion.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent == null ? null : userAgent.trim();
    }

    public String getGets() {
        return gets;
    }

    public void setGets(String gets) {
        this.gets = gets == null ? null : gets.trim();
    }

    public String getPosts() {
        return posts;
    }

    public void setPosts(String posts) {
        this.posts = posts == null ? null : posts.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Log{" +
                "logId='" + logId + '\'' +
                ", logValue='" + logValue + '\'' +
                ", createTime=" + createTime +
                ", optionPersion='" + optionPersion + '\'' +
                ", url='" + url + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", gets='" + gets + '\'' +
                ", posts='" + posts + '\'' +
                ", ip='" + ip + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}