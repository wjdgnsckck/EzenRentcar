package ezen5project.java.EzenRentCar.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import ezen5project.java.EzenRentCar.controller.CouponController;
import ezen5project.java.EzenRentCar.controller.Mymenu;
import ezen5project.java.EzenRentCar.model.dto.MemberDto;
import ezen5project.java.EzenRentCar.model.dto.RentRecordDto;

// 마이메뉴 클래스(View)
public class MyMenu {
	
	// MyMenu 싱글톤
	private static MyMenu mymenu = new MyMenu();
	
	public static MyMenu getInstance() {
		return mymenu;
	}
	
	private MyMenu() {}
	
	static Scanner sc = new Scanner(System.in);
	
	// ------- 메소드 --------------------------------
	
	// 마이메뉴 화면 프론트
	public void myMenuMain() {
		// 마이메뉴 프론트 무한루프
		while(true) {
			
			System.out.println("\n===================================== My Menu "
					+ "===========================================\n");
			System.out.println(" 1. 내정보 조회  2. 대여내역 조회  3. 쿠폰등록  4. 개인정보변경  5. 회원탈퇴  6. 뒤로가기");
			System.out.println("\n================================="
					+ "========================================================");
			
			int ch = 0;
			try {
				System.out.print("입력 >> "); 				
				ch = sc.nextInt();
			} 
			catch (InputMismatchException e) {
				System.err.println("안내) 1~6의 숫자를 입력해주세요");
			}
			sc.nextLine();
			
			if(ch == 1) { memberInfo(); }		// 내정보 조회 함수 실행
			if(ch == 2) { rentRecord(); }		// 대여내역 조회 함수 실행
			if(ch == 3) { CouponView.getInstance().couponSign(); }				// 쿠폰등록 함수 호출
			if(ch == 4) { infoModify(); }			// 개인정보 변경 함수 실행
			if(ch == 5) { memberDelete(); }	// 회원탈퇴 함수 실행
			if(ch == 6) {return;}
			
		}
	}
	
	// 내정보 조회
	public void memberInfo() {
		System.out.println("================= My Info =================");
		
		// Controller에서 MemberDto 객체를 반환받음
		MemberDto result = 
				Mymenu.getInstance().memberInfo();
		
		System.out.printf("%s\t\t : %s\n", "이름", result.getMname());
		System.out.printf("%s\t\t : %s\n", "아이디", result.getMid());
		System.out.printf("%s\t\t : %s\n", "주소", result.getMad());
		System.out.printf("%s\t : %s\n", "전화번호", result.getMph());
		
		// 쿠폰 보유내역 출력 함수 호출
		CouponView.getInstance().couponPrint();
		System.out.println();
		
	}
	
	// 대여내역 조회
	public void rentRecord() {
		// RentRecord 객체 리스트를 반환받음
		ArrayList<RentRecordDto> result = 
				Mymenu.getInstance().rentRecord();
		
		System.out.printf("%s\t %s\t %s\t\t %s\t %s\n", "대여기록", "차량이름", "대여일", "반납예정일", "반납완료일");
		for(int i = 0; i < result.size(); i++) {
			if(result.get(i).getLcompletelog() == null) {result.get(i).setLcompletelog("대여중");}
			System.out.printf("%d\t\t %s\t %s\t %s\t %s\n", i+1, result.get(i).getKname(),
					result.get(i).getLstartlog(), result.get(i).getLendlog(), result.get(i).getLcompletelog());
		}
		System.out.println();
	}
	
	// 개인정보변경
	public void infoModify() {
		int pwCheck = passwordCheck();
		if(pwCheck == 1) {
			int result = 0;
			while(true) {	
				System.out.println("\n\n=================== 개인정보 변경 ===================");
				System.out.print("1. 비밀번호 변경 2. 주소 변경 3. 전화번호 변경 4. 완료\n");
				
				int ch = 0;
				try {
					System.out.print("메뉴 입력 >> "); 				
					ch = sc.nextInt();
				} 
				catch (InputMismatchException e) {
					System.err.println("안내) 1~4의 숫자를 입력해주세요");
					return;
				}
				sc.nextLine();
				
				if(ch == 4) {return;}
				System.out.print("수정사항 입력 >> ");String text = sc.next();
				if(ch == 1) {result = Mymenu.getInstance().infoModify(ch, text);}
				else if(ch == 2) {result = Mymenu.getInstance().infoModify(ch, text);}
				else if(ch == 3) {result = Mymenu.getInstance().infoModify(ch, text);}
				else {System.err.println("안내) 1 ~ 4 사이의 값을 입력해주세요."); continue;};
				
				if(result == 1) {System.out.println("안내) 변경사항 저장 완료");}
				else if(result == 2) {System.err.println("안내) 변경사항 저장 실패. 관리자에게 문의해주세요");}
				else if(result == 3) {System.err.println("안내) 변경전 값과 똑같은 값을 입력할 수 없습니다.");}

			}
		}else if(pwCheck == 2){
			System.err.println("안내) 비밀번호가 맞지 않습니다."); return;
		}else if(pwCheck == 3) {
			System.err.println("안내) 1~15글자 사이로 입력해주세요."); return;
		}
		
	}
	
	// 비밀번호 확인
	public int passwordCheck() {
		System.out.println("\n\n========= 비밀번호 확인 =========");
		System.out.print("비밀번호를 입력해주세요 >> "); String pw = sc.next();
		
		int result = 
				Mymenu.getInstance().passwordCheck(pw);
		
		return result;
		
	}
	
	// 회원탈퇴
	public void memberDelete() {
		int pwCheck = passwordCheck();
		if(pwCheck == 1) {
			boolean result = 
					Mymenu.getInstance().memberDelete();
			if(result) {
				System.out.println("회원탈퇴가 완료되었습니다.");
			}else {
				System.err.println("안내) 회원탈퇴에 실패하였습니다. 관리자에게 문의해주세요.");
			}
			
		}else if(pwCheck == 2){
			System.err.println("안내) 비밀번호가 맞지 않습니다."); return;
		}else if(pwCheck == 3) {
			System.err.println("안내) 1~15글자 사이로 입력해주세요."); return;
		}
	}

}























