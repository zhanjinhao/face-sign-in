package cn.facesignin.mq;

import java.util.List;

import cn.facesignin.pojo.User;

public class Entity {
	private List<User> users;
	private Integer aid;
	private String filePath;
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	@Override
	public String toString() {
		return "Entity [users=" + users + ", aid=" + aid + ", filePath=" + filePath + "]";
	}
	
}
