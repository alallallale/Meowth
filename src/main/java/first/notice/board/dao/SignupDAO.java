package first.notice.board.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import first.notice.board.vo.UserVO;

@Repository
public class SignupDAO {
	@Autowired
	SqlSession session;

	public void signup(UserVO user) {
		session.insert("user.userinsert", user);
	}

	public UserVO signin(String p_userid, String p_password) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("p_userid", p_userid);
		map.put("p_password", p_password);
		return session.selectOne("signin", map);
	}

	public UserVO loadmyaccount(String p_userid) {
		return session.selectOne("loadmyaccount", p_userid);
	}

	public int userDelete(UserVO uservo) {
		return session.delete("userdelete", uservo);
	}

	public void accountUpdate(UserVO uservo) {
		session.update("accountupdate", uservo);
	}

	public String idConfirm(String p_userid) {
		return session.selectOne("idconfirm", p_userid);
	}

	public String nameConfirm(String p_name) {
		return session.selectOne("nameconfirm", p_name);
	}

	public List<UserVO> userList(HashMap<String, String> hashmap) {
		return session.selectList("userlist", hashmap);
	}

	public List<UserVO> userGradeList(HashMap<String, String> hashmap) {
		return session.selectList("usergradelist", hashmap);
	}

	public int gradeUpdate(HashMap<String, String> hashmap) {
		return session.update("gradeupdate", hashmap);
	}

	public int userDeleteMaster(String userid) {
		return session.delete("userdeletemaster", userid);
	}

	public int allUserDelete() {
		return session.delete("alluserdelete");
	}

	public List<HashMap<String, String>> accountChart(String settingYear) {
		return session.selectList("accountchart", settingYear);
	}
}
