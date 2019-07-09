package cn.facesignin.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.facesignin.constant.FaceppConfig;
import cn.facesignin.constant.ImgFilePathConfig;
import cn.facesignin.constant.Type;
import cn.facesignin.face.FaceUtils;
import cn.facesignin.mapper.ActivityMapper;
import cn.facesignin.mapper.SigninRecordMapper;
import cn.facesignin.pojo.Activity;
import cn.facesignin.pojo.SigninRecord;
import cn.facesignin.pojo.SigninRecordExample;
import cn.facesignin.pojo.SigninRecordExample.Criteria;
import cn.facesignin.pojo.User;
import cn.facesignin.service.ActivityService;
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
	private ActivityService activityService;
	
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
	public void userSignInDB(List<User> users, Integer aid) {
		Iterator<User> iterator = users.iterator();
		
		Activity activity = activityService.getActivityByAid(aid);
		System.out.println(activity);
		
		Date aendTime = activity.getAendTime();
		Date now = new Date(System.currentTimeMillis());
		//to > 0：当前时间比结束时间大，签到状态设置为迟到
		//to < 0：当前时间比结束时间小，签到状态设置为成功
		int to = now.compareTo(aendTime);  
		
		while(iterator.hasNext()) {
			User user = iterator.next();
			System.out.println(user);
			//select by uid and aid
			System.out.println("VerifyServiceImpl.userSignInDB  ==>  " + user.getUid() + "   " + aid);
			
			SigninRecord record = signInRecordService.getRecordByAidAndUid(user.getUid(), aid);
			
			if(record == null) {
				SigninRecord recordNow = new SigninRecord();
				recordNow.setAid(aid);
				recordNow.setSinTime(new Date());
				recordNow.setSoutTime(new Date());
				recordNow.setScheckType(Type.SIGNIN_RECORD_SCHECK_TYPE_IMG);
				
				if(to > 0)
					recordNow.setSstatus(Type.SIGNIN_RECORD_SSTATUS_LATE);
				else
					recordNow.setSstatus(Type.SIGNIN_RECORD_SSTATUS_NORMAL);
				
				recordNow.setUid(user.getUid());
				recordNow.setSimgPath(user.getUid() + ".jpg");
				signinRecordMapper.insert(recordNow);
			}else {
				SigninRecordExample example = new SigninRecordExample();
				Criteria criteria = example.createCriteria();
				criteria.andAidEqualTo(aid);
				criteria.andUidEqualTo(user.getUid());
				record.setSoutTime(new Date());
				signinRecordMapper.updateByExample(record, example);
			}
		}
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
			System.out.println("path  ==>  " + path);
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
	
}
