package first.notice.board.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import first.notice.board.dao.SignupDAO;
import first.notice.board.service.SignupService;
import first.notice.board.vo.UserVO;

@Service
public class SignupServiceImpl implements SignupService {
	@Autowired
	SignupDAO sign;

	@Override
	public void signup(UserVO user) {
		sign.signup(user);
	}

	@Override
	public UserVO signin(String p_userid, String p_password) {
		return sign.signin(p_userid, p_password);
	}

	@Override
	public UserVO loadmyaccount(String p_userid) {
		return sign.loadmyaccount(p_userid);
	}

	@Override
	public int userDelete(String id, String password) {
		UserVO uservo = new UserVO();
		uservo.setP_userid(id);
		uservo.setP_password(password);
		return sign.userDelete(uservo);
	}

	@Override
	public void accountUpdate(UserVO uservo) {
		sign.accountUpdate(uservo);

	}

	@Override
	public String idConfirm(String p_userid) {
		return sign.idConfirm(p_userid);
	}

	@Override
	public String nameConfirm(String p_name) {
		return sign.nameConfirm(p_name);
	}

	@Override
	public List<UserVO> userList(HashMap<String, String> hashmap) {
		return sign.userList(hashmap);
	}

	@Override
	public List<UserVO> userGradeList(HashMap<String, String> hashmap) {
		return sign.userGradeList(hashmap);
	}

	@Override
	public int gradeUpdate(HashMap<String, String> hashmap) {
		return sign.gradeUpdate(hashmap);

	}

	@Override
	public int userDeleteMaster(String userid) {
		return sign.userDeleteMaster(userid);

	}

	@Override
	public int allUserDelete() {
		return sign.allUserDelete();

	}

	@Override
	public List<HashMap<String, String>> accountchart(String settingYear) {
		return sign.accountChart(settingYear);
	}

}
