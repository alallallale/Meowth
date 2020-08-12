package first.notice.board.service;

import java.util.HashMap;
import java.util.List;

import first.notice.board.vo.UserVO;

public interface SignupService {
	// 회원가입
	public void signup(UserVO user);

	// 로그인
	public UserVO signin(String p_userid, String p_password);

	// 마이페이지
	public UserVO loadmyaccount(String p_userid);

	// 회원탈퇴
	public int userDelete(String id, String password);

	// 회원수정
	public void accountUpdate(UserVO uservo);

	// id확인
	public String idConfirm(String p_userid);

	// name 확인
	public String nameConfirm(String p_name);

	// 관리자 설정

	// 회원정보 가져오기
	public List<UserVO> userList(HashMap<String, String> hashmap);

	// 회원정보 등급별로 가져오기
	public List<UserVO> userGradeList(HashMap<String, String> hashmap);

	// 회원등급 조정
	public int gradeUpdate(HashMap<String, String> hashmap);

	// 회원 강제탈퇴
	public int userDeleteMaster(String userid);

	// 모든 회원 탈퇴
	public int allUserDelete();

	// 월별 가입자 수
	public List<HashMap<String, String>> accountchart(String settingYear);
}
