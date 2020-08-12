package first.notice.board.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import first.notice.board.dao.CommentDAO;
import first.notice.board.service.CommentService;
import first.notice.board.vo.CommentVO;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	CommentDAO commentdao;

	@Override
	public void commentinsert(CommentVO commentvo) {
		commentdao.commentinsert(commentvo);

	}

	@Override
	public List<CommentVO> commentList(int p_seq) {
		return commentdao.commentlist(p_seq);
	}

	@Override
	public int commentDelete(HashMap<String, String> hashmap) {
		return commentdao.commentDelete(hashmap);
	}

	@Override
	public void commentDeleteMaster(int p_seq) {
		commentdao.commentDeleteMaster(p_seq);

	}

	@Override
	public List<HashMap<String, String>> commentChart(String settingYear) {
		return commentdao.commentChart(settingYear);
	}

}
