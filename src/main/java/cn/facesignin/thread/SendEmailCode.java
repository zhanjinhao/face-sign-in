package cn.facesignin.thread;

import cn.facesignin.utils.MailVerification;

public class SendEmailCode implements Runnable{
	
	private String msg;
	private String email;
	
	public SendEmailCode(String msg, String email) {
		this.msg = msg;
		this.email = email;
	}
	
	@Override
	public void run() {
		try {
			new MailVerification(msg, email).sendVerCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}