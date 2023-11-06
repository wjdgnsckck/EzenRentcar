package ezen5project.java.EzenRentCar.controller;

import ezen5project.java.EzenRentCar.model.dao.MemberDao;
import ezen5project.java.EzenRentCar.model.dto.MemberDto;

// 회원기능 클래스(로그인/회원가입/아이디찾기/비밀번호 찾기 등)
public class MemberController {
	private static MemberController memberController = new MemberController();
	public static MemberController getInstance() {
		return memberController;
	} 
	private MemberController() {};
	
	
	// 현재 로그인 상태
	// 0 			: 로그인 안한상태
	// 0이 아닌 경우 	: 로그인한 회원번호 
	private int loginSession = 0;
	public int getLoginSession() {
		return loginSession;
	}
	
	
	// 1. 회원가입 기능
		// 정수 0을 UnloginView클래스에 반환해야만 회원가입 진행
	public int signUp(String name, String sno, String id, String pw, String adress, String phone) {
		
	// 회원가입 유효성 검사 1 : 잘못된 입력값 확인
		// 유효성검사 1-1 입력받은 이름 중 숫자가 포함되어 있는 경우 1 반환하여 회원가입 실패
		for(int i=0; i<name.length(); i++) {
				// isDigit	: 숫자면 true 문자면 false 리턴
			if( Character.isDigit(name.charAt(i)) ) {
				return 1;
			}
		}
		// 유효성검사 1-2 아이디가 6이상 15자리 이하가 아닌 경우 5 반환하여 실패 
		if( id.length()<6 || id.length()>15 ) {
			return 5;
		}
		// 유효성검사 2 주민번호 앞자리가 6자리가 아닌 경우 2 반환하여 실패
		if( sno.split("-")[0].length()!=6 ) {
			return 2;
		}
		// 유효성검사 3 주민번호 앞자리가 6자리가 아닌 경우 3 반환하여 실패
		if( sno.split("-")[1].length()!=7 ) {
			return 3;
		}
		// 유효성검사 4 비밀번호가 8이상 20자리 이하가 아닌 경우 4 반환하여 실패
		if( pw.length()<8 || pw.length()>20 ) {
			return 4;
		}
		// 유효성검사 5 전화번호에 '-'를 2개 포함하지 않은 경우 6반환하여 실패
		int phoneCountCheck = 2;
		for(int i=0; i<phone.length(); i++) {
			if( phone.charAt(i) == '-') phoneCountCheck--;
		}
		if( phoneCountCheck != 0 ) return 6;
		
		
		
		
		
	// 회원가입 중복 검사 2 : 아이디 중복검사, 주민번호 중복검사, 휴대전화 중복검사
		if( MemberDao.getInstance().duplicationCheck("mid", id) ) return 101;
		if( MemberDao.getInstance().duplicationCheck("msno", sno) ) return 102;
		if( MemberDao.getInstance().duplicationCheck("mph", phone) ) return 103;
		 
		
	// 회원가입 정상실행 : 모든 유효성 검사 통과시에만 실행 
		MemberDto memberDto = new MemberDto(name, sno, id, pw, adress, phone);
		return MemberDao.getInstance().signUp(memberDto);
		
	}
	
	// 2-1 로그인 기능
	public int login(String id, String pw) {
		
		int result = MemberDao.getInstance().login(id, pw);
		if(result != 0) {
			this.loginSession = result;
			return result;
		}
		else return result; 
		
	}
	// 2-2 로그아웃 기능
	public void logOut() {
		this.loginSession = 0;
	}
	
	
	
	
	public String searchId(String name, String sno, String phone) {
		
	// 아이디 찾기 입력 유효성 검사
		// 유효성검사 1 입력받은 이름 중 숫자가 포함되어 있는 경우 1 반환하여 회원가입 실패
		for(int i=0; i<name.length(); i++) {
				// isDigit	: 숫자면 true 문자면 false 리턴
			if( Character.isDigit(name.charAt(i)) ) {
				return "1";
			}
		}
		// 유효성검사 2 주민번호 앞자리가 6자리가 아닌 경우 2 반환하여 실패
		if( sno.split("-")[0].length()!=6 ) {
			return "2";
		}
		// 유효성검사 3 주민번호 앞자리가 6자리가 아닌 경우 3 반환하여 실패
		if( sno.split("-")[1].length()!=7 ) {
			return "3";
		}
		
		String mid = MemberDao.getInstance().searchId(name, sno, phone);
		
		return mid;
		
		
	}
	public String searchPw(String name, String id, String sno) {
		
	// 비밀번호 찾기 입력 유효성 검사
		// 유효성검사 1 입력받은 이름 중 숫자가 포함되어 있는 경우 1 반환하여 회원가입 실패
		for(int i=0; i<name.length(); i++) {
				// isDigit	: 숫자면 true 문자면 false 리턴
			if( Character.isDigit(name.charAt(i)) ) {
				return "1";
			}
		}
		// 유효성검사 2 주민번호 앞자리가 6자리가 아닌 경우 2 반환하여 실패
		if( sno.split("-")[0].length()!=6 ) {
			return "2";
		}
		// 유효성검사 3 주민번호 앞자리가 6자리가 아닌 경우 3 반환하여 실패
		if( sno.split("-")[1].length()!=7 ) {
			return "3";
		}
		
		String mpw = MemberDao.getInstance().searchPw(name, sno, id);
		
		return mpw;
	}
	//로그인 후 이름 출력
	public String loginName( int mno ) {
		
		String result = MemberDao.getInstance().loginName(mno);
		return result;
		
	}
	//관리자 로그인 세션 = -1
	public int adminLogin() {
		
		int result = -1;
		this.loginSession = result;
		return result;
		
		
	}
	
}


























