package ezen5project.java.EzenRentCar.controller;

import java.util.ArrayList;

import ezen5project.java.EzenRentCar.model.dao.CouponDao;
import ezen5project.java.EzenRentCar.model.dto.CouponSignDto;

// 쿠폰기능 클래스
public class CouponController {
	
	// 싱글톤
	private static CouponController couponController = new CouponController();
	
	public static CouponController getInstance() {
		return couponController;
	}
	  
	private CouponController() {}
	
	// 쿠폰등록
	public boolean couponSign(int dno) {
		
		boolean result = 
				CouponDao.getInstance().couponSign(MemberController.getInstance().getLoginSession(), dno);
		
		return result;
	}
	
	// 쿠폰번호 확인
	public int couponCheck(String coupon) {
		if(coupon.length() < 0 || coupon.length() > 50) {return -2;}
		
		int result = 
				CouponDao.getInstance().couponCheck(coupon);
		
		return result;
	}
	
	// 쿠폰보유내역 출력
	public ArrayList<CouponSignDto> couponPrint() {
		
		return CouponDao.getInstance().couponPrint(MemberController.getInstance().getLoginSession());
	}

}
