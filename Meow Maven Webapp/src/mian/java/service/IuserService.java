package service;

import java.util.List;

import model.User;

public interface IuserService extends interfaceService{

	public User getObjectByOpenId(String openid);
	public List<User> list(int enterpriseid);
	
	public List<User> listMgr(int enterpriseid);
	public void addMgr(int id);
	public void removeMgr(int id);
	
	public List<User> listYuangong(int enterpriseid);
	public void removeYuangong(int id);
}
