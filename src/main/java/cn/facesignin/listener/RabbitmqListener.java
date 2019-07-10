package cn.facesignin.listener;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections4.map.HashedMap;
import org.opencv.core.Rect;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;

import cn.facesignin.constant.ImgFilePathConfig;
import cn.facesignin.mq.Entity;
import cn.facesignin.pojo.Activity;
import cn.facesignin.pojo.User;
import cn.facesignin.service.VerifyService;
import cn.facesignin.utils.OpencvUtils;

public class RabbitmqListener implements ChannelAwareMessageListener {

	@Autowired
	private VerifyService verifyService;

	private OpencvUtils opencvUtils = OpencvUtils.getInstance();

	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		channel.basicQos(10);
		String msg = new String(message.getBody(), "utf-8");
		channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
		System.out.println(msg);

		Entity entity = JSON.parseObject(msg, Entity.class);

		System.out.println(entity);

		
	}

}