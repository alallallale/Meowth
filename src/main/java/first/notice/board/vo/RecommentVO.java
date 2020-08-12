package first.notice.board.vo;

import java.util.Date;

public class RecommentVO {
	int p_tableseq;
	int p_commentseq;
	int p_recommentseq;
	String p_repassword;
	String p_rewriter;
	Date p_redate;
	String p_recontent;
	String p_recommentip;

	public int getP_tableseq() {
		return p_tableseq;
	}

	public void setP_tableseq(int p_tableseq) {
		this.p_tableseq = p_tableseq;
	}

	public int getP_commentseq() {
		return p_commentseq;
	}

	public void setP_commentseq(int p_commentseq) {
		this.p_commentseq = p_commentseq;
	}

	public int getP_recommentseq() {
		return p_recommentseq;
	}

	public void setP_recommentseq(int p_recommentseq) {
		this.p_recommentseq = p_recommentseq;
	}

	public String getP_repassword() {
		return p_repassword;
	}

	public void setP_repassword(String p_repassword) {
		this.p_repassword = p_repassword;
	}

	public String getP_rewriter() {
		return p_rewriter;
	}

	public void setP_rewriter(String p_rewriter) {
		this.p_rewriter = p_rewriter;
	}

	public Date getP_redate() {
		return p_redate;
	}

	public void setP_redate(Date p_redate) {
		this.p_redate = p_redate;
	}

	public String getP_recontent() {
		return p_recontent;
	}

	public void setP_recontent(String p_recontent) {
		this.p_recontent = p_recontent;
	}

	public String getP_recommentip() {
		return p_recommentip;
	}

	public void setP_recommentip(String p_recommentip) {
		this.p_recommentip = p_recommentip;
	}

	@Override
	public String toString() {
		return "RecommentVO [p_tableseq=" + p_tableseq + ", p_commentseq=" + p_commentseq + ", p_recommentseq="
				+ p_recommentseq + ", p_repassword=" + p_repassword + ", p_rewriter=" + p_rewriter + ", p_redate="
				+ p_redate + ", p_recontent=" + p_recontent + ", p_recommentip=" + p_recommentip + "]";
	}

}
