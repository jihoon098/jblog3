package kr.co.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.jblog.service.UserService;
import kr.co.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public String login() {
		return "user/login";
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	
	@RequestMapping(value = "/joinsuccess", method = RequestMethod.GET)
	public String joinsuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping(value = "/joinsuccess", method = RequestMethod.POST)
	public String joinsuccess(@ModelAttribute UserVo vo) {
		//이상한 값 넣지못하도록 처리해주자..
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}

}
