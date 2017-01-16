package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.image;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import service.IimageService;

@Controller
public class uploadControlller {
	
	@Resource
	private IimageService service; 
	@Value("${config.upload}")
	private String upload;
	
	@ResponseBody
	@RequestMapping("/upload")
	public image headimage(@RequestParam("file") CommonsMultipartFile upfile,HttpServletRequest req) throws IllegalStateException, IOException{
		//|获取在Web服务器上的 绝对路径
		String path =req.getRealPath("/upload")+"\\";
		if(!upfile.isEmpty()){
			String suffix = upfile.getOriginalFilename().substring(upfile.getOriginalFilename().lastIndexOf("."));//后缀
			image img = new image();
			img.setSuffix(suffix);
			img = service.saveOrUpdate(img); 
			String filename = img.getId()+ suffix;
			img.setUrl(upload+filename);
			service.saveOrUpdate(img);
			upfile.transferTo(new File(path+filename)); //保存图片
			return img;
		}else{
			return null;
		}
	}
	@ResponseBody
	@RequestMapping("/upload/before")
	public String beforeupload(String status,HttpServletRequest req,HttpSession session){
		if("md5Check".equals(status)){
			String md5 = req.getParameter("md5");
			
			if(session.getAttribute("md5") ==null){
				List<String> li = new ArrayList<String>();
				li.add(md5);
				session.setAttribute("upload_md5", li);
				return "{ifExist:0}";
			}else{
				List<String> li = (List<String>)session.getAttribute("md5");
				if(li.contains(md5)){
					return "{ifExist:1}";
				}else{
					return "{ifExist:0}";
				}
			}
		}else if("chunkCheck".equals(status)){
			String chunkMD5 = req.getParameter("chunkMD5");
			String chunkIndex = req.getParameter("chunkIndex");
			String size = req.getParameter("size");
			return "{ifExist:0}";
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/upload/delete")
	public String deleteSigle(String id,String suffix,HttpServletRequest req) {
		service.delete(id);
		String path =req.getRealPath("/upload")+"\\";
		File f = new File(path+id+suffix);
		if(f.exists()){
			f.delete();
		}
		return "{code:1,msg:'ok'}";
	}
}











