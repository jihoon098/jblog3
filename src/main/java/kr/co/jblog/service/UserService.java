package kr.co.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.jblog.repository.BlogDao;
import kr.co.jblog.repository.CategoryDao;
import kr.co.jblog.repository.UserDao;
import kr.co.jblog.vo.UserVo;


@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao categoryDao;
	
	
	public UserVo getUser(UserVo vo) {
		return userDao.getUser(vo);
	}

	public void join(UserVo vo) {
		userDao.join(vo);
		System.out.println("---blogDao.insert(vo)---");
		blogDao.insert(vo);
		System.out.println("---categoryDao.insert(vo)---");
		categoryDao.insertBasic(vo);
		System.out.println("---user,blog,category생성---");
	}
	
	
	
	

}
