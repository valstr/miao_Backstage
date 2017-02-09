package controller;

import java.util.Map;

import javax.annotation.Resource;

import model.work;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.commonResponse;

import service.IworkService;

@Controller
@RequestMapping("/user")
public class userController {

	@Resource
	private IworkService service;
	
	@ResponseBody
	@RequestMapping("/save")
	public Map<String, Object> save(work obj) {
		service.save(obj);
		return commonResponse.getResonse1();
	}
}
