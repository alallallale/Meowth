package first.notice.board;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import first.notice.board.service.impl.BoardServiceImpl;
import first.notice.board.service.impl.CommentServiceImpl;
import first.notice.board.service.impl.RecommendationServiceImpl;
import first.notice.board.service.impl.RecommentServiceImpl;
import first.notice.board.service.impl.SignupServiceImpl;
import first.notice.board.vo.BoardVO;
import first.notice.board.vo.CommentVO;
import first.notice.board.vo.RecommendationVO;
import first.notice.board.vo.RecommentVO;
import first.notice.board.vo.UserVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	SignupServiceImpl signupService;
	@Autowired
	BoardServiceImpl boardService;
	@Autowired
	CommentServiceImpl commentService;
	@Autowired
	RecommentServiceImpl recommentService;
	@Autowired
	RecommendationServiceImpl recommendationService;

	HttpSession session = null;
	getIP getip = new getIP();

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		model.addAttribute("yourip", getip.getIp(request));
		return "home";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Locale locale, Model model) {
		logger.info("회원가입 : ", locale);
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(UserVO uservo, String birth) throws ParseException {
		logger.info("계정 생성");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		uservo.setP_birth(format.parse(birth));
		System.out.println(uservo.toString() + "birth : " + birth);
		signupService.signup(uservo);
		return "signupsuccess";
	}

	@RequestMapping(value = "/idConfirm", method = RequestMethod.GET)
	@ResponseBody
	public String idConfirm(String p_userid) {
		logger.info("id 중복 확인");
		if (p_userid.equals("")) {
			return "null";
		}
		String id = signupService.idConfirm(p_userid);
		if (id == null) {
			return "true";
		} else {
			return "false";
		}
	}

	@RequestMapping(value = "/nameConfirm", method = RequestMethod.GET)
	@ResponseBody
	public String nameConfirm(String p_name) {
		logger.info("name 중복 확인");
		if (p_name.equals("")) {
			return "null";
		}
		String name = signupService.nameConfirm(p_name);
		if (name == null) {
			return "true";
		} else {
			return "false";
		}
	}

	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String signin(Model model) {
		logger.info("로그인");
		return "signin";
	}

	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public String signin(Model model, HttpServletRequest request, HttpServletResponse response, String p_userid,
			String p_password) throws Exception {
		logger.info("로그인 POST");
		session = request.getSession(true);
		UserVO uservo = signupService.signin(p_userid, p_password);
		if (uservo != null) {
			System.out.println("존재하는 계정");
			session.setAttribute("uservo", uservo);
			return "redirect:/";
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('비밀번호 또는 아이디가 틀립니다.'); history.go(-1);</script>");
			out.flush();
			return "home";
		}
	}

	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public String signout(Model model) {
		logger.info("로그아웃");
		if (session == null) {
		} else {
			session.invalidate();
			session = null;
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public String board(Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "sortType", required = false, defaultValue = "p_date") String sortType,
			@RequestParam(value = "orderType", required = false, defaultValue = "Descending") String orderType) {
		logger.info("게시판");
//		System.out.println("sortType : " + sortType + ", orderType : " + orderType);
		int totalBoardNum = boardService.totalBoard();
		int totalPageNumber = totalBoardNum / 15;
		if (totalBoardNum % 15 != 0) {
			totalPageNumber++;
		}

		// 가져올 번호들
		Integer[] param = new Integer[2];
		param[0] = page * 15 - 14; // 아래
		param[1] = page * 15; // 위
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("bottomboard", param[0].toString());
		map.put("topboard", param[1].toString());
		map.put("sortType", sortType);
		if (orderType.equals("Descending")) {
			map.put("orderType", "desc");
		} else {
			map.put("orderType", "asc");
		}

		// 리스트를 가져오고 저장
		List<BoardVO> list = boardService.boardselect(map);

		List<String> dateList = new ArrayList<String>();
		Date time = new Date();
		int[][] recommendationArray = new int[list.size()][2];
		for (int i = 0; i < list.size(); i++) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat todayFormat = new SimpleDateFormat("HH:mm");
			String today = dateFormat.format(time);
			if (dateFormat.format(list.get(i).getP_date()).equals(today)) {
				dateList.add(i, todayFormat.format(list.get(i).getP_date()));
			} else {
				dateList.add(i, dateFormat.format(list.get(i).getP_date()));
			}

			List<HashMap<String, String>> recommendationList = recommendationService.bring(list.get(i).getP_seq());
			if (recommendationList.size() == 0) {
				recommendationArray[i][0] = 0;
				recommendationArray[i][1] = 0;

			} else if (recommendationList.size() == 1) {
				if (recommendationList.get(0).get("p_recommendation").equals("YES")) {
					recommendationArray[i][0] = Integer
							.parseInt(String.valueOf(recommendationList.get(0).get("count")));
					recommendationArray[i][1] = 0;
				} else {
					recommendationArray[i][0] = 0;
					recommendationArray[i][1] = Integer
							.parseInt(String.valueOf(recommendationList.get(0).get("count")));
				}
			} else {
				recommendationArray[i][0] = Integer.parseInt(String.valueOf(recommendationList.get(0).get("count")));
				recommendationArray[i][1] = Integer.parseInt(String.valueOf(recommendationList.get(1).get("count")));

			}
		}

		model.addAttribute("page", page);
		model.addAttribute("totalPageNumber", totalPageNumber);
		model.addAttribute("list", list);
		model.addAttribute("dateList", dateList);
		model.addAttribute("recommendationArray", recommendationArray);
		return "board";
	}

	@RequestMapping(value = "/boardwrite", method = RequestMethod.GET)
	public String boardwrite(Model model) {
		logger.info("게시판 작성");
		return "boardwrite";
	}

	@PostMapping(value = "/uploadSummernoteImageFile", produces = "application/json")
	@ResponseBody
	public HashMap<String, Object> uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {

		JsonObject jsonObject = new JsonObject();
		Gson gson = new Gson();

		String fileRoot = "C:\\PikkaPikka\\"; // 저장될 파일 경로
		String originalFileName = multipartFile.getOriginalFilename(); // 오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); // 파일 확장자

		// 랜덤 UUID+확장자로 저장될 savedFileName
		String savedFileName = UUID.randomUUID() + extension;

		File targetFile = new File(fileRoot + savedFileName);
		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile); // 파일 저장
			jsonObject.addProperty("url", "/summernoteImage/" + savedFileName);
			jsonObject.addProperty("responseCode", "success");
		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile); // 실패시 저장된 파일 삭제
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		return gson.fromJson(jsonObject, new HashMap<String, Object>().getClass());
	}

	@RequestMapping(value = "/boardwrite", method = RequestMethod.POST)
	public String boardwrite(Model model, BoardVO boardvo, HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		logger.info("게시판 작성");
		boardvo.setP_boardip(getip.getIp(request));
		boardService.boardinsert(boardvo);
		return "redirect:/board";

	}

	@RequestMapping(value = "/boardconfirm", method = RequestMethod.GET)
	public String boardConfirm() {
		logger.info("boardConfirm GET");
		return "redirect:/board";

	}

	@RequestMapping(value = "/boardconfirm", method = RequestMethod.POST)
	public String boardConfirm(String tableseq, String boardpassword, Model model, String buttontype) {
		logger.info("게시글 비밀번호 확인");
//		System.out.println("tableseq : " + tableseq + ", password : " + boardpassword + ", buttontype : " + buttontype);
		HashMap<String, String> hashmap = new HashMap<String, String>();
		hashmap.put("p_seq", tableseq);
		hashmap.put("p_password", boardpassword);
		// 버튼먼저 판단하고 그다음에 어떤 문제에 따라 alert
		BoardVO boardvo = boardService.boardConfirm(hashmap);

		if (buttontype.equals("boardupdatemodalbtn")) {
			if (boardvo == null) {
				model.addAttribute("passwordconfirm", "passwordfail");
			} else {
				model.addAttribute("passwordconfirm", "passwordsuccess");
				model.addAttribute("boardvo", boardvo);
				SimpleDateFormat day = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
				String stringDate = day.format(boardvo.getP_date());
				model.addAttribute("stringDate", stringDate);
			}
			return "boardupdate";
		} else if (buttontype.equals("boarddeletemodalbtn")) {
			if (boardvo == null) {
				model.addAttribute("passwordconfirm", "passwordfail");
			} else {
				model.addAttribute("passwordconfirm", "passwordsuccess");
				boardService.deleteBoard(hashmap);
			}
			return "boarddelete";
		} else {
			return "redirect:/board";
		}
	}

	@RequestMapping(value = "/boarddetail", method = RequestMethod.GET)
	public String boarddetail(Model model, int p_seq) {
		logger.info("게시판 상세");
		boardService.boardviewcountup(p_seq);
		BoardVO boardvo = boardService.boarddetail(p_seq);
		SimpleDateFormat day = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		String stringDate = day.format(boardvo.getP_date());
		System.out.println(stringDate);
		int recommendationCount = recommendationService.upconfirm(p_seq);
		int commentCount = commentService.commentList(p_seq).size() + recommentService.recommentList(p_seq).size();

		model.addAttribute("boardvo", boardvo);
		model.addAttribute("stringDate", stringDate);
		model.addAttribute("recommendationCount", recommendationCount);
		model.addAttribute("commentCount", commentCount);
		return "boarddetail";
	}

	@RequestMapping(value = "comment", method = RequestMethod.GET)
	public String comment(int p_tableseq, Model model) {
		logger.info("코멘트");
		System.out.println("게시글 번호 : " + p_tableseq);
		List<CommentVO> list = commentService.commentList(p_tableseq);
		System.out.println("댓글의 갯수 : " + list.size());
		List<String> dateList = new ArrayList<String>();
		Date time = new Date();
		for (int i = 0; i < list.size(); i++) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat todayFormat = new SimpleDateFormat("HH:mm");
			String today = dateFormat.format(time);
			if (dateFormat.format(list.get(i).getP_date()).equals(today)) {
				dateList.add(i, todayFormat.format(list.get(i).getP_date()));
			} else {
				dateList.add(i, dateFormat.format(list.get(i).getP_date()));
			}
		}

		List<RecommentVO> recommentList = recommentService.recommentList(p_tableseq);
		System.out.println("답글의 총 갯수 : " + recommentList.size());
		List<String> reDateList = new ArrayList<String>();
		for (int i = 0; i < recommentList.size(); i++) {
//			System.out.println(recommentList.get(i).toString());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat todayFormat = new SimpleDateFormat("HH:mm");
			String today = dateFormat.format(time);
			if (dateFormat.format(recommentList.get(i).getP_redate()).equals(today)) {
				reDateList.add(i, todayFormat.format(recommentList.get(i).getP_redate()));
			} else {
				reDateList.add(i, dateFormat.format(recommentList.get(i).getP_redate()));
			}
		}

		model.addAttribute("commentlist", list);
		model.addAttribute("dateList", dateList);
		model.addAttribute("recommentlist", recommentList);
		model.addAttribute("redateList", reDateList);

		return "comment";
	}

	@RequestMapping(value = "comment", method = RequestMethod.POST)
	@ResponseBody
	public String commentinsert(CommentVO commentvo, Model model, HttpServletRequest request) {
		logger.info("코멘트 입력 후 로딩");
		System.out.println("commentvo.toString() : " + commentvo.toString());
		commentvo.setP_commentip(getip.getIp(request));
		commentvo.setP_content(commentvo.getP_content().replace("\r\n", "<br>"));
		commentService.commentinsert(commentvo);

		return "comment";
	}

	@RequestMapping(value = "commentDelete", method = RequestMethod.POST)
	public String commentDelete(int p_seq, String p_grade, String p_password, int p_tableseq, Model model,
			HttpServletResponse response, HttpServletRequest request) throws IOException {
		logger.info("댓글 삭제 후 로딩");
		System.out.println("p_seq : " + p_seq + ", p_grade : " + p_grade + ", p_password : " + p_password
				+ ", p_tableseq : " + p_tableseq);
		String passwordConfirm = "";
		if (p_grade.equals("master")) {
			// 걍 바로 번호만 검사해서 삭제
			System.out.println("p_geade : " + p_grade);
			commentService.commentDeleteMaster(p_seq);
		} else {
			// 번호와 비번을 확인해서 삭제
			HashMap<String, String> deletehashmap = new HashMap<String, String>();
			deletehashmap.put("p_seq", String.valueOf(p_seq));
			deletehashmap.put("p_password", p_password);
			int deleteconfirm = commentService.commentDelete(deletehashmap);
			System.out.println("p_grade : " + p_grade);

			if (deleteconfirm == 0) {
				System.out.println("비밀번호가 다르다");
				passwordConfirm = "false";
			} else if (deleteconfirm == 1) {
				System.out.println("비밀번호가 같다");
				passwordConfirm = "true";
			} else {
				passwordConfirm = "somethingwrong";
				System.out.println("특이사항 발생");
			}

		}

		System.out.println("게시글 번호 : " + p_tableseq);
		List<CommentVO> list = commentService.commentList(p_tableseq);
		System.out.println("댓글의 갯수 : " + list.size());
		List<String> dateList = new ArrayList<String>();
		Date time = new Date();
		for (int i = 0; i < list.size(); i++) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat todayFormat = new SimpleDateFormat("HH:mm");
			String today = dateFormat.format(time);
			if (dateFormat.format(list.get(i).getP_date()).equals(today)) {
				dateList.add(i, todayFormat.format(list.get(i).getP_date()));
			} else {
				dateList.add(i, dateFormat.format(list.get(i).getP_date()));
			}
		}

		List<RecommentVO> recommentList = recommentService.recommentList(p_tableseq);
		System.out.println("답글의 총 갯수 : " + recommentList.size());
		List<String> reDateList = new ArrayList<String>();
		for (int i = 0; i < recommentList.size(); i++) {
//			System.out.println(recommentList.get(i).toString());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat todayFormat = new SimpleDateFormat("HH:mm");
			String today = dateFormat.format(time);
			if (dateFormat.format(recommentList.get(i).getP_redate()).equals(today)) {
				reDateList.add(i, todayFormat.format(recommentList.get(i).getP_redate()));
			} else {
				reDateList.add(i, dateFormat.format(recommentList.get(i).getP_redate()));
			}
		}

		model.addAttribute("commentlist", list);
		model.addAttribute("dateList", dateList);
		model.addAttribute("recommentlist", recommentList);
		model.addAttribute("redateList", reDateList);
		model.addAttribute("passwordConfirm", passwordConfirm);
		return "comment";
	}

	@RequestMapping(value = "/boardupdate", method = RequestMethod.POST)
	public String boardupdate(BoardVO boardvo, HttpServletRequest request) {
		logger.info("게시판 수정 실행");
		System.out.println(boardvo.toString());
		boardvo.setP_boardip(getip.getIp(request));
		boardvo.setP_content(boardvo.getP_content().replace("\r\n", "<br>"));
		boardService.updateBoard(boardvo);
		return "redirect:/board";
	}

