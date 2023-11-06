package ezen5project.java.EzenRentCar.view;

import java.util.ArrayList;

import ezen5project.java.EzenRentCar.controller.CouponController;
import ezen5project.java.EzenRentCar.model.dto.CouponSignDto;

// 쿠폰기능 클래스
public class CouponView {
	
	// 싱글톤
	private static CouponView couponview = new CouponView();
	
	public static CouponView getInstance() {
		return couponview;
	}
	
	private CouponView() {}
	
	// 쿠폰등록
	public void couponSign() {
		System.out.println("등록하실 쿠폰번호를 입력해주세요");
		System.out.print("입력 >> ");	String coupon = MyMenu.getInstance().sc.next();
		boolean result = false;	// 쿠폰 등록 
		int couponCk = 0;	// 쿠폰번호확인 변수
		couponCk = CouponController.getInstance().couponCheck(coupon);
		
		if(couponCk > 0) {
			result = 
					CouponController.getInstance().couponSign(couponCk);
		}else if(couponCk == -1) {
			System.err.println("안내) 존재하지 않는 쿠폰번호입니다."); return;
		}else if(couponCk == -2) {
			System.err.println("안내) 쿠폰번호는 1~50글자내로 입력해주세요."); return;
		}
		
		if(result) {System.out.println("안내) 쿠폰이 등록되었습니다.");}
		else {System.out.println("안내) 쿠폰등록에 실패하였습니다. 관리자에게 문의해주세요");}
	}
	
	// 쿠폰보유내역 출력
	public void couponPrint() {
		System.out.println("\n================ 보유 쿠폰 내역 ================");
		
		ArrayList<CouponSignDto> list = new ArrayList<>();
		list  = CouponController.getInstance().couponPrint();
		System.out.printf("%s\t %s\t %s\t\t %s\n", "쿠폰이름", "할인율", "등록일", "사용일");
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getTusedate() == null) {list.get(i).setTusedate("미사용");}
			System.out.printf("%s\t %d%%\t %s\t %s\n", list.get(i).getDname(), list.get(i).getDpercentage(),
					list.get(i).getTupdate(), list.get(i).getTusedate());
		}
		
		
	}
	

}













