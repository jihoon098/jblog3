package kr.co.jblog.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.jblog.repository.BlogDao;
import kr.co.jblog.vo.BlogVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	
	public BlogVo get(String id) {
		return blogDao.get(id);
	}

	
	public void update(String id, String title, String url) {
		BlogVo vo = new BlogVo();
		vo.setId(id);
		vo.setTitle(title);
		vo.setLogo(url);
		
		blogDao.update(vo);	
	}
	
	
	public Map<String, Object> getAll(String id, Long categoryPathNo, Long postPathNo) {
		return blogDao.getAll(id, categoryPathNo, postPathNo);
	}
	

}
