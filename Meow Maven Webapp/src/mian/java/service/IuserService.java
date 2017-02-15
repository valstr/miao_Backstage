package service;

import model.user;

public interface IuserService extends interfaceService{
	public user getObjectByOpenId(String openid);
}