//	@RequestMapping("/boarddelete")
//	public String boarddelete() {
//		return "boarddelete";
//	}

	@RequestMapping(value = "recomment", method = RequestMethod.POST)
	@ResponseBody
	public String recommentinsert(RecommentVO recommentvo, Model model, HttpServletRequest request) {
		logger.info("리코멘트 입력 후 로딩");
		System.out.println(recommentvo.toString());
		recommentvo.setP_recommentip(getip.getIp(request));
		recommentvo.setP_recontent(recommentvo.getP_recontent().replace("\r\n", "<br>"));
		recommentService.recommentinsert(recommentvo);

		return "comment";
	}

	@RequestMapping(value = "recommentDelete", method = RequestMethod.POST)
	public String recommentDelete(String p_grade, String p_repassword, int p_tableseq, int p_recommentseq, Model model,
			HttpServletResponse response, HttpServletRequest request) throws IOException {
		logger.info("답글 삭제 후 로딩");
		System.out.println("p_grade : " + p_grade + ", p_repassword : " + p_repassword + ", p_recommentseq : "
				+ p_recommentseq + ", p_tableseq : " + p_tableseq);
		String passwordConfirm = "";
		if (p_grade.equals("master")) {
			// 걍 바로 번호만 검사해서 삭제
			System.out.println("p_grade : " + p_grade);
			recommentService.recommentDeleteMaster(p_recommentseq);
		} else {
			// 번호와 비번을 확인해서 삭제
			HashMap<String, String> deletehashmap = new HashMap<String, String>();
			deletehashmap.put("p_recommentseq", String.valueOf(p_recommentseq));
			deletehashmap.put("p_repassword", p_repassword);
			int deleteconfirm = recommentService.recommentDelete(deletehashmap);
			System.out.println("p_grade : " + p_grade);

			if (deleteconfirm == 0) {
				System.out.println("비밀번호가 다르다");
				passwordConfirm = "false";
			} else if (deleteconfirm == 1) {
				System.out.println("비밀번호가 같다");
				passwordConfirm = "retrue";
			} else {
				passwordConfirm = "somethingwrong";
				System.out.println("특이사항 발생");
			}

		}

		System.out.println("게시글 번호 : " + p_tableseq);
		List<CommentVO> list = commentService.commentList(p_tableseq);
		System.out.println("댓글의 갯수 : " + list.size());
		List<String> dateList = new ArrayList<String>();
		Date time = new Date();
		for (int i = 0; i < list.size(); i++) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat todayFormat = new SimpleDateFormat("HH:mm");
			String today = dateFormat.format(time);
			if (dateFormat.format(list.get(i).getP_date()).equals(today)) {
				dateList.add(i, todayFormat.format(list.get(i).getP_date()));
			} else {
				dateList.add(i, dateFormat.format(list.get(i).getP_date()));
			}
		}

		List<RecommentVO> recommentList = recommentService.recommentList(p_tableseq);
		System.out.println("답글의 총 갯수 : " + recommentList.size());
		List<String> reDateList = new ArrayList<String>();
		for (int i = 0; i < recommentList.size(); i++) {
//			System.out.println(recommentList.get(i).toString());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat todayFormat = new SimpleDateFormat("HH:mm");
			String today = dateFormat.format(time);
			if (dateFormat.format(recommentList.get(i).getP_redate()).equals(today)) {
				reDateList.add(i, todayFormat.format(recommentList.get(i).getP_redate()));
			} else {
				reDateList.add(i, dateFormat.format(recommentList.get(i).getP_redate()));
			}
		}

		model.addAttribute("commentlist", list);
		model.addAttribute("dateList", dateList);
		model.addAttribute("recommentlist", recommentList);
		model.addAttribute("redateList", reDateList);
		model.addAttribute("passwordConfirm", passwordConfirm);
		return "comment";
	}

	@RequestMapping(value = "recommendation", method = RequestMethod.GET)
	public String recommendation(int p_tableseq, Model model) {
		logger.info("추천 로딩");
		List<HashMap<String, String>> recommendationList = recommendationService.bring(p_tableseq);
		if (recommendationList.size() == 0) {
			HashMap<String, String> hashmap1 = new HashMap<String, String>();
			hashmap1.put("p_recommendation", "YES");
			hashmap1.put("count", "0");
			HashMap<String, String> hashmap2 = new HashMap<String, String>();
			hashmap2.put("p_recommendation", "NO");
			hashmap2.put("count", "0");
			recommendationList.add(0, hashmap1);
			recommendationList.add(1, hashmap2);
		} else if (recommendationList.size() == 1) {
			if (recommendationList.get(0).get("p_recommendation").equals("YES")) {
				HashMap<String, String> hashmap2 = new HashMap<String, String>();
				hashmap2.put("p_recommendation", "NO");
				hashmap2.put("count", "0");
				recommendationList.add(1, hashmap2);
			} else {
				recommendationList.add(1, recommendationList.get(0));
				HashMap<String, String> hashmap1 = new HashMap<String, String>();
				hashmap1.put("p_recommendation", "YES");
				hashmap1.put("count", "0");
				recommendationList.add(0, hashmap1);
			}
		}

		model.addAttribute("recommendationList", recommendationList);
		return "recommendation";
	}

	@RequestMapping(value = "recommendation", method = RequestMethod.POST)
	@ResponseBody
	public String recommendationInsert(int p_tableseq, String p_recommendation, Model model) {
		logger.info("추천 삽입하기");
		if (session == null) {
			model.addAttribute("situation", "notLogin");
			System.out.println("세션이 없다");
			return "notLogin";
		} else {
			UserVO uservo = (UserVO) session.getAttribute("uservo");
			RecommendationVO recommendationvo = new RecommendationVO();
			recommendationvo.setP_recommendation(p_recommendation);
			recommendationvo.setP_tableseq(p_tableseq);
			recommendationvo.setP_writer(uservo.getP_userid());
			System.out.println("recommendationInsert : " + recommendationvo.toString());
			int count = recommendationService.findDuplicates(recommendationvo);

			System.out.println("recommendationInsert : " + count);
			if (count >= 1) {
				if (p_recommendation.equals("YES")) {
					model.addAttribute("situation", "haveYES");
					return "haveYES";
				} else {
					model.addAttribute("situation", "haveNO");
					return "haveNO";
				}
			} else {
				recommendationService.insertrecommendation(recommendationvo);
				if (p_recommendation.equals("YES")) {
					return "successYES";
				} else if (p_recommendation.equals("NO")) {
					return "successNO";
				} else {
					return "somethingWrong";
				}
			}
		}
	}

	@RequestMapping(value = "/myPage", method = RequestMethod.GET)
	public String myPage(Model model) {
		logger.info("마이페이지");
		UserVO sessionuservo = null;
		if (session == null) {
			return "redirect:/signin";
		} else {
			sessionuservo = (UserVO) session.getAttribute("uservo");
		}
		if (sessionuservo == null) {
			return "redirect:/";
		} else {
			UserVO getMyAccount = signupService.loadmyaccount(sessionuservo.getP_userid());
			System.out.println("getMyAccount : " + getMyAccount.toString());
			model.addAttribute("getMyAccount", getMyAccount);
			return "myPage";
		}
	}

	@RequestMapping(value = "/passwordConfirm", method = RequestMethod.POST)
	@ResponseBody
	public String passwordConfirm(String password, String id) {
		logger.info("비밀번호 검사");
		UserVO uservo = signupService.signin(id, password);
		if (uservo == null) {
			System.out.println("없어");
			return "false";
		} else {
			System.out.println("있어");
			return "true";
		}
	}

	@RequestMapping(value = "/accountDelete", method = RequestMethod.POST)
	@ResponseBody
	public String accountDelete(String id, String password) {
		if (signupService.userDelete(id, password) == 1) {
			session.invalidate();
			return "deleteSuccess";
		} else {
			return "deleteFail";
		}
	}

	@RequestMapping(value = "/accountUpdate", method = RequestMethod.GET)
	public String accountUpdate(String id, Model model) {
		logger.info("회원정보 수정");
		UserVO uservo = signupService.loadmyaccount(id);
		if (uservo == null) {
			return "redirect:/";
		}
		model.addAttribute("uservo", uservo);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String yourBirthday = dateFormat.format(uservo.getP_birth());
		model.addAttribute("yourBirthday", yourBirthday);
		return "accountupdate";
	}

	@RequestMapping(value = "/accountUpdate", method = RequestMethod.POST)
	public String accountUpdate(UserVO uservo, String birth, Model model) throws ParseException {
		logger.info("회원정보 수정 POST");
		UserVO sessionuservo = (UserVO) session.getAttribute("uservo");
		uservo.setP_userid(sessionuservo.getP_userid());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		uservo.setP_birth(format.parse(birth));
		System.out.println(uservo.toString());
		signupService.accountUpdate(uservo);
		return "redirect:/myPage";
	}

	@RequestMapping(value = "/homepagemaster", method = RequestMethod.GET)
	public String homepagemaster() {
		logger.info("관리자 페이지");
		return "homepagemaster";
	}

	@RequestMapping(value = "/accountmanagement", method = RequestMethod.GET)
	public String accountmanagement(Model model,
			@RequestParam(value = "grade", required = false, defaultValue = "all") String grade,
			@RequestParam(value = "sortType", required = false, defaultValue = "p_userid") String sortType,
			@RequestParam(value = "orderType", required = false, defaultValue = "Ascending") String orderType) {
		logger.info("회원정보 불러오기");
//		System.out.println("grade : " + grade + ", sortType : " + sortType + ", orderType : " + orderType);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("sortType", sortType);
		if (orderType.equals("Descending")) {
			map.put("orderType", "desc");
		} else {
			map.put("orderType", "asc");
		}
		List<UserVO> userList = null;
		if (grade.equals("all")) {
			userList = signupService.userList(map);
		} else {
			map.put("grade", grade);
			userList = signupService.userGradeList(map);
		}
		List<String> userDate = new ArrayList<String>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
		for (int i = 0; i < userList.size(); i++) {
			userDate.add(i, dateFormat.format(userList.get(i).getP_date()));
		}
		model.addAttribute("userList", userList);
		model.addAttribute("userDate", userDate);
		return "accountmanagement";
	}

	@RequestMapping(value = "gradeupdate", method = RequestMethod.POST)
	@ResponseBody
	public String gradeUpdate(String grade, String userid) {
		logger.info("회원등급 변경하기");
		HashMap<String, String> hashmap = new HashMap<String, String>();
		hashmap.put("grade", grade);
		hashmap.put("userid", userid);
		int result = signupService.gradeUpdate(hashmap);
//		System.out.println("gradeUpdate : " + result);
		if (result == 1) {
			return "updateSuccess";
		} else {
			return "updateFail";
		}
	}

	@RequestMapping(value = "accountdeletemaster", method = RequestMethod.POST)
	@ResponseBody
	public String accountDeleteMaster(String userid) {
		logger.info("회원 강제탈퇴");
		int result = signupService.userDeleteMaster(userid);
		if (result == 1) {
			return "deleteSuccess";
		} else {
			return "DeleteFail";
		}
	}

	@RequestMapping(value = "boardstatistics", method = RequestMethod.GET)
	public String boardStatistics(Model model) {
		logger.info("게시글 통계");
		return "boardstatistics";
	}

	@RequestMapping(value = "boardChart", method = RequestMethod.GET)
	@ResponseBody
	public List<HashMap<String, String>> boardChart(
			@RequestParam(value = "chartType", required = false, defaultValue = "board") String chartType,
			@RequestParam(value = "chartYear", required = true) String chartYear) {
//		System.out.println("chartType : " + chartType + ", chartYear : " + chartYear);
		List<HashMap<String, String>> boardhashmap = new ArrayList<HashMap<String, String>>();
		if (chartType.equals("board")) {
			boardhashmap = boardService.boardChart(chartYear);
		} else if (chartType.equals("account")) {
			boardhashmap = signupService.accountchart(chartYear);
		} else if (chartType.equals("comment")) {
			boardhashmap = commentService.commentChart(chartYear);
		}
		List<HashMap<String, String>> monthArray = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> addList = new HashMap<String, String>();
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < boardhashmap.size(); j++) {
				if (i + 1 == Integer.parseInt(boardhashmap.get(j).get("month").substring(5))) {
					addList.put("month", boardhashmap.get(j).get("month"));
					addList.put("count", String.valueOf(boardhashmap.get(j).get("count")));
				}
			}
			if (addList.isEmpty()) {
				if (i < 9) {
					addList.put("month", chartYear + "-0" + (i + 1));
					addList.put("count", "0");
				} else {
					addList.put("month", chartYear + "-" + (i + 1));
					addList.put("count", "0");
				}
			}
//			System.out.println("addList : " + addList.toString());
			monthArray.add(i, (HashMap<String, String>) addList.clone());
			addList.clear();
		}
		boardhashmap.clear();
		return monthArray;
	}

}// HomeController end
