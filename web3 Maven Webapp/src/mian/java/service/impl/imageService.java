package service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.impl.imageDaoImpl;

import model.image;
import model.model;
import service.IimageService;

@Service
public class imageService implements IimageService{
	
	@Resource
	private imageDaoImpl dao;

	public image saveOrUpdate(image obj) {
		return dao.saveOrUpdate(obj);
	}

	@Override
	public void delete(String id) {
		dao.delete(id);
	}

	@Override
	public void deleteByModelId(String modelId) {
		dao.deleteByModelId(modelId);
		
	}

	@Override
	public List<image> list(String modelId) {
		return dao.list(modelId);
	}

	@Override
	public void updateImageId(String id, String[] imageid) {
		if(imageid.length>0){
			String ids = "";
			for (int i = 0; i < imageid.length; i++) {
				if(i==imageid.length-1){
					ids += "'"+imageid[i]+"'";
				}else{
					ids += "'"+imageid[i]+"'"+",";
				}
			}
			dao.updateImageId(id,ids);
		}
	}

	
}
