package service.impl;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;


import model.user;

import org.springframework.stereotype.Service;

import dao.impl.userDaoImpl;
import service.IuserService;

@Service
public class userServiceImpl implements IuserService {
	
	@Resource
	private userDaoImpl dao;

	@Override
	public void save(Serializable obj) {
		dao.save(obj);
	}

	@Override
	public void delete(int id) {
		dao.delete(id);
	}

	@Override
	public void modify(Serializable obj) {
		dao.modify(obj);
	}

	@Override
	public List<Serializable> list() {
		return dao.list();
	}

	@Override
	public List<Serializable> list(int page, int size) {
		return dao.list(page, size);
	}

	@Override
	public long count() {
		return dao.count();
	}

	@Override
	public user getObjectByOpenId(String openid) {
		return dao.getObjectByOpenId(openid);
	}
	

}
