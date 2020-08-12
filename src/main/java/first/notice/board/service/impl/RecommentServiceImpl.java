package first.notice.board.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import first.notice.board.dao.RecommentDAO;
import first.notice.board.service.RecommentService;
import first.notice.board.vo.RecommentVO;

@Service
public class RecommentServiceImpl implements RecommentService {
	@Autowired
	RecommentDAO recommentdao;

	@Override
	public void recommentinsert(RecommentVO recommentvo) {
		recommentdao.recommentinsert(recommentvo);

	}

	@Override
	public List<RecommentVO> recommentList(int p_tableseq) {
		return recommentdao.recommentlist(p_tableseq);

	}

	@Override
	public int recommentDelete(HashMap<String, String> hashmap) {
		return recommentdao.recommentDelete(hashmap);

	}

	@Override
	public void recommentDeleteMaster(int p_recommentseq) {
		recommentdao.recommentDeleteMaster(p_recommentseq);

	}

}
