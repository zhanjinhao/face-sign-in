package cn.facesignin.listener;

import org.opencv.core.Core;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class InitOpencv implements ApplicationListener<ContextRefreshedEvent>{

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		
		try {
			System.out.println("加载Opencv...");
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		}catch (Exception e) {
			
		}
		
	}
}
