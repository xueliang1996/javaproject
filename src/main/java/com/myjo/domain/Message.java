package com.myjo.domain;

public class Message {

	private String PhoneNumbers;//短信接收号码
	private String SignName;//短信签名
	private String TemplateCode;//短信模板id

	public Message() {
		// TODO Auto-generated constructor stub
	}

	public Message(String phoneNumbers, String signName, String templateCode) {
		super();
		PhoneNumbers = phoneNumbers;
		SignName = signName;
		TemplateCode = templateCode;
		
	}

	public String getPhoneNumbers() {
		return PhoneNumbers;
	}

	public void setPhoneNumbers(String phoneNumbers) {
		PhoneNumbers = phoneNumbers;
	}

	public String getSignName() {
		return SignName;
	}

	public void setSignName(String signName) {
		SignName = signName;
	}

	public String getTemplateCode() {
		return TemplateCode;
	}

	public void setTemplateCode(String templateCode) {
		TemplateCode = templateCode;
	}


	@Override
	public String toString() {
		return "Message [PhoneNumbers=" + PhoneNumbers + ", SignName=" + SignName + ", TemplateCode=" + TemplateCode
				+  "]";
	}

	
}
