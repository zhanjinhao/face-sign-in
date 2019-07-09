package cn.facesignin.face;

import java.util.HashMap;

import cn.facesignin.constant.FaceppConfig;
import cn.facesignin.face.BaseFaceppUtils;
import cn.facesignin.face.FaceppOriginalResult;
import cn.facesignin.utils.FaceppConfigMap;

public class FaceppOriginalResult {

	private BaseFaceppUtils baseFaceppUtils = new BaseFaceppUtils();

	/**
	 * 检测人脸获得face_token和人眼信息
	 * @param imgFileBase64
	 * @return
	 */
	public String detectImgByBase64(String imgFileBase64) {
		HashMap<String, String> map = FaceppConfigMap.getInstance();
		map.put("image_base64", imgFileBase64);
		map.put("return_attributes", "eyestatus");
		byte[] post = null;
		try {
			post = baseFaceppUtils.post(FaceppConfig.API_DETECT, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(post);
	}
	
	/**
	 * 创建Faceset
	 * @param outer_id
	 */
	public String createFaceset(String outer_id) {
		HashMap<String, String> map = FaceppConfigMap.getInstance();
		map.put("display_name", outer_id);
		map.put("outer_id", outer_id);
		byte[] post = null;
		try {
			post = baseFaceppUtils.post(FaceppConfig.API_CREATE_FACESET, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(post);
	}
	
	/**
	 * 向faseset中插入facetoken
	 * @param facesetToken
	 * @param faceToken
	 */
	public String addFace(String facesetToken, String faceToken) {
		HashMap<String, String> map = FaceppConfigMap.getInstance();
		map.put("faceset_token", facesetToken);
		map.put("face_tokens", faceToken);
		byte[] post = null;
		try {
			post = baseFaceppUtils.post(FaceppConfig.API_FACESET_ADDFACE, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(post);
	}
	
	/**
	 * 从faseset中移出facetoken
	 * @param facesetToken
	 * @param faceToken
	 */
	public String removeFace(String facesetToken, String faceToken) {
		HashMap<String, String> map = FaceppConfigMap.getInstance();
		map.put("faceset_token", facesetToken);
		map.put("face_tokens", faceToken);
		byte[] post = null;
		try {
			post = baseFaceppUtils.post(FaceppConfig.API_FACESET_REMOVE, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(post);
	}
	
	/**
	 * 
	 * @param facesetToken
	 * @param faceToken
	 */
	public String search(String facesetToken, String faceToken) {
		HashMap<String, String> map = FaceppConfigMap.getInstance();
		map.put("face_token", faceToken);
		map.put("faceset_token", facesetToken);
		byte[] post = null;
		try {
			post = baseFaceppUtils.post(FaceppConfig.API_SEARCH, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(post);
	}
	
}
