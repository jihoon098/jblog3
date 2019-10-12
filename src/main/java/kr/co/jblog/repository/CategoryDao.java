package kr.co.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.jblog.vo.CategoryVo;
import kr.co.jblog.vo.UserVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;

	public void insertBasic(UserVo vo) {
		sqlSession.insert("category.insertBasic", vo);
	}

	public List<CategoryVo> get(String id) {
		List<CategoryVo> result = sqlSession.selectList("category.getList", id);
		return result;
	}

	public Map<String, Object> getAdminCategory(String id) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("AdminCategoryList", sqlSession.selectList("category.getAdminCategory", id));
		return result;
	}

	public void insert(CategoryVo vo) {
		sqlSession.insert("category.insert", vo);
	}

	public void delete(CategoryVo vo) {
		sqlSession.insert("category.delete", vo);
	}

}
