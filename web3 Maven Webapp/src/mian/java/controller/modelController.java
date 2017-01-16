package controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import model.model;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.commonResponse;
import common.responseRows;

import service.IimageService;
import service.ImodelService;

@Controller
@RequestMapping("/model")
public class modelController {

	@Resource
	private ImodelService servie;
	@Resource
	private IimageService imgservice;
	
	@RequestMapping(value="/save")
	public String save(model model,String[] imageid){
		servie.saveOrUpdate(model);
		imgservice.updateImageId(model.getId(),imageid);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public responseRows list(int page,int pageSize){
		responseRows rows = new responseRows();
		long total = servie.count();
		rows.setTotal(total);
		List<model> list = servie.list(page, pageSize);
		rows.setRows(list);
		return rows;
	}
	@RequestMapping("/toedit")
	public String toedit(String id,Model m){
		model model = servie.getModelById(id);
		System.out.println(model.getContent());
		m.addAttribute("obj", model);
		return "edit";
	}
	@RequestMapping(value="/edit")
	public String edit(model model,String[] imageid) {
		servie.saveOrUpdate(model);
		imgservice.updateImageId(model.getId(),imageid);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public String delete(String id,HttpServletRequest req) {
		String path =req.getRealPath("/upload")+"\\";
		servie.delete(id, path);
		return "{code:1,msg:'ok'}";
	}
	
	@ResponseBody
	@RequestMapping("/getmodel")
	public Map<String, Object> getModel(String id){
		Map<String, Object> mp = commonResponse.getResonse1();
		model model = servie.getModelById(id);
		mp.put("cont", model);
		return mp;
	}
	
}
