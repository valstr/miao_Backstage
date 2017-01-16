package dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import model.model;
import dao.ImodelDao;

@Repository("modelDao")
public class modelDaoImpl implements ImodelDao {
	
	private static final String entity = "model";
	@Resource
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void saveOrUpdate(model model) {
		getSession().saveOrUpdate(model);
	}

	@Override
	public model getModelById(String id) {
		return (model) getSession().get(model.class, id); 
	}

	@Override
	public List<model> list(int page, int pageSize) {
		Session session = this.getSession();
		Query query = session.createQuery("from model");
		query.setFirstResult((page-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public void delete(String id) {
		getSession().createQuery("delete from model where id in('"+id+"')").executeUpdate();
	}

	public long count() {
		Object value = getSession().createQuery("select count(id) from "+entity).uniqueResult();
		return (Long)value;
	}

}
