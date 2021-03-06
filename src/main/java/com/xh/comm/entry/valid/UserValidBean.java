package com.xh.comm.entry.valid;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.xh.comm.entry.pub.BaseBean;
/**
 * validBean
 * @author yuq
 * @date 2017年8月9日
 */
public class UserValidBean extends BaseBean{
    private String userId;

    /**
	 * 用户名
	 */
	@NotBlank(message = "{login.username.notblank}")
	private String username;

	/**
	 * 密码
	 */
	@NotBlank(message = "{login.password.notblank}")
	private String password;

    private String code;

    private Long phone;

    private String email;

    private String creater;

    private Date createTime;

    private String updater;

    private Date updateTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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
}