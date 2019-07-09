package cn.facesignin.thread;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.servlet.http.HttpSession;

import org.opencv.core.Rect;

import cn.facesignin.constant.ImgFilePathConfig;
import cn.facesignin.pojo.User;
import cn.facesignin.service.VerifyService;
import cn.facesignin.utils.OpencvUtils;

public class Verify implements Runnable{
	
	private File imgFile;
	private Integer aid;
	private HttpSession session;

	private VerifyService verifyService;
	
	private OpencvUtils opencvUtils = new OpencvUtils();
	
	public Verify(File imgFile, Integer aid, HttpSession session, VerifyService verifyService) {
		this.imgFile = imgFile;
		this.aid = aid;
		this.session = session;
		this.verifyService = verifyService;
	}
	
	@Override
	public void run() {
		
		List<User> userList = (List<User>) session.getAttribute("userList");
		
		List<Rect> rects = opencvUtils.getFaceRects(imgFile.getAbsolutePath());
		
		System.out.println("rectsNum  ==>   " + rects.size());
		
		OpencvUtils.enlargeRects(imgFile.getAbsolutePath(), rects, 10);
		
		Iterator<Rect> iterator = rects.iterator();
		
		while(iterator.hasNext()) {
			Rect rect = iterator.next();
			
			System.out.println(rect);
			
			File imageCut = opencvUtils.imageCut(imgFile.getAbsolutePath(), 
					ImgFilePathConfig.ROOT + File.separator + ImgFilePathConfig.VERIFY + File.separator + aid + File.separator + UUID.randomUUID() +".jpg", 
					rect);
			
			File imageMark = opencvUtils.imageMark(imgFile.getAbsolutePath(), 
					ImgFilePathConfig.ROOT + File.separator + ImgFilePathConfig.VERIFY + File.separator + aid + File.separator + UUID.randomUUID() +".jpg", 
					rect);
			
			List<User> users = verifyService.verify(imageCut, userList);
			verifyService.userSignInDB(users, aid);
			verifyService.saveFile(imageMark, users, aid);
			
			imageCut.delete();
			imageMark.delete();
			
		}
		//imgFile.delete();
	}
}