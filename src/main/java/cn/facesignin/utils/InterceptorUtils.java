package cn.facesignin.utils;

import cn.facesignin.constant.AccessControlConfig;

public class InterceptorUtils {
	
	public static boolean isReleaseDirect(String uri) {
		int size = AccessControlConfig.LIST.size();
		
		for(int i = 0; i < size; i++) {
			if(uri.contains(AccessControlConfig.LIST.get(i)))
				return true;
		}
		return false;
	}
}
