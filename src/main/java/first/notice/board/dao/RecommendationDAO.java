package first.notice.board.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import first.notice.board.vo.RecommendationVO;

@Repository
public class RecommendationDAO {
	@Autowired
	SqlSession session;

	public int upconfirm(int p_tableseq) {
		return session.selectOne("upconfirm", p_tableseq);
	}

	public int downconfirm(int p_tableseq) {
		return session.selectOne("ownconfirm", p_tableseq);
	}

	public List<HashMap<String, String>> bring(int p_tableseq) {
		return session.selectList("bring", p_tableseq);
	}

	public void insertrecommendation(RecommendationVO recommendationvo) {
		session.insert("insertrecommendation", recommendationvo);
	}

	public void deletecommendation(RecommendationVO recommendationvo) {
		session.delete("deletecommendation", recommendationvo);
	}

	public int findDuplicates(RecommendationVO recommendationvo) {
		try {
			return session.selectOne("findduplicates", recommendationvo);
		} catch (Exception e) {
			return 0;
		}
	}

}
