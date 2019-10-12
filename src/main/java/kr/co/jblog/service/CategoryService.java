package kr.co.jblog.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.jblog.repository.CategoryDao;
import kr.co.jblog.repository.PostDao;
import kr.co.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private PostDao postDao;
	
	public List<CategoryVo> get(String id) {
		return categoryDao.get(id);
	}

	public Map<String, Object> getAdminCategory(String id) {
		return categoryDao.getAdminCategory(id);
	}

	public void insert(CategoryVo vo) {
		categoryDao.insert(vo);
	}

	public void delete(CategoryVo vo) {
		postDao.setCategoryToBasic(vo);
		categoryDao.delete(vo);
	}

	public Map<String, Object> insertAndgetNewCategory(CategoryVo vo) {
		categoryDao.insert(vo);
		return categoryDao.getNewCategory(vo);
	}


}
