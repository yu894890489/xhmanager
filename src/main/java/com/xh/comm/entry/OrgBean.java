package com.xh.comm.entry;

import java.util.Date;
import java.util.List;

public class OrgBean {
    private String orgId;

    private String orgName;

    private String orgParentId;

    private String orgCode;

    private String orgImg;

    private String orgWebsite;

    private String orgEmail;

    private String orgPhone;

    private String orgProvId;

    private String orgCityId;

    private String orgAreaId;

    private String orgDesc;

    private Integer sortKey;

    private String recordState;

    private String creater;

    private Date createTime;

    private String updater;

    private Date updateTime;

    private String isDelete;

    private String orgAddress;

    private String modifyUserId;

    private Date modifyTime;
    
    private List<OrgBean> children;
    
    

    public List<OrgBean> getChildren() {
		return children;
	}

	public void setChildren(List<OrgBean> children) {
		this.children = children;
	}

	public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getOrgParentId() {
        return orgParentId;
    }

    public void setOrgParentId(String orgParentId) {
        this.orgParentId = orgParentId == null ? null : orgParentId.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getOrgImg() {
        return orgImg;
    }

    public void setOrgImg(String orgImg) {
        this.orgImg = orgImg == null ? null : orgImg.trim();
    }

    public String getOrgWebsite() {
        return orgWebsite;
    }

    public void setOrgWebsite(String orgWebsite) {
        this.orgWebsite = orgWebsite == null ? null : orgWebsite.trim();
    }

    public String getOrgEmail() {
        return orgEmail;
    }

    public void setOrgEmail(String orgEmail) {
        this.orgEmail = orgEmail == null ? null : orgEmail.trim();
    }

    public String getOrgPhone() {
        return orgPhone;
    }

    public void setOrgPhone(String orgPhone) {
        this.orgPhone = orgPhone == null ? null : orgPhone.trim();
    }

    public String getOrgProvId() {
        return orgProvId;
    }

    public void setOrgProvId(String orgProvId) {
        this.orgProvId = orgProvId == null ? null : orgProvId.trim();
    }

    public String getOrgCityId() {
        return orgCityId;
    }

    public void setOrgCityId(String orgCityId) {
        this.orgCityId = orgCityId == null ? null : orgCityId.trim();
    }

    public String getOrgAreaId() {
        return orgAreaId;
    }

    public void setOrgAreaId(String orgAreaId) {
        this.orgAreaId = orgAreaId == null ? null : orgAreaId.trim();
    }

    public String getOrgDesc() {
        return orgDesc;
    }

    public void setOrgDesc(String orgDesc) {
        this.orgDesc = orgDesc == null ? null : orgDesc.trim();
    }

    public Integer getSortKey() {
        return sortKey;
    }

    public void setSortKey(Integer sortKey) {
        this.sortKey = sortKey;
    }

    public String getRecordState() {
        return recordState;
    }

    public void setRecordState(String recordState) {
        this.recordState = recordState == null ? null : recordState.trim();
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress == null ? null : orgAddress.trim();
    }

    public String getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(String modifyUserId) {
        this.modifyUserId = modifyUserId == null ? null : modifyUserId.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}