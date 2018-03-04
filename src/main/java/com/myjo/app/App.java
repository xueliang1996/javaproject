package com.myjo.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.myjo.domain.AccessKey;
import com.myjo.domain.Message;
import com.myjo.domain.Oc_Info;
import com.myjo.service.StatusMapperService;

public class App extends TimerTask {

	private int i = 1;
	
	public Boolean getCompareStatus() {
		Boolean flag = true;
		StatusMapperService sms = new StatusMapperService();
		Oc_Info oc = sms.selectStatus();
		System.out.println(oc.getStatus());
		if("FAILURE".equals(oc.getStatus())){
			flag = false;
		}
		return flag;
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Boolean flag = getCompareStatus();
		System.out.println(flag);
		try {
			String rootPath = System.getProperty("user.dir").replace("\\", "/");
			FileInputStream fs = new FileInputStream(rootPath+"/conf.properties");
			Properties pro = new Properties();
			pro.load(new InputStreamReader(fs, "utf-8"));//直接用pro.load(fs)会出现输出内容中文乱码
			AccessKey ak = new AccessKey(pro.getProperty("accessKeyId"),pro.getProperty("accessKeySecret"));
			System.out.println(ak.getAccessKeyId()+":"+ak.getAccessKeySecret());
			fs.close();
			//System.out.println(flag);
			if(flag == false) {
				//设置超时时间-可自行调整
				System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
				System.setProperty("sun.net.client.defaultReadTimeout", "30000");
				//初始化ascClient需要的几个参数
				final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
				final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
				//替换成你的AK
				final String accessKeyId = ak.getAccessKeyId();//你的accessKeyId,参考本文档步骤2
				final String accessKeySecret = ak.getAccessKeySecret();//你的accessKeySecret，参考本文档步骤2
				//初始化ascClient,暂时不支持多region（请勿修改）
				IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
				accessKeySecret);
				try {
					DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
					IAcsClient acsClient = new DefaultAcsClient(profile);
					//组装请求对象
					SendSmsRequest request = new SendSmsRequest();
					//使用post提交
					request.setMethod(MethodType.POST);
					//必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,
					//验证码类型的短信推荐使用单条调用的方式
					Message msg = new Message(pro.getProperty("PhoneNumbers"),pro.getProperty("SignName"),pro.getProperty("TemplateCode"));
					
					request.setPhoneNumbers(msg.getPhoneNumbers());
					//必填:短信签名-可在短信控制台中找到
					request.setSignName(msg.getSignName());
					//必填:短信模板-可在短信控制台中找到
					request.setTemplateCode(msg.getTemplateCode());
					//可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
					//友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
					//request.setTemplateParam(msg.getTemplateParam());
					//请求失败这里会抛ClientException异常
					SendSmsResponse sendSmsResponse;
					sendSmsResponse = acsClient.getAcsResponse(request);
					if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
						//请求成功
							System.out.println("短信发送成功");
						}else {
							System.out.println("短信发送失败");
						}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				System.out.println("同步库存运行成功");
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("这是第"+i+"次执行");
		//i++;
	}
	
	public static void main(String args[]) throws ClientException {
		Timer timer = new Timer();
		timer.schedule(new App(),500,1000*60*10);
	}

}
