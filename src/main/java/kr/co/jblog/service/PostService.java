package kr.co.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.jblog.repository.PostDao;
import kr.co.jblog.vo.PostVo;

@Service
public class PostService {

	@Autowired
	private PostDao postDao;
	
	public void write(PostVo vo) {
		postDao.write(vo);
	}

}
