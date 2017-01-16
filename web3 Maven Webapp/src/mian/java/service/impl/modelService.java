package service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.aspectj.util.FileUtil;
import org.springframework.stereotype.Service;

import dao.impl.imageDaoImpl;
import dao.impl.modelDaoImpl;

import model.image;
import model.model;
import service.ImodelService;

@Service
public class modelService implements ImodelService {
	
	@Resource
	private modelDaoImpl dao;
	@Resource
	private imageDaoImpl imgdao;
	
	@Override
	public void saveOrUpdate(model model) {
		dao.saveOrUpdate(model);
	}

	@Override
	public void delete(String id,String path) {
		List<image> list = imgdao.list(id);
		for (image image : list) {
			File f = new File(path+image.getId()+image.getSuffix());
			if(f.exists()){
				f.delete();
			}
		}
		imgdao.deleteByModelId(id);
		dao.delete(id);
	}

	@Override
	public model getModelById(String id) {
		return dao.getModelById(id);
	}

	@Override
	public List<model> list(int page, int pageSize) {
		return dao.list(page, pageSize);
	}
	
	public long count(){
		return dao.count();
	}

}
