package kr.co.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.jblog.repository.CategoryDao;
import kr.co.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	public List<CategoryVo> get() {
		return categoryDao.get();
	}

}
