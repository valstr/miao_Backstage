package controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.commonResponse;
import common.responseRows;
import service.IuserService;

@Controller
@RequestMapping("/user")
public class userController {

	@Resource
	private IuserService service;
	//private User user;
	
	/**
	 * @param enterprise
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/save")
	public Map<String, Object> save(user user){
		service.save(user);
		return commonResponse.getResonse1();
	}
	
	/**
	 * @param enterprise
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/modify")
	public Map<String, Object> modify(user user){
		service.modify(user);//
		//
		//mo.addAttribute(user);
		//${user.username}
		//req.getRequestDispatcher("edit.jsp").forward(req, res);
		//res.sendRedirect("")
		return commonResponse.getResonse1();//  success  user save.jsp  update.jsp success.jsp list.jsp  redirect:success   success.jsp    "save"    save.jsp
	}
	
	
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(int page,int pageSize){
		Map<String, Object> map = commonResponse.getResonse1();
		map.put("total", service.count());
		map.put("rows", service.list(page, pageSize));
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/list/{enterpriseid}")
	public Map<String, Object> list(@PathVariable("enterpriseid") int enterpriseid){
		Map<String, Object> map = commonResponse.getResonse1();
		map.put("rows", service.list(enterpriseid));
		return map;
	}
	
	/**
	 * app enterpriseid 管理员,员工列表
	 * @param enterpriseid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listuser")
	public Map<String, Object> listUser(int enterpriseid){
		Map<String, Object> map = commonResponse.getResonse1();
		map.put("mgr", service.listMgr(enterpriseid));
		map.put("yg", service.listYuangong(enterpriseid));
		return map;
	}
	
	/**
	 * app管理员列表
	 * @param enterpriseid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listMgr/{enterpriseid}")
	public Map<String, Object> listMgr(@PathVariable("enterpriseid") int enterpriseid){
		Map<String, Object> map = commonResponse.getResonse1();
		map.put("rows", service.listMgr(enterpriseid));
		return map;
	}
	
	
	/**
	 * app添加管理员
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addMgr")
	public Map<String, Object> addMgr(int id){
		Map<String, Object> map = commonResponse.getResonse1();
		service.addMgr(id);
		return map;
	}
	
	/**
	 * app删除管理员
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/removeMgr")
	public Map<String, Object> removeMgr(int id){
		Map<String, Object> map = commonResponse.getResonse1();
		service.removeMgr(id);
		return map;
	}
	
	/**
	 * app删除员工
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/removeYuangong")
	public Map<String, Object> removeYuangong(int id){
		Map<String, Object> map = commonResponse.getResonse1();
		service.removeMgr(id);
		return map;
	}
	
	/**
	 * app  enterpriseid下 员工列表
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listYuangong")
	public Map<String, Object> listYuangong(int id){
		Map<String, Object> map = commonResponse.getResonse1();
		map.put("rows", service.listYuangong(id));
		return map;
	}
	
	/**
	 * @param enterprise
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getinfo")
	public Map<String, Object> getUserInfo(String code){
		String openid=code;
		user obj = service.getObjectByOpenId(openid);
		Map<String, Object> map = commonResponse.getResonse1();
		map.put("info", obj);
		return map;
	}
	
	@RequestMapping("/save")
	private responseRows save(int page,int pageSize) {
		responseRows rr = new responseRows();
		rr.setTotal(1);
		rr.setRows(service.list(page, pageSize));
		
		//{total:1,code:,msg:,rows:[{},{}]}
		return rr;
	}
	
	
	
}
