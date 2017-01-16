package dao.impl;

import java.util.List;
import javax.annotation.Resource;
import model.image;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import dao.IimageDao;

@Repository("imageDao")
public class imageDaoImpl implements IimageDao {

	@Resource
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public image saveOrUpdate(image obj) {
		getSession().saveOrUpdate(obj);
		return obj;
	}

	@Override
	public void delete(String id) {//hibernate ql  
		getSession().createQuery("delete from image where id='"+id+"'").executeUpdate();
	}

	@Override
	public void deleteByModelId(String modelId) {
		getSession().createQuery("delete from image where modelId='"+modelId+"'").executeUpdate();
	}

	@Override
	public List<image> list(String modelId) {
		return getSession().createQuery("from image where modelId='"+modelId+"'").list();
	}

	public void updateImageId(String id, String ids) {
		getSession().createQuery("update image set modelId='"+id+"' where id in("+ids+")").executeUpdate();
	}

	

}
