package cn.facesignin.constant;

import java.util.ResourceBundle;

public class OpencvConfig {
	public static final String CLASSIFIER;
	
	static{
		ResourceBundle bundle = ResourceBundle.getBundle("base");
		CLASSIFIER = bundle.getString("classifier");
	}
	
}
