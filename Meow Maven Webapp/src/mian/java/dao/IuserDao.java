package dao;

import java.util.List;

import model.User;

public interface IuserDao extends interfaceDao {

	public List<User> listMgr(int enterpriseid);
	public void addMgr(int id);
	public void removeMgr(int id);
	
	public List<User> listYuangong(int enterpriseid);
	public void removeYuangong(int id);
	long count();
}
