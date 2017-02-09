package dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import model.user;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import dao.IuserDao;

@Repository("userDao")
public class userDaoImpl implements IuserDao {
	
	private static final String entity = "user";
	
	@Resource
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void save(Serializable obj) {
		getSession().save(obj);
	}

	public void delete(int id) {
		getSession().createQuery("delete from "+entity+" where id="+id).executeUpdate();
	}

	public void modify(Serializable obj) {
		getSession().merge(obj);
	}

	public List<Serializable> list() {
		return getSession().createQuery("from "+entity).list();
	}

	public List<Serializable> list(int page, int size) {
		Session session = this.getSession();
		Query query = session.createQuery("from "+entity);
		query.setFirstResult((page-1)*size);
		query.setMaxResults(size);
		return query.list();
	}

	@Override
	public long count() {
		Object value = getSession().createQuery("select count(id) from "+entity).uniqueResult();
		System.out.println(value); 
		return (Long)value;
	}

	public user getObjectByOpenId(String openid) {
		return (user) getSession().createQuery("from "+entity+" where openid='"+openid+"'").uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<user> list(int enterpriseid) {
		return getSession().createQuery("from "+entity+" where enterpriseid="+enterpriseid).list();
	}

}
