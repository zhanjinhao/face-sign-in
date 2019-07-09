package cn.facesignin.constant;

import java.util.ResourceBundle;

public class MailConfig {
	
	static{
		ResourceBundle bundle = ResourceBundle.getBundle("base");
		SENDER_ADDRESS = bundle.getString("senderAddress");
		SENDER_ACCOUNT = bundle.getString("senderAccount");
		SENDER_PASSWORD = bundle.getString("senderPassword");
	}
	
	// 发件人地址
	public static final String SENDER_ADDRESS;
	
	// 发件人账户名
	public static final String SENDER_ACCOUNT;
	
	// 发件人账户密码
	public static final String SENDER_PASSWORD;
}
