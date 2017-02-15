package controller;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import model.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import common.commonResponse;
import common.httpUtil;
import service.IuserService;

@Controller
@RequestMapping("/user")
public class userController {

	@Resource
	private IuserService service;
	@Value("${appid}")
	private String appid;
	@Value("${AppSecret}")
	private String AppSecret;
	
	/**
	 * @param enterprise
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/save")
	public Map<String, Object> save(user user){
		service.save(user);
		Map<String, Object> map = commonResponse.getResonse1();
		map.put("cont", user);
		return map;
	}
	
	/**
	 * @param enterprise
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/modify")
	public Map<String, Object> modify(user user){
		service.modify(user);
		return commonResponse.getResonse1();
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(int page,int pageSize){
		Map<String, Object> map = commonResponse.getResonse1();
		map.put("total", service.count());
		map.put("rows", service.list(page, pageSize));
		return map;
	}

	/**
	 * 用户登入
	 * @param user
	 * @param code
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value="/login")
	public Map<String, Object> login(String code) throws ParseException, IOException {
		System.out.println("================"+code+"-------------"+appid+"--------------"+AppSecret);
		JSONObject json = httpUtil.doGetStr("https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+AppSecret+"&js_code="+code+"&grant_type=authorization_code");
		String openid = json.getString("openid");
		if(openid != null){//登入成功 
			user u = service.getObjectByOpenId(openid);
			Map<String, Object> map = commonResponse.getResonse1();
			if(u==null){//第一次登入 添加到系统
				map.put("openid", openid);
			}else{ //第二次登录
				map.put("cont", u);
			}
			return map;
		}else{
			return commonResponse.getResonse500();
		}
	}
	
}
