package first.notice.board.service;

import java.util.HashMap;
import java.util.List;

import first.notice.board.vo.BoardVO;

public interface BoardService {
	public List<BoardVO> boardselect(HashMap<String, String> map);

	public void boardinsert(BoardVO boardvo);

	public BoardVO boarddetail(int p_seq);

	public void boardviewcountup(int p_seq);

	public int totalBoard();

	public BoardVO boardConfirm(HashMap<String, String> hashmap);

	public int deleteBoard(HashMap<String, String> hashmap);

	public int deleteBoardMaster(int p_seq);

	public int updateBoard(BoardVO boardvo);

	public List<HashMap<String, String>> boardChart(String settingYear);

}
