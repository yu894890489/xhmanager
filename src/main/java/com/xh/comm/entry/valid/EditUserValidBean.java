package com.xh.comm.entry.valid;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.xh.comm.entry.pub.BaseBean;

public class EditUserValidBean extends BaseBean{
	@NotBlank(message = "{login.userId.notblank}")
	private String userId;

    /**
	 * 用户名
	 */
	@NotBlank(message = "{login.username.notblank}")
	private String username;
    private Long phone;
    
    @Email
    private String email;

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
    
    

}
