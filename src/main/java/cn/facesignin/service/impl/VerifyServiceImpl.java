package cn.facesignin.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

import cn.facesignin.constant.FaceppConfig;
import cn.facesignin.constant.ImgFilePathConfig;
import cn.facesignin.constant.Type;
import cn.facesignin.face.FaceUtils;
import cn.facesignin.mapper.SigninRecordMapper;
import cn.facesignin.pojo.Activity;
import cn.facesignin.pojo.SigninRecord;
import cn.facesignin.pojo.SigninRecordExample;
import cn.facesignin.pojo.SigninRecordExample.Criteria;
import cn.facesignin.pojo.User;
import cn.facesignin.service.SignInRecordService;
import cn.facesignin.service.VerifyService;
import cn.facesignin.utils.FileUtils;

@Service
public class VerifyServiceImpl implements VerifyService {

	private FaceUtils faceUtils = new FaceUtils();

	@Autowired
	private SigninRecordMapper signinRecordMapper;
	
	@Autowired
	private SignInRecordService signInRecordService;
	
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	
	@Override
	/**
	 * 暂时保存图片
	 */
	public File tempSaveFile(MultipartFile file, Integer aid) {
		
		//保存位置  ==>  ROOT/VERIFY/aid/uuid.jpg
		String path = ImgFilePathConfig.ROOT + File.separator + ImgFilePathConfig.VERIFY + 
				File.separator + aid + File.separator + UUID.randomUUID() +".jpg";
		System.out.println("tempPath  ==>  " + path);
		File forceCreateFile = null;
		try {
			forceCreateFile = FileUtils.forceCreateFile(path);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		//向磁盘写文件
		try {
			file.transferTo(forceCreateFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forceCreateFile;
	}

	@Override
	/**
	 * 符合要求的user（置信度大于设定值。不使用组的存在于数据库，使用组的在组中）
	 */
	public List<User> verify(File file, List<User> userList) {
		
		ArrayList<String> faceTokens = faceUtils.getFaceTokensByFile(file);
		System.out.println("VerifyServiceImpl.verify :    faceTokens  ==>  " + faceTokens);
		
		if(faceTokens == null || faceTokens.size() == 0)
			return new ArrayList<>();
		
		Iterator<String> iterator = faceTokens.iterator();
		List<User> list = new ArrayList<>();
		while(iterator.hasNext()) {
			String searchFaceToken = faceUtils.searchFaceToken(FaceppConfig.FACESET_NAME, iterator.next());
			
			System.out.println("searchFaceToken   ==>" + searchFaceToken);
			
			if(searchFaceToken != null) {
				User user = getUserBySearchedFaceToken(searchFaceToken, userList);
				list.add(user);
			}
		}
		
		return list;
	}

	private User getUserBySearchedFaceToken(String faceToken, List<User> userList) {
		Iterator<User> iterator = userList.iterator();
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(faceToken.equals(user.getUfaceToken()))
				return user;
		}
		return null;
	}

	@Override
	/**
	 * 把符合要求的用户写入数据库中存储
	 */
	public void userSignInDB(String admin, List<User> users, Activity activity) {
		
		Integer aid = activity.getAid();
		
		Map<Object, Object> map = null;
		
		String strAid = aid.toString() + "`" + admin;
		
		if(redisTemplate.hasKey(strAid)) {
			map = redisTemplate.opsForHash().entries(strAid);
		} else {
			map = new HashMap<>();
			redisTemplate.opsForHash().putAll(strAid, map);
		}

		Date now = new Date();
		
		Iterator<User> iterator = users.iterator();
		
		while(iterator.hasNext()) {
			User user = iterator.next();
 
			String string = (String)map.get(user.getUid());
			SigninRecord object = JSON.parseObject(string, SigninRecord.class);
			if(map.containsKey(user.getUid())) {

				object.setSoutTime(now);
				redisTemplate.opsForHash().delete(strAid, user.getUid());
				redisTemplate.opsForHash().put(strAid, user.getUid(), JSON.toJSONString(object));

			}else {

				SigninRecord recordNow = new SigninRecord();
				recordNow.setAid(aid);
				recordNow.setSinTime(new Date());
				recordNow.setSoutTime(new Date());
				recordNow.setScheckType(Type.SIGNIN_RECORD_SCHECK_TYPE_IMG);
				//to > 0：当前时间比结束时间大，签到状态设置为迟到
				//to < 0：当前时间比结束时间小，签到状态设置为成功
				Date aendTime = activity.getAendTime();
				int to = now.compareTo(aendTime);
				if(to > 0)
					recordNow.setSstatus(Type.SIGNIN_RECORD_SSTATUS_LATE);
				else
					recordNow.setSstatus(Type.SIGNIN_RECORD_SSTATUS_NORMAL);
				redisTemplate.opsForHash().put(strAid, user.getUid(), JSON.toJSONString(recordNow));
			}

		}

		System.out.println(" =====> " + redisTemplate.opsForHash().entries(strAid));
	}

	@Override
	/**
	 * 把暂时文件复制在相应路径。暂时文件删除
	 * 路径格式：ROOT/VERIFY/aid/uid.jpg
	 */
	public void saveFile(File file, List<User> users, Integer aid) {
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()) {
			User user = iterator.next();
			String path = ImgFilePathConfig.ROOT + File.separator + ImgFilePathConfig.VERIFY + 
					File.separator + aid + File.separator + user.getUid() +".jpg";
			File forceCreateFile = null;
			try {
				forceCreateFile = FileUtils.forceCreateFile(path);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			FileUtils.copyFile(file, forceCreateFile);
		}
		file.delete();
	}

	@Override
	public void saveVerifyRecordToDB(Map<Object, Object> map) {
		
		Set<Entry<Object,Object>> entrySet = map.entrySet();
		Iterator<Entry<Object, Object>> iterator = entrySet.iterator();
		while(iterator.hasNext()) {
			Entry<Object, Object> key = iterator.next();
			String str = (String)map.get(key);
			
			SigninRecord record = JSON.parseObject(str, SigninRecord.class);
			
			SigninRecord recordFromDB = signInRecordService.getRecordByAidAndUid(record.getUid(), record.getAid());
			
			if(recordFromDB == null) {
				signInRecordService.insert(record);
			}else {
				
				SigninRecordExample example = new SigninRecordExample();
				Criteria createCriteria = example.createCriteria();
				createCriteria.andAidEqualTo(record.getAid());
				createCriteria.andUidEqualTo(record.getUid());
				
				signinRecordMapper.updateByExample(record, example);
			}
		}
		
		
	}
	
}
