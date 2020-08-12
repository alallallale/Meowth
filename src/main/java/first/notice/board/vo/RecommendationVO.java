package first.notice.board.vo;

public class RecommendationVO {
	int p_tableseq;
	String p_recommendation;
	String p_writer;

	public int getP_tableseq() {
		return p_tableseq;
	}

	public void setP_tableseq(int p_tableseq) {
		this.p_tableseq = p_tableseq;
	}

	public String getP_recommendation() {
		return p_recommendation;
	}

	public void setP_recommendation(String p_recommendation) {
		this.p_recommendation = p_recommendation;
	}

	public String getP_writer() {
		return p_writer;
	}

	public void setP_writer(String p_writer) {
		this.p_writer = p_writer;
	}

	@Override
	public String toString() {
		return "RecommendationVO [p_tableseq=" + p_tableseq + ", p_recommendation=" + p_recommendation + ", p_writer="
				+ p_writer + "]";
	}

}
