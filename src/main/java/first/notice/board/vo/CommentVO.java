package first.notice.board.vo;

import java.util.Date;

public class CommentVO {
	int p_tableseq;
	int p_seq;
	String p_password;
	String p_writer;
	Date p_date;
	String p_content;
	String p_commentip;

	public int getP_tableseq() {
		return p_tableseq;
	}

	public void setP_tableseq(int p_tableseq) {
		this.p_tableseq = p_tableseq;
	}

	public int getP_seq() {
		return p_seq;
	}

	public void setP_seq(int p_seq) {
		this.p_seq = p_seq;
	}

	public String getP_password() {
		return p_password;
	}

	public void setP_password(String p_password) {
		this.p_password = p_password;
	}

	public String getP_writer() {
		return p_writer;
	}

	public void setP_writer(String p_writer) {
		this.p_writer = p_writer;
	}

	public Date getP_date() {
		return p_date;
	}

	public void setP_date(Date p_date) {
		this.p_date = p_date;
	}

	public String getP_content() {
		return p_content;
	}

	public void setP_content(String p_content) {
		this.p_content = p_content;
	}

	public String getP_commentip() {
		return p_commentip;
	}

	public void setP_commentip(String p_commentip) {
		this.p_commentip = p_commentip;
	}

	@Override
	public String toString() {
		return "CommentVO [p_tableseq=" + p_tableseq + ", p_seq=" + p_seq + ", p_password=" + p_password + ", p_writer="
				+ p_writer + ", p_date=" + p_date + ", p_content=" + p_content + ", p_commentip=" + p_commentip + "]";
	}

}
