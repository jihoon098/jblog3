package kr.co.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.co.jblog.service.UserService;
import kr.co.jblog.vo.UserVo;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {		
		
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");

		UserVo vo = new UserVo();
		vo.setId(id);
		vo.setPassword(password);
		
		
		UserVo authUser = userService.getUser(vo);
		if(authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		//session 처리
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);

		response.sendRedirect(request.getContextPath());
		return false;
	}

}
