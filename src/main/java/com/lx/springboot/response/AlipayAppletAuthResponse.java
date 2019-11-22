package com.lx.springboot.response;

import java.io.Serializable;

public class AlipayAppletAuthResponse implements Serializable {

	private static final long serialVersionUID = -4947171788237695103L;

	//是否成功
	public boolean isSuccess;

	//支付宝用户的唯一userId
	private String  userId;

	//访问令牌
	private String  accessToken;

	//访问令牌的有效时间
	private String  expiresIn;

	//刷新令牌
	private String  refreshToken;

	//刷新令牌的有效时间
	private String  reExpiresIn;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean success) {
		isSuccess = success;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getReExpiresIn() {
		return reExpiresIn;
	}

	public void setReExpiresIn(String reExpiresIn) {
		this.reExpiresIn = reExpiresIn;
	}

	@Override
	public String toString() {
		return "AlipayAppletAuthResponse{" +
				"isSuccess=" + isSuccess +
				", userId='" + userId + '\'' +
				", accessToken='" + accessToken + '\'' +
				", expiresIn='" + expiresIn + '\'' +
				", refreshToken='" + refreshToken + '\'' +
				", reExpiresIn='" + reExpiresIn + '\'' +
				'}';
	}
}
