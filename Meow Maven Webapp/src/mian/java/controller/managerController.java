package controller;

import java.util.Map;

import javax.annotation.Resource;

import model.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.commonResponse;

import service.ImanagerService;

@Controller
@RequestMapping("/manager")
public class managerController {

	@Resource
	private ImanagerService service;
	
	/**
	 * @param id
	 * @return manager model
	 */
	@RequestMapping(value="/{id}")
	@ResponseBody
	public manager getObjectById(@PathVariable("id") int id){
		return service.getObjectById(id);
	}
	
	/**
	 * @param obj
	 * @return {code:0,msg:''}
	 */
	@RequestMapping(value="/modify")
	@ResponseBody
	public Map<String, Object> modifyManger(manager obj){
		service.modify(obj);
		return commonResponse.getResonse1();
	}
	
	/**
	 * @param obj
	 * @return 
	 */
	@RequestMapping(value="/login")
	@ResponseBody
	public Map<String, Object> login(manager obj){
		boolean flag = service.login(obj);
		if(flag){
			return commonResponse.getResonse1();
		}else{
			return commonResponse.getResonse0();
		}
	}
	
	/**
	 * @param obj
	 * @return {code:1,msg:'success',total:1,list:[manager-model]}
	 */
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(int page,int pageSize){
		Map<String, Object> map = commonResponse.getResonse1();
		map.put("total", service.count());
		map.put("rows", service.list(page, pageSize));
		return map;
	}
	
	/**
	 * 
	 * @param obj
	 * @return
	 */
	@RequestMapping(value="/save")
	@ResponseBody
	public Map<String, Object> saveManger(manager obj){
		service.save(obj);
		return commonResponse.getResonse1();
	}
	
}
