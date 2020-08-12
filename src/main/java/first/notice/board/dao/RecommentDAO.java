package first.notice.board.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import first.notice.board.vo.RecommentVO;

@Repository
public class RecommentDAO {
	@Autowired
	SqlSession session;

	public void recommentinsert(RecommentVO recommentvo) {
		session.insert("recommentinsert", recommentvo);
	}

	public List<RecommentVO> recommentlist(int p_tableseq) {
		return session.selectList("recommentlist", p_tableseq);

	}

	public int recommentDelete(HashMap<String, String> hashmap) {
		return session.delete("recommentDelete", hashmap);
	}

	public void recommentDeleteMaster(int p_recommentseq) {
		session.delete("recommentDeleteMaster", p_recommentseq);
	}

}
