package ezen5project.java.EzenRentCar.controller;

import java.util.ArrayList;

import ezen5project.java.EzenRentCar.model.dao.MyMenu;
import ezen5project.java.EzenRentCar.model.dto.MemberDto;
import ezen5project.java.EzenRentCar.model.dto.RentRecordDto;

// 마이메뉴 클래스(Controller)
public class Mymenu {
	
	private static Mymenu mymenu = new Mymenu();
	 
	public static Mymenu getInstance() {
			return mymenu;
	}
		
	private Mymenu() {}
	
	// --------- 메소드 ----------------------------------------
	
	// 내정보 조회
	public MemberDto memberInfo() {
		return MyMenu.getInstance().memberInfo(MemberController.getInstance().getLoginSession());
	}
	
	// 대여내역 조회
	public ArrayList<RentRecordDto> rentRecord() {
		return MyMenu.getInstance().rentRecord(MemberController.getInstance().getLoginSession());
	}
	
	// 개인정보변경
	public int infoModify(int ch, String text) {
		int result = 0;
		if(ch == 1) {result = MyMenu.getInstance().infoModify(MemberController.getInstance().getLoginSession(), "mpw", text);}
		if(ch == 2) {result = MyMenu.getInstance().infoModify(MemberController.getInstance().getLoginSession(), "mad", text);}
		if(ch == 3) {result = MyMenu.getInstance().infoModify(MemberController.getInstance().getLoginSession(), "mph", text);}
		
		return result;
	}
	
	// 비밀번호 확인
	public int passwordCheck(String pw) {
		
		if(pw.length() < 1 || pw.length() > 15) {return 3;}
		
		boolean result =
				MyMenu.getInstance().passwordCheck(MemberController.getInstance().getLoginSession(), pw);
		if(result) {
			return 1;
		}else {return 2;}
	}
	
	// 회원탈퇴
	public boolean memberDelete() {
		
		return MyMenu.getInstance().memberDelete(MemberController.getInstance().getLoginSession());
	}

}
