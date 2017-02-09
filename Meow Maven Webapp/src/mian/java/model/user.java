package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class user implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2991804980243331113L;
	private String id;
	private String title;//用户类型,0个人；1公司；
	private String tribeids; //已加群号集合
	
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTribeids() {
		return tribeids;
	}
	public void setTribeids(String tribeids) {
		this.tribeids = tribeids;
	}

	
	
}
