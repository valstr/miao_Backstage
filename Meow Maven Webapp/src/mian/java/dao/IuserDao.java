package dao;

import model.user;

public interface IuserDao extends interfaceDao {
	public user getObjectByOpenId(String openid);
}
