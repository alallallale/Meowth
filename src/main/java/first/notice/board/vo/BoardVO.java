package first.notice.board.vo;

import java.util.Date;

public class BoardVO {
	int p_seq;
	int p_boardtype;
	String p_writer;
	String p_password;
	String p_title;
	String p_content;
	Date p_date;
	int p_viewcount;
	String p_boardip;

	public int getP_seq() {
		return p_seq;
	}

	public void setP_seq(int p_seq) {
		this.p_seq = p_seq;
	}

	public int getP_boardtype() {
		return p_boardtype;
	}

	public void setP_boardtype(int p_boardtype) {
		this.p_boardtype = p_boardtype;
	}

	public String getP_writer() {
		return p_writer;
	}

	public void setP_writer(String p_writer) {
		this.p_writer = p_writer;
	}

	public String getP_password() {
		return p_password;
	}

	public void setP_password(String p_password) {
		this.p_password = p_password;
	}

	public String getP_title() {
		return p_title;
	}

	public void setP_title(String p_title) {
		this.p_title = p_title;
	}

	public String getP_content() {
		return p_content;
	}

	public void setP_content(String p_content) {
		this.p_content = p_content;
	}

	public Date getP_date() {
		return p_date;
	}

	public void setP_date(Date p_date) {
		this.p_date = p_date;
	}

	public int getP_viewcount() {
		return p_viewcount;
	}

	public void setP_viewcount(int p_viewcount) {
		this.p_viewcount = p_viewcount;
	}

	public String getP_boardip() {
		return p_boardip;
	}

	public void setP_boardip(String p_boardip) {
		this.p_boardip = p_boardip;
	}

	@Override
	public String toString() {
		return "BoardVO [p_seq=" + p_seq + ", p_boardtype=" + p_boardtype + ", p_writer=" + p_writer + ", p_password="
				+ p_password + ", p_title=" + p_title + ", p_content=" + p_content + ", p_date=" + p_date
				+ ", p_viewcount=" + p_viewcount + ", p_boardip=" + p_boardip + "]";
	}

}
