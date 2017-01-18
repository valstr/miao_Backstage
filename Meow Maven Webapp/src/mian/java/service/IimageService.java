package service;

import java.util.List;

import model.image;

public interface IimageService {
	public image saveOrUpdate(image img);
	public void delete(String id);
	public void deleteByModelId(String modelId);
	public List<image> list(String modelId);
	public void updateImageId(String id, String[] imageid);
}
