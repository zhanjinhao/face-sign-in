package cn.facesignin.constant;

import java.util.ResourceBundle;

public class ThreadPoolConfig {
	
	static{
		ResourceBundle bundle = ResourceBundle.getBundle("base");
		EMAIL = Integer.valueOf(bundle.getString("emailpool"));
		VERIFY = Integer.valueOf(bundle.getString("verifypool"));
	}
	
	// 发件人地址
	public static final Integer EMAIL;
	
	// 发件人账户名
	public static final Integer VERIFY;
	
}
