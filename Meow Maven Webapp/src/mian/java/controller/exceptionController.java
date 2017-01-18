package controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.commonResponse;
import common.myexception;

@Controller
public class exceptionController {

	@ResponseBody
	@ExceptionHandler
	@RequestMapping("/exception")
	public Map<String, Object> exception(){
		return commonResponse.getResonse500();
	}
}
