package service.impl;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;

import model.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.IuserService;
import dao.impl.userDaoImpl;

@Transactional
@Service("userService")
public class userServiceImpl implements IuserService {
	
	@Resource
	private userDaoImpl userDao;

	@Override
	public void save(Serializable obj) {
		userDao.save(obj);
	}

	@Override
	public void delete(int id) {
		userDao.delete(id);
	}

	@Override
	public void modify(Serializable obj) {
		userDao.modify(obj);
	}

	@Override
	public List<Serializable> list() {
		return userDao.list();
	}

	@Override
	public List<Serializable> list(int page, int size) {
		return userDao.list(page, size);
	}

	@Override
	public long count() {
		return userDao.count();
	}

	@Override
	public user getObjectByOpenId(String openid) {
		return userDao.getObjectByOpenId(openid);
	}


	@Override
	public List<user> list(int enterpriseid) { 
		return userDao.list(enterpriseid);
	}

	@Override
	public List<user> listMgr(int enterpriseid) {
		return userDao.listMgr(enterpriseid);
	}

	@Override
	public void addMgr(int id) {
		userDao.addMgr(id);
	}

	@Override
	public void removeMgr(int id) {
		userDao.removeMgr(id);
	}

	@Override
	public List<user> listYuangong(int enterpriseid) {
		return userDao.listYuangong(enterpriseid);
	}

	@Override
	public void removeYuangong(int id) {
		userDao.removeYuangong(id);
	}
	
	

}
