package dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public interface interfaceDao {

	public Session getSession();
	public void setSessionFactory(SessionFactory sessionFactory);
	public void save(Serializable obj);
	public void delete(int id);
	public void modify(Serializable obj);
	public List<Serializable> list();
	public List<Serializable> list(int page,int size);
	public long count();
}
