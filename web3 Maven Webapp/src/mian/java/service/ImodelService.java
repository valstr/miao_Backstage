package service;

import java.util.List;

import model.model;

public interface ImodelService {
	public void saveOrUpdate(model model);
	public void delete(String id,String path);
	public model getModelById(String id);
	public List<model> list(int page,int pageSize);
	public long count();
}
