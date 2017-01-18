package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table
public class model implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9095262079072448731L;
	private String id;
	private String content;
	private String name;
	private Date date = new Date();
	private List<image> images = new ArrayList();
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
	public Date getDate() {
		return date;
	}
	@OneToMany(cascade=CascadeType.ALL,mappedBy ="model",targetEntity=image.class,fetch=FetchType.EAGER)
	public List<image> getImages() {
		return images;
	}
	public void setImages(List<image> images) {
		this.images = images;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
