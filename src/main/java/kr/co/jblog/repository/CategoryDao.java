package kr.co.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.jblog.vo.CategoryVo;
import kr.co.jblog.vo.UserVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	public void insert(UserVo vo) {
		sqlSession.insert("category.insert", vo);
	}

	public List<CategoryVo> get(String id) {
		List<CategoryVo> result = sqlSession.selectList("category.getList", id);
		return result;
	}

}

