package com.soft.manager.po;

import java.io.Serializable;
import java.util.Date;

public class UserPrivilege implements Serializable {
    private Integer privilegeId;

    private Integer userId;

    private Byte isWholesaleprice;

    private Byte isdiscount;

    private Integer discount;

    private Date createTime;

    private Date updateTime;

    private Byte state;

    private Integer adminId;

    private static final long serialVersionUID = 1L;

    public Integer getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Byte getIsWholesaleprice() {
        return isWholesaleprice;
    }

    public void setIsWholesaleprice(Byte isWholesaleprice) {
        this.isWholesaleprice = isWholesaleprice;
    }

    public Byte getIsdiscount() {
        return isdiscount;
    }

    public void setIsdiscount(Byte isdiscount) {
        this.isdiscount = isdiscount;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
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

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }
}