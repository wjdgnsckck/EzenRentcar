package ezen5project.java.EzenRentCar.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import ezen5project.java.EzenRentCar.controller.MemberController;

// 회원기능( 등) 클래스
public class MemberView {
	private static MemberView memberView = new MemberView();
	public static MemberView getInstance() {
		return memberView;
	} 
	private MemberView() {}


	 
	private Scanner scanner = new Scanner(System.in);
	
	public void loginPage() {
		
		while (MemberController.getInstance().getLoginSession() != 0) {
			
			// 로그인 직후 바로 오늘의 차량 이벤트창 출력
				// 오늘의 차량은 로그인 시 단 한 번만 출력됨
				// 이벤트창 출력 메서드가 실행될 시 eventSwitch는 false로 변경되어
				// 더 이상 실행되지 않음
				SuggestView.getInstance().runEvent();
			
			int mno = MemberController.getInstance().getLoginSession();
			
			String result = MemberController.getInstance().loginName(mno);
			if(result == null) {
				System.out.println("시스템 오류] 이름을 찾을수 없습니다.");
			}

			System.out.println("["+ result +"] 님 안녕하세요!");
			
			System.out.println("\n========================================================");
			System.out.println("\n1.렌트하기 2.반납하기 3.마이메뉴 4.후기게시판 0.로그아웃\n");
			System.out.println("========================================================");
			int ch = -1;
			try {
				System.out.print("입력 >> "); 				
				ch = scanner.nextInt();
			} 
			catch (InputMismatchException e) {
				System.err.println("안내) 0~4의 숫자를 입력해주세요");
			}
			scanner.nextLine();
			
			if(ch==1) { RentView.getInstance().region(); }
			else if(ch==2) {RentView.getInstance().carReturn();}	
			else if(ch==3) { MyMenu.getInstance().myMenuMain(); }
			else if(ch==4) { BoardView.getinstance().BoardView(); }
			else if(ch==0) { 
				System.out.println("로그아웃 되었습니다. 안녕히가세요");
				MemberController.getInstance().logOut();
				}
			
		
		}//wh end
		
	}

	

}
