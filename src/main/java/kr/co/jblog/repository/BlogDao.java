package kr.co.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.jblog.vo.BlogVo;
import kr.co.jblog.vo.PostVo;
import kr.co.jblog.vo.UserVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;

	public void insert(UserVo vo) {
		sqlSession.insert("blog.insert", vo);
	}

	public BlogVo get(String id) {
		return sqlSession.selectOne("blog.get", id);
	}

	public void update(BlogVo vo) {
		sqlSession.update("blog.update", vo);
	}

	public Map<String, Object> getAll(String id, Long categoryPathNo, Long postPathNo) {
		//0,0일때 -> DB쪽에서 category_no가 가장 작은 것에 대한 검색. > 해당 카테고리 중에 가장 최근꺼 보여줌.
		//2,0일때 -> 2에 대한 카테고리들과  그것 중 가장 최근꺼 보여줌.
		//2,2일때 -> 2카테고리에 대한 2에 해당하는 포스트 검색.
		
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("id", id);
		map.put("categoryPathNo", categoryPathNo);
		map.put("postPathNo", postPathNo);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("blogVo", sqlSession.selectOne("blog.get", id));//blog를 가져옴
		result.put("categoryList", sqlSession.selectList("category.getList"));//categoryList를 가져옴
		result.put("postList", sqlSession.selectList("post.getList", map));//postList를 가져옴
		result.put("postVo", sqlSession.selectOne("post.get", map));//post가져옴
		
		//System.out.println((BlogVo)result.get("blogVo"));
		//System.out.println((PostVo)result.get("postVo"));
		
		return result;
	}
	
	
	
	
}
