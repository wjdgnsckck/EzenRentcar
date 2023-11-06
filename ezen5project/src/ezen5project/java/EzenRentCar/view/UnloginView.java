package ezen5project.java.EzenRentCar.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import ezen5project.java.EzenRentCar.controller.MemberController;


// 로그인 전 페이지(로그인 / 회원가입 / 아이디찾기 / 비밀번호 찾기)
public class UnloginView {
	
	private static UnloginView unloginView = new UnloginView();
	public static UnloginView getInstance() {
		return unloginView;
	}
	
	Scanner sc = new Scanner(System.in);
	
	private UnloginView() {}

	// 로그인 전 기능 기능실행
	// 1.회원가입  2.로그인  3.아이디 찾기  4.비밀번호 찾기

	public void loginMenu() {

		
		while(true) {
			System.out.println("=================== eZen RentCar ====================");
			System.out.println("\n 1.회원가입 2.로그인 3.아이디 찾기 4.비밀번호 찾기 \n");
			System.out.println("=====================================================");
			int ch = 0;
			try {
				System.out.print("입력 >> "); ch = sc.nextInt();
			} catch (InputMismatchException e) {
				System.err.println("안내) 1~4의 숫자를 입력해주세요.");
			} catch (Exception e) {
				System.out.println(e);
			}
			sc.nextLine();
			
			if( ch==1 ) signUp();	// 회원가입
			if( ch==2 ) login();	// 로그인
			if( ch==3 ) searchId();	// 아이디 찾기
			if( ch==4 ) searchPw();	// 비밀번호 찾기
			
		}
	}
	// 1 회원가입 view
	public void signUp() {
		// 이름입력
		System.out.println("성명을 입력해주십시오 > ");
		String name = sc.next();
		// 주민번호입력
		System.out.println("주민번호 앞 6자리를 입력해주십시오 > ");
		String sno1 = sc.next();
		System.out.println("주민번호 뒤 7자리를 입력해주십시오 > ");
		String sno2 = sc.next();
		String sno = sno1 + "-" + sno2;
		// 아이디 입력
		System.out.println("아이디를 입력해주십시오 > ");
		System.out.println("6~15글자");
		String id = sc.next();
		// 비밀번호 입력
		System.out.println("비밀번호를 입력해주십시오 > ");
		System.out.println("8~20글자");
		String pw = sc.next();
		// 주소 입력
		System.out.println("거주지역을 입력해주십시오");
		System.out.println("예) 서울, 부산, 안산");
		String adress = sc.next();
		// 전화번호 입력
		System.out.println("전화번호를 입력해주십시오");
		System.out.println("'-' 포함");
		String phone = sc.next();
		
		int result = MemberController.getInstance().signUp(name, sno, id, pw, adress, phone);
		
		// 유효성검사) 이름에 숫자 포함될 시 실패
		if(result == 1) {
			System.out.println("실패) 이름에 숫자가 포함되어 있습니다");
			return;
		}
		// 유효성검사) 주민번호 앞자리 6자리가 아닌 경우 실패
		if(result == 2) {
			System.out.println("실패) 주민번호 앞 6자리를 입력해주십시오");
			return;
		}
		// 유효성검사) 주민번호 뒷자리 7자리가 아닌 경우 실패
		if(result == 3) {
			System.out.println("실패) 주민번호 뒤 7자리를 입력해주십시오");
			return;
		}
		// 유효성검사) 비밀번호가 8이상 20자리 이하가 아닌 경우 실패
		if(result == 4) {
			System.out.println("실패) 비밀번호를 8자리 이상, 20자리 이하로 입력하십시오");
			return;
		}
		// 유효성검사) 비밀번호가 8이상 20자리 이하가 아닌 경우 실패
		if(result == 5) {
			System.out.println("실패) 아이디를 6자리 이상, 15자리 이하로 입력하십시오");
			return;
		}
		// 유효성검사) 비밀번호가 8이상 20자리 이하가 아닌 경우 실패
		if(result == 6) {
			System.out.println("실패) 전화번호 상 '-'를 올바르게 입력하여 주십시오");
			return;
		}
		
		
		 
		// 아이디 중복검사
		if(result == 101) {
			System.out.println("실패) 이미 가입된 아이디입니다");
			return;
		}
		// 주민번호 중복검사
		if(result == 102) {
			System.out.println("실패) 이미 가입된 회원입니다");
			return;
		}
		// 전화번호 중복검사
		if(result == 103) {
			System.out.println("실패) 이미 가입된 전화번호입니다");
			return;
		}
		
		
		// 회원가입 정상실행
		if(result == 0) System.out.println("회원가입이 완료되었습니다");
		// 회원가입정보 DB저장 실패
		else if(result == 1000) System.out.println("실패)서버에 회원정보가 저장되지 않았습니다 [관리자문의]");
		
	}
	
