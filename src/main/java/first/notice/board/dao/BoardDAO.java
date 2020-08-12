package first.notice.board.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import first.notice.board.vo.BoardVO;

@Repository
public class BoardDAO {
	@Autowired
	SqlSession session;

	public List<BoardVO> boardselect(HashMap<String, String> map) {
		return session.selectList("board.boardselect", map);
	}

	public void boardinsert(BoardVO boardvo) {
		session.insert("boardwrite", boardvo);
	}

	public BoardVO boarddetail(int p_seq) {
		return session.selectOne("boarddetail", p_seq);
	}

	public void boardviewcountup(int p_seq) {
		session.update("boardviewcountup", p_seq);
	}

	public int totalBoard() {
		return session.selectOne("totalBoard");
	}

	public BoardVO boardConfirm(HashMap<String, String> hashmap) {
		return session.selectOne("boardconfirm", hashmap);
	}

	public int deleteBoard(HashMap<String, String> hashmap) {
		return session.delete("deleteboard", hashmap);
	}

	public int deleteBoardMaster(int p_seq) {
		return session.delete("deleteboardmaster", p_seq);
	}

	public int updateBoard(BoardVO boardvo) {
		return session.update("updateboard", boardvo);
	}

	public List<HashMap<String, String>> boardChart(String settingYear) {
		return session.selectList("boardchart", settingYear);
	}

}
