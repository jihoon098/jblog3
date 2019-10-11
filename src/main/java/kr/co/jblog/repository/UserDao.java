package kr.co.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.jblog.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	public UserVo getUser(UserVo vo) {
		return sqlSession.selectOne("user.getByIdAndPassword", vo);
	}

	public void join(UserVo vo) {
		sqlSession.insert("user.join", vo);
	}

}
