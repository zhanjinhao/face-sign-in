package cn.facesignin.constant;

import java.util.ResourceBundle;

public class ServerConfig {
	static{
		ResourceBundle bundle = ResourceBundle.getBundle("base");
		PROTOCOL = bundle.getString("protocol");
		SERVER_IP = bundle.getString("serverIp");
		PORT = bundle.getString("port");
	}
	
	// 发件人地址
	public static final String PROTOCOL;
	
	// 发件人账户名
	public static final String SERVER_IP;
	
	// 发件人账户密码
	public static final String PORT;
}
