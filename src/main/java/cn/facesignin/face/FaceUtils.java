package cn.facesignin.face;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.facesignin.constant.FaceppConfig;
import cn.facesignin.face.FaceUtils;
import cn.facesignin.face.FaceppOriginalResult;
import cn.facesignin.utils.ImgFileBase64Utils;

public class FaceUtils{
	
	private FaceppOriginalResult fppor = new FaceppOriginalResult();
	
	/**
	 * 使用Facepp获得图片中的人脸数量
	 * @param imgFileBase64
	 */
	public Integer getFaceNumWithFacepp(String imgFileBase64) {
		String result = fppor.detectImgByBase64(imgFileBase64);
		JSONObject jso = JSON.parseObject(result);
		JSONArray jsarr=jso.getJSONArray("faces");
		return jsarr.size();
	}
	
	/**
	 * 获得图片中的face_token集合
	 * @param imgFileBase64
	 */
	public ArrayList<String> getFaceTokensByBase64(String imgFileBase64){
		String result = fppor.detectImgByBase64(imgFileBase64);
		JSONObject jso = JSON.parseObject(result);
		JSONArray jsarr=jso.getJSONArray("faces");
        int size = jsarr.size();
        ArrayList<String> faces = new ArrayList<>();
        for(int i = 0; i < size; i++){
        	JSONObject ao=jsarr.getJSONObject(i);
        	faces.add(ao.getString("face_token"));
        }
        return faces.size()>0? faces:null;
	}

	
	/**
	 * 获得图片中的face_token集合
	 * @param file
	 */
	public ArrayList<String> getFaceTokensByFile(File file){
		
		String imgFileBase64 = ImgFileBase64Utils.encodeImgFileToBase64(file);
		
		String result = fppor.detectImgByBase64(imgFileBase64);
		JSONObject jso = JSON.parseObject(result);
		JSONArray jsarr=jso.getJSONArray("faces");
        int size = jsarr.size();
        ArrayList<String> faces = new ArrayList<>();
        for(int i = 0; i < size; i++){
        	JSONObject ao=jsarr.getJSONObject(i);
        	faces.add(ao.getString("face_token"));
        }
        return faces.size()>0? faces:null;
	}
	
	
	/**
	 * 获得图片中的face_token集合，如果有多个，默认是人脸面积最大的那个
	 * @param path
	 */
	public String getFaceTokenByBase64(String path){
		return getFaceTokensByBase64(path)!=null?getFaceTokensByBase64(path).get(0):null;
	}
	
	public String getFacesetToken(String outer_id){
		String result = fppor.createFaceset(outer_id);
		JSONObject jso = JSON.parseObject(result);
		return null;
	}
	
	/**
	 * 
	 * @param facesetToken
	 * @param faceToken
	 * @return
	 */
	public Boolean addFace(String facesetToken, String faceToken) {
		String addFace = fppor.addFace(facesetToken, faceToken);
		JSONObject jso = JSON.parseObject(addFace);
		JSONArray jsarr=jso.getJSONArray("error_message");
		System.err.println(addFace);
		if(jsarr == null) {
			return true;
		}
		return false;
	}
	
	/**
	 * @param facesetToken
	 * @param faceToken
	 * @return
	 */
	public Boolean removeFace(String facesetToken, String faceToken) {
		String removeFace = fppor.removeFace(facesetToken, faceToken);
		JSONObject jso = JSON.parseObject(removeFace);
		JSONArray jsarr=jso.getJSONArray("error_message");
		System.err.println(removeFace);
		if(jsarr == null) {
			return true;
		}
		return false;
	}
	
	/**
	 * 从faceToken中搜索最相近的一张人脸，如果最相近的人脸置信度小于设置的阈值（FaceppConfig.CONFIDENCE），返回null
	 * @param facesetToken
	 * @param faceToken
	 */
	public String searchFaceToken(String facesetToken, String faceToken) {
		String search = fppor.search(facesetToken, faceToken);
		JSONObject jso = JSON.parseObject(search);
		JSONArray errorMessage=jso.getJSONArray("error_message");
		if(errorMessage != null)
			return null;
		JSONArray result = jso.getJSONArray("results");
		JSONObject jsonObject = (JSONObject) result.get(0);
		
		//置信度
		BigDecimal confidence = (BigDecimal) jsonObject.get("confidence");
		Double doubleValue = confidence.doubleValue();
		
		//匹配出来的faceToken
		String face_token = (String)jsonObject.get("face_token");
		System.out.println("confidence  ==>  " + doubleValue + "    " + face_token);
		if(doubleValue > FaceppConfig.CONFIDENCE)
			return face_token;
		return null;
	}
	
}