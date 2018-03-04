package com.myjo.domain;

public class AccessKey {

	private String accessKeyId;
	private String accessKeySecret;
	public AccessKey() {
		// TODO Auto-generated constructor stub
	}
	public AccessKey(String accessKeyId, String accessKeySecret) {
		super();
		this.accessKeyId = accessKeyId;
		this.accessKeySecret = accessKeySecret;
	}
	public String getAccessKeyId() {
		return accessKeyId;
	}
	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}
	public String getAccessKeySecret() {
		return accessKeySecret;
	}
	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}
	@Override
	public String toString() {
		return "AccessKey [accessKeyId=" + accessKeyId + ", accessKeySecret=" + accessKeySecret + "]";
	}

	
	
}
