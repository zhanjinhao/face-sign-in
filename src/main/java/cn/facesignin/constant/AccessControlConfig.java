package cn.facesignin.constant;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AccessControlConfig {
	public static final List<String> LIST;
	static{
		LIST = new ArrayList<>();
		ResourceBundle bundle = ResourceBundle.getBundle("base");
		String[] split = bundle.getString("accessConfig").split(",");
		int len = split.length;
		for(int i = 0; i < len; i++)
			LIST.add(split[i]);
	}
}
