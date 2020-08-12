package first.notice.board.service;

import java.util.HashMap;
import java.util.List;

import first.notice.board.vo.RecommentVO;

public interface RecommentService {
	public void recommentinsert(RecommentVO recommentvo);

	public List<RecommentVO> recommentList(int p_tableseq);

	public int recommentDelete(HashMap<String, String> hashmap);

	public void recommentDeleteMaster(int p_recommentseq);
}
