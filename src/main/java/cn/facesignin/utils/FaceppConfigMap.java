package cn.facesignin.utils;

import java.util.HashMap;

import cn.facesignin.constant.FaceppConfig;

public class FaceppConfigMap extends HashMap<String, String>{
	private static final long serialVersionUID = 1L;
	/**
	 * 获得一个含有api_key和api_secret的map
	 * @return HashMap
	 */
	public static HashMap<String, String> getInstance() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("api_key", FaceppConfig.KEY);
		map.put("api_secret", FaceppConfig.SECRET);
		return map;
	}
}
