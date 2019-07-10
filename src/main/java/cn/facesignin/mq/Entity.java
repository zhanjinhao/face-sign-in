package cn.facesignin.mq;

import java.util.List;

import cn.facesignin.pojo.Activity;
import cn.facesignin.pojo.User;

public class Entity {
	private List<User> users;
	private Activity activity;
	private String filePath;
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	@Override
	public String toString() {
		return "Entity [users=" + users + ", activity=" + activity + ", filePath=" + filePath + "]";
	}
}
