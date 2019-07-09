package cn.facesignin.constant;

import java.util.ResourceBundle;

public class FaceppConfig {
	
	static{
		ResourceBundle bundle = ResourceBundle.getBundle("facepp");
		KEY = bundle.getString("key");
		SECRET = bundle.getString("secret");
		API_DETECT = bundle.getString("detect_api");
		API_CREATE_FACESET = bundle.getString("create_faceset");
		API_FACESET_ADDFACE = bundle.getString("faceset_addface");
		FACESET_NAME = bundle.getString("faceset");
		API_SEARCH = bundle.getString("search");
		API_FACESET_REMOVE = bundle.getString("faceset_remove");
	}
	
	public static final String KEY;
	public static final String SECRET;
	
	public static final String FACESET_NAME;
	
	public static final String API_DETECT;
	public static final String API_CREATE_FACESET;
	public static final String API_FACESET_ADDFACE;
	public static final String API_FACESET_REMOVE;
	public static final String API_SEARCH;
	
	public static final Double CONFIDENCE = 66.0d;
}