	// 2-1 로그인 view
	public void login() {
		System.out.println("아이디 > ");
		String id = sc.next();
		System.out.println("비밀번호 > ");
		String pw = sc.next();
		
		//관리자로그인
		if(id.equals("admin") && pw.equals("1234")) {
			int admin =MemberController.getInstance().adminLogin();
			if(admin == -1) {
				ManagerView.getInstance().managerView();
			}
			return;
		}
		
		int result = MemberController.getInstance().login( id, pw );
		
		// 아이디, 비밀번호가 일치할 경우 MemberView클래스 이동
		if(result != 0) {
			System.out.println("로그인이 완료되었습니다");
			
			// MemberView.		로그인 이후 화면 메소드 실행
			MemberView.getInstance().loginPage();
			
		} else {
			System.out.println("로그인 실패) 아이디 비밀번호를 확인해주세요");
		}
	}
	
	// 3 아이디 찾기
	public void searchId() {
		
		// 이름입력
		System.out.println("성명을 입력해주십시오 > ");
		String name = sc.next();
		
		// 주민번호 입력
		System.out.println("주민번호 앞 6자리를 입력해주십시오 > ");
		String sno1 = sc.next();
		System.out.println("주민번호 뒤 7자리를 입력해주십시오 > ");
		String sno2 = sc.next();
		String sno = sno1 + "-" + sno2;
		
		// 전화번호 입력
		System.out.println("전화번호를 입력해주십시오");
		System.out.println("'-' 포함");
		String phone = sc.next();
		
		String result = MemberController.getInstance().searchId(name, sno, phone);
		
		// 유효성 검사
		if(result.equals("1")) {
			System.out.println("실패) 이름에 숫자가 포함되어 있습니다");
			return;
		}
		if(result.equals("2")) {
			System.out.println("실패) 주민번호 앞 6자리를 입력해주십시오");
			return;
		}
		if(result.equals("3")) {
			System.out.println("실패) 주민번호 뒤 7자리를 입력해주십시오");
			return;
		}
		
		
		
		if(!result.equals("0")) {
			System.out.println("회원님의 아이디는 "+result+" 입니다");
		} else {
			System.out.println("입력하신 정보가 회원정보와 일치하지 않습니다");
		}
	}
	
	// 4 비밀번호 찾기
	public void searchPw() {
		
		// 이름입력
		System.out.println("성명을 입력해주십시오 > ");
		String name = sc.next();
		
		
		// 아이디 입력
		System.out.println("아이디를 입력해주십시오 > ");
		String id = sc.next();
		
		// 주민번호 입력
		System.out.println("주민번호 앞 6자리를 입력해주십시오 > ");
		String sno1 = sc.next();
		System.out.println("주민번호 뒤 7자리를 입력해주십시오 > ");
		String sno2 = sc.next();
		String sno = sno1 + "-" + sno2;
		
		String result = MemberController.getInstance().searchPw(name, id, sno);
		
		// 유효성 검사
		if(result.equals("1")) {
			System.out.println("실패) 이름에 숫자가 포함되어 있습니다");
			return;
		}
		if(result.equals("2")) {
			System.out.println("실패) 주민번호 앞 6자리를 입력해주십시오");
			return;
		}
		if(result.equals("3")) {
			System.out.println("실패) 주민번호 뒤 7자리를 입력해주십시오");
			return;
		}
		
		
		
		if(!result.equals("0")) {
			System.out.println("회원님의 비밀번호는 "+result+" 입니다");
		} else {
			System.out.println("입력하신 정보가 회원정보와 일치하지 않습니다");
		}
		
		
		
	}

	
}
