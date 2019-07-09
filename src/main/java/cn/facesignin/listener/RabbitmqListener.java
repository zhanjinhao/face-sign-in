package cn.facesignin.listener;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.opencv.core.Rect;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;

import cn.facesignin.constant.ImgFilePathConfig;
import cn.facesignin.mq.Entity;
import cn.facesignin.pojo.User;
import cn.facesignin.service.VerifyService;
import cn.facesignin.utils.OpencvUtils;

public class RabbitmqListener implements ChannelAwareMessageListener {

	@Autowired
	private VerifyService verifyService;

	private OpencvUtils opencvUtils = new OpencvUtils();

	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		channel.basicQos(10);
		String msg = new String(message.getBody(), "utf-8");
		channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
		System.out.println(msg);

		Entity entity = JSON.parseObject(msg, Entity.class);

		System.out.println(entity);
		
		List<User> userList = entity.getUsers();
		
		String filePath = entity.getFilePath();
		
		Integer aid = entity.getAid();
		
		List<Rect> rects = opencvUtils.getFaceRects(filePath);
		
		System.out.println("rectsNum  ==>   " + rects.size());
		
		OpencvUtils.enlargeRects(filePath, rects, 10);
		
		Iterator<Rect> iterator = rects.iterator();
		
		while(iterator.hasNext()) {
			Rect rect = iterator.next();
			
			System.out.println(rect);
			
			File imageCut = opencvUtils.imageCut(filePath, 
					ImgFilePathConfig.ROOT + File.separator + ImgFilePathConfig.VERIFY + File.separator + aid + File.separator + UUID.randomUUID() +".jpg", 
					rect);

			File imageMark = opencvUtils.imageMark(filePath, 
					ImgFilePathConfig.ROOT + File.separator + ImgFilePathConfig.VERIFY + File.separator + aid + File.separator + UUID.randomUUID() +".jpg", 
					rect);
			
			List<User> users = verifyService.verify(imageCut, userList);
			verifyService.userSignInDB(users, aid);
			verifyService.saveFile(imageMark, users, aid);
			
			imageCut.delete();
			imageMark.delete();
		
		}
	}

}