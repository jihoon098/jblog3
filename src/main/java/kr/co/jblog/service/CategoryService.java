package kr.co.jblog.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.jblog.repository.CategoryDao;
import kr.co.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	public List<CategoryVo> get(String id) {
		return categoryDao.get(id);
	}

	public Map<String, Object> getAdminCategory(String id) {
		return categoryDao.getAdminCategory(id);
	}

	public void insert(CategoryVo vo) {
		categoryDao.insert(vo);
	}


}