package cn.facesignin.constant;

import java.util.ResourceBundle;

public class ImgFilePathConfig {
	public static final String ROOT;
	public static final String REGISTER;
	public static final String VERIFY;
	public static final String TEMP_PATH;
	
	static{
		ResourceBundle bundle = ResourceBundle.getBundle("base");
		ROOT = bundle.getString("root");
		REGISTER = bundle.getString("register");
		VERIFY = bundle.getString("verify");
		TEMP_PATH = bundle.getString("temp_path");
	}
}
