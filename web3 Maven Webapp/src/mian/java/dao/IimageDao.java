package dao;

import java.util.List;

import model.image;
import model.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public interface IimageDao {
	public Session getSession();
	public void setSessionFactory(SessionFactory sessionFactory);
	public image saveOrUpdate(image obj);
	public void delete(String id);
	public void deleteByModelId(String modelId);
	public List<image> list(String modelId);
}
