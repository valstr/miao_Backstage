package service;

import java.util.List;

import model.user;

public interface IuserService extends interfaceService{

	public user getObjectByOpenId(String openid);
	public List<user> list(int enterpriseid);
	
	public List<user> listMgr(int enterpriseid);
	public void addMgr(int id);
	public void removeMgr(int id);
	
	public List<user> listYuangong(int enterpriseid);
	public void removeYuangong(int id);
}
