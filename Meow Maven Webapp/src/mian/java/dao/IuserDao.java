package dao;

import java.util.List;

import model.user;

public interface IuserDao extends interfaceDao {

	public List<user> listMgr(int enterpriseid);
	public void addMgr(int id);
	public void removeMgr(int id);
	
	public List<user> listYuangong(int enterpriseid);
	public void removeYuangong(int id);
	long count();
}
