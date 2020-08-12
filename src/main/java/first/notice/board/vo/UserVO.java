package first.notice.board.vo;

import java.util.Date;

public class UserVO {
	String p_userid;
	String p_password;
	String p_name;
	String p_phone;
	String p_email;
	Date p_date;
	Date p_birth;
	String p_grade;

	public String getP_userid() {
		return p_userid;
	}

	public void setP_userid(String p_userid) {
		this.p_userid = p_userid;
	}

	public String getP_password() {
		return p_password;
	}

	public void setP_password(String p_password) {
		this.p_password = p_password;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public String getP_phone() {
		return p_phone;
	}

	public void setP_phone(String p_phone) {
		this.p_phone = p_phone;
	}

	public String getP_email() {
		return p_email;
	}

	public void setP_email(String p_email) {
		this.p_email = p_email;
	}

	public Date getP_date() {
		return p_date;
	}

	public void setP_date(Date p_date) {
		this.p_date = p_date;
	}

	public Date getP_birth() {
		return p_birth;
	}

	public void setP_birth(Date p_birth) {
		this.p_birth = p_birth;
	}

	public String getP_grade() {
		return p_grade;
	}

	public void setP_grade(String p_grade) {
		this.p_grade = p_grade;
	}

	@Override
	public String toString() {
		return "UserVO [p_userid=" + p_userid + ", p_password=" + p_password + ", p_name=" + p_name + ", p_phone="
				+ p_phone + ", p_email=" + p_email + ", p_date=" + p_date + ", p_birth=" + p_birth + ", p_grade="
				+ p_grade + "]";
	}

}
