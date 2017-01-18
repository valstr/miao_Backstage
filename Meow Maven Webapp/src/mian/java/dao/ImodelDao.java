package dao;

import java.util.List;

import model.model;

public interface ImodelDao {
	public void saveOrUpdate(model model);
	public void delete(String id);
	public model getModelById(String id);
	public List<model> list(int page,int pageSize);
}
