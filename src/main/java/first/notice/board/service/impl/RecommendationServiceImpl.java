package first.notice.board.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import first.notice.board.dao.RecommendationDAO;
import first.notice.board.service.RecommendationService;
import first.notice.board.vo.RecommendationVO;

@Service
public class RecommendationServiceImpl implements RecommendationService {
	@Autowired
	RecommendationDAO recommendationdao;

	@Override
	public int upconfirm(int p_tableseq) {
		return recommendationdao.upconfirm(p_tableseq);
	}

	@Override
	public int downconfirm(int p_tableseq) {
		return recommendationdao.downconfirm(p_tableseq);
	}

	@Override
	public List<HashMap<String, String>> bring(int p_tableseq) {
		return recommendationdao.bring(p_tableseq);
	}

	@Override
	public void insertrecommendation(RecommendationVO recommendationvo) {
		recommendationdao.insertrecommendation(recommendationvo);
	}

	@Override
	public void deletecommendation(RecommendationVO recommendationvo) {
		recommendationdao.deletecommendation(recommendationvo);
	}

	@Override
	public int findDuplicates(RecommendationVO recommendationvo) {
		return recommendationdao.findDuplicates(recommendationvo);
	}

}
