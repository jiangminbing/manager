package com.soft.manager.po;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private Integer messageId;

    private String messageTitle;

    private String messageShortContext;

    private String url;

    private Date pushTime;

    private Byte push;

    private Byte pushNow;

    private Byte isAll;

    private Date createTime;

    private Date updateTime;

    private Byte delState;

    private Integer adminId;

    private static final long serialVersionUID = 1L;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle == null ? null : messageTitle.trim();
    }

    public String getMessageShortContext() {
        return messageShortContext;
    }

    public void setMessageShortContext(String messageShortContext) {
        this.messageShortContext = messageShortContext == null ? null : messageShortContext.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public Byte getPush() {
        return push;
    }

    public void setPush(Byte push) {
        this.push = push;
    }

    public Byte getPushNow() {
        return pushNow;
    }

    public void setPushNow(Byte pushNow) {
        this.pushNow = pushNow;
    }

    public Byte getIsAll() {
        return isAll;
    }

    public void setIsAll(Byte isAll) {
        this.isAll = isAll;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getDelState() {
        return delState;
    }

    public void setDelState(Byte delState) {
        this.delState = delState;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }
}