package first.notice.board.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import first.notice.board.vo.CommentVO;

@Repository
public class CommentDAO {
	@Autowired
	SqlSession session;

	public void commentinsert(CommentVO commentvo) {
		session.insert("commentinsert", commentvo);
	}

	public List<CommentVO> commentlist(int p_seq) {
		return session.selectList("commentlist", p_seq);

	}

	public int commentDelete(HashMap<String, String> hashmap) {
		return session.delete("commentDelete", hashmap);
	}

	public void commentDeleteMaster(int p_seq) {
		session.delete("commentDeleteMaster", p_seq);
	}

	public List<HashMap<String, String>> commentChart(String settingYear) {
		return session.selectList("commentChart", settingYear);
	}

}
