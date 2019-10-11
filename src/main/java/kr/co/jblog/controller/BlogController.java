package kr.co.jblog.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.jblog.service.BlogService;
import kr.co.jblog.service.FileuploadService;
import kr.co.jblog.vo.BlogVo;


@Controller
@RequestMapping( "/{id:(?!assets).*}" )
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@Autowired
	private FileuploadService fileuploadService;
	
	
	@RequestMapping({"", "/{pathNo1}", "/{pathNo1}/{pathNo2}"})
	public String main(
			@PathVariable String id,
			@PathVariable Optional<Long> pathNo1,
			@PathVariable Optional<Long> pathNo2,
			Model model) {

		Long categoryPathNo = 0L;
		Long postPathNo = 0L;
				
		//pathNo2가 있으면 2개값을 다 받음, 없으면 pathNo1만 받음. 
		if(pathNo2.isPresent()) {
			categoryPathNo = pathNo1.get();
			postPathNo = pathNo2.get();
		}else if(pathNo1.isPresent()){
			categoryPathNo = pathNo1.get();
		}
		
		Map<String, Object> result = blogService.getAll(id, categoryPathNo, postPathNo);
		model.addAllAttributes(result);
		return "blog/blog-main";
	}
	

	@RequestMapping(value = "/admin/basic", method=RequestMethod.GET)
	public String adminBasic(@PathVariable String id, Model model) {
		
		//id,title, logo를 담은 vo넘기기
		BlogVo vo = blogService.get(id);
		model.addAttribute("blogVo", vo);
		
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping(value = "/admin/basic", method=RequestMethod.POST)
	public String adminBasic(
			@PathVariable String id, 
			@RequestParam(value="title", required = false) String title,
			@RequestParam(value="logo-file", required = false) MultipartFile multipartFile,
			Model model) {

		//파일 업로드. 파일을 저장하고 경로를 DB에 저장
		//url의 값은:  /uploads/2019~~~~.png 형태로 반환
		String url = fileuploadService.restore(multipartFile);
		blogService.update(id, title, url);
		
		return "redirect:/"+ id + "/admin/basic";
	}
	
	@RequestMapping(value = "/admin/category", method=RequestMethod.GET)
	public String adminCategory(@PathVariable String id, Model model) {
		BlogVo vo = blogService.get(id);
		model.addAttribute("blogVo", vo);
		
		return "blog/blog-admin-category";
	}
	
	@RequestMapping(value = "/admin/write", method=RequestMethod.GET)
	public String adminWrite(@PathVariable String id, Model model) {
		BlogVo vo = blogService.get(id);
		model.addAttribute("blogVo", vo);
		
		return "blog/blog-admin-write";
	}
	
}