package first.notice.board.service;

import java.util.HashMap;
import java.util.List;

import first.notice.board.vo.CommentVO;

public interface CommentService {
	public void commentinsert(CommentVO commentvo);

	public List<CommentVO> commentList(int p_seq);

	public int commentDelete(HashMap<String, String> hashmap);

	public void commentDeleteMaster(int p_seq);

	public List<HashMap<String, String>> commentChart(String settingYear);

}
