package first.notice.board.service;

import java.util.HashMap;
import java.util.List;

import first.notice.board.vo.RecommendationVO;

public interface RecommendationService {

	public int upconfirm(int p_tableseq);

	public int downconfirm(int p_tableseq);

	public List<HashMap<String, String>> bring(int p_tableseq);

	public void insertrecommendation(RecommendationVO recommendationvo);

	public void deletecommendation(RecommendationVO recommendationvo);

	public int findDuplicates(RecommendationVO recommendationvo);
}
