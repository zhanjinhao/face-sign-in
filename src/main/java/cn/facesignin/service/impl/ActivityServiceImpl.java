package cn.facesignin.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.facesignin.constant.ImgFilePathConfig;
import cn.facesignin.entity.PageResult;
import cn.facesignin.mapper.ActivityMapper;
import cn.facesignin.mapper.SigninRecordMapper;
import cn.facesignin.pojo.Activity;
import cn.facesignin.pojo.ActivityExample;
import cn.facesignin.pojo.ActivityExample.Criteria;
import cn.facesignin.pojo.SigninRecord;
import cn.facesignin.pojo.SigninRecordExample;
import cn.facesignin.service.ActivityService;
import cn.facesignin.service.SignInRecordService;
import cn.facesignin.utils.ExcelUtils;
import cn.facesignin.utils.FileUtils;
import cn.facesignin.utils.ZipUtils;

@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityMapper activityMapper;
	
	@Autowired
	private SigninRecordMapper signinRecordMapper;
	
	@Autowired
	private SignInRecordService signInRecordService;
	
	ZipUtils zipUtils = new ZipUtils();
	
	@Override
	public void createActivity(Activity activity) {
		activityMapper.insert(activity);
	}

	@Override
	public PageResult findPage(int pageNum, int pageSize, int oid) {
		ActivityExample example = new ActivityExample();
		Criteria criteria = example.createCriteria();
		criteria.andOidEqualTo(oid);
		
		PageHelper.startPage(pageNum, pageSize);
		Page<Activity> page = (Page<Activity>)activityMapper.selectByExample(example);
		return new PageResult(page.getTotal(), page.getResult());
	}
	@Override
	public int updateByPrimaryKey(Activity record) {
		return activityMapper.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer aid) {
		return activityMapper.deleteByPrimaryKey(aid);
	}

	@Override
	public int inputInfo(SigninRecord record) {
		return signinRecordMapper.insert(record);
	}

	@Override
	public int deleteSigninRecord(String uid, Integer aid) {
		SigninRecordExample example = new SigninRecordExample();
		cn.facesignin.pojo.SigninRecordExample.Criteria criteria = example.createCriteria();
		criteria.andAidEqualTo(aid);
		criteria.andUidEqualTo(uid);
		List<SigninRecord> selectByExample = signinRecordMapper.selectByExample(example);
		if(selectByExample == null ||selectByExample.size() == 0)
			return -1;
		SigninRecord record = selectByExample.get(0);
		record.setSstatus("2");
		return signinRecordMapper.updateByExample(record, example);
	}
	
	public int resigninRecord(String uid, Integer aid) {
		SigninRecordExample example = new SigninRecordExample();
		cn.facesignin.pojo.SigninRecordExample.Criteria criteria = example.createCriteria();
		criteria.andAidEqualTo(aid);
		criteria.andUidEqualTo(uid);
		List<SigninRecord> selectByExample = signinRecordMapper.selectByExample(example);
		if(selectByExample == null ||selectByExample.size() == 0)
			return -1;
		SigninRecord record = selectByExample.get(0);
		record.setSstatus("1");
		return signinRecordMapper.updateByExample(record, example);
	}

	@Override
	public Activity getActivityByAid(Integer aid) {
		return activityMapper.selectByPrimaryKey(aid);
	}

	@Override
	public String createZipFile(Integer aid) throws Exception {
		List<SigninRecord> list = signInRecordService.selectAllSigninRecordByAid(aid);
		
		System.out.println(list);
		
		new ExcelUtils().createExcel(list, aid);
		//图片的路径
		String folderPath = ImgFilePathConfig.ROOT + File.separator + ImgFilePathConfig.VERIFY 
				+ File.separator + aid;
		
		System.out.println("folderPath  ==>  " + folderPath);
		
		String zipPath = ImgFilePathConfig.ROOT + File.separator + ImgFilePathConfig.TEMP_PATH 
				+ File.separator + aid + ".zip";
		
		System.out.println("zipPath  ==>  " + zipPath);
		
		FileUtils.forceCreateFile(zipPath);
		
		zipUtils.toZip(folderPath, new FileOutputStream(zipPath), true);
		
		return zipPath;
	}

	@Override
	public void deleteTempImg(Integer aid) throws Exception {
		String path = ImgFilePathConfig.ROOT + File.separator + ImgFilePathConfig.VERIFY + File.separator + aid;
		File folder = new File(path);
		if(folder.isDirectory()) {
			File[] files = folder.listFiles();
			for(File e : files) {
				String name = e.getName();
				if(name.length() > 20) {
					e.delete();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		ActivityServiceImpl ac = new ActivityServiceImpl();
		try {
			ac.deleteTempImg(25);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
