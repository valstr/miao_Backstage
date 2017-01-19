package dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import model.User;

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

	public User getObjectByOpenId(String openid) {
		return (User) getSession().createQuery("from "+entity+" where openid='"+openid+"'").uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<User> list(int enterpriseid) {
		return getSession().createQuery("from "+entity+" where enterpriseid="+enterpriseid).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listMgr(int enterpriseid) {
		return getSession().createQuery("from "+entity+" where enterpriseid="+enterpriseid+" and isgl=1").list();
	}

	@Override
	public void addMgr(int id) {
		getSession().createQuery("update "+entity+" set isgl=1 where openid='"+id+"'").executeUpdate();
	}

	@Override
	public void removeMgr(int id) {
		getSession().createQuery("update "+entity+" set isgl=0 where openid='"+id+"'").executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listYuangong(int enterpriseid) {
		return getSession().createQuery("from "+entity+" where enterpriseid="+enterpriseid+" and isgl=0").list();
	}

	@Override
	public void removeYuangong(int id) {
		getSession().createQuery("delete from "+entity+" where openid='"+id+"'").executeUpdate();
	}

}
