package com.lx.springboot.request;


import java.io.Serializable;

public class AlipayAppletAuthRequest implements Serializable{

	private static final long serialVersionUID = -4947171788237695103L;

	//换码类型
	private String  grant_type;//值为authorization_code时，代表用code换取；值为refresh_token时，代表用refresh_token换取

	//授权码
	private String  code;

	//刷新令牌
	private String  refresh_token;

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	@Override
	public String toString() {
		return "AlipayAppletAuthRequest{" +
				"grant_type='" + grant_type + '\'' +
				", code='" + code + '\'' +
				", refresh_token='" + refresh_token + '\'' +
				'}';
	}
}
