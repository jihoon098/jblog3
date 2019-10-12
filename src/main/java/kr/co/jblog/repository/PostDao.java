package kr.co.jblog.repository;


import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.jblog.vo.CategoryVo;
import kr.co.jblog.vo.PostVo;

@Repository
public class PostDao {

	@Autowired
	private SqlSession sqlSession;
	
	public void write(PostVo vo) {
		sqlSession.insert("post.write", vo);
	}

	public void setCategoryToBasic(CategoryVo vo) {
		sqlSession.insert("post.updateCategoryNo", vo);
	}

	public Map<String, Object> checkExist(CategoryVo vo) {
		Map<String, Object> result = sqlSession.selectOne("post.checkExist", vo);
		return result;
	}

}
