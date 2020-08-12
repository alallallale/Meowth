package first.notice.board.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import first.notice.board.dao.BoardDAO;
import first.notice.board.service.BoardService;
import first.notice.board.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardDAO board;

	@Override
	public List<BoardVO> boardselect(HashMap<String, String> map) {
		return board.boardselect(map);
	}

	@Override
	public void boardinsert(BoardVO boardvo) {
		board.boardinsert(boardvo);
	}

	@Override
	public BoardVO boarddetail(int p_seq) {
		return board.boarddetail(p_seq);
	}

	@Override
	public void boardviewcountup(int p_seq) {
		board.boardviewcountup(p_seq);
	}

	@Override
	public int totalBoard() {
		return board.totalBoard();
	}

	@Override
	public BoardVO boardConfirm(HashMap<String, String> hashmap) {
		return board.boardConfirm(hashmap);
	}

	@Override
	public int deleteBoard(HashMap<String, String> hashmap) {
		return board.deleteBoard(hashmap);
	}

	@Override
	public int deleteBoardMaster(int p_seq) {
		return board.deleteBoardMaster(p_seq);
	}

	@Override
	public int updateBoard(BoardVO boardvo) {
		return board.updateBoard(boardvo);
	}

	@Override
	public List<HashMap<String, String>> boardChart(String settingYear) {
		return board.boardChart(settingYear);
	}

}
