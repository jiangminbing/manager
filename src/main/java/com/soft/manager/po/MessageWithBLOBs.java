package com.soft.manager.po;

import java.io.Serializable;

public class MessageWithBLOBs extends Message implements Serializable {
    private String messageContext;

    private String userIds;

    private static final long serialVersionUID = 1L;

    public String getMessageContext() {
        return messageContext;
    }

    public void setMessageContext(String messageContext) {
        this.messageContext = messageContext == null ? null : messageContext.trim();
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds == null ? null : userIds.trim();
    }
}