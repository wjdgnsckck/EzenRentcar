package ezen5project.java.EzenRentCar;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import ezen5project.java.EzenRentCar.controller.CouponController;
import ezen5project.java.EzenRentCar.controller.MemberController;
import ezen5project.java.EzenRentCar.view.ManagerView;

// 정해진 시간에 회원들에게 쿠폰을 나눠주는 기능
public class CouponThread  extends Thread{
	
	@Override
	public void run() {
		boolean result = false;
		
		while(true) {
			LocalTime now = LocalTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
			String formatedNow = now.format(formatter);

			if(ManagerView.getInstance().isCouponState() && 
					formatedNow.equals(ManagerView.getInstance().getCtime())) {
				result = CouponController.getInstance().couponSign(ManagerView.getInstance().getCno());
				if(result) {
					System.out.println("****** 쿠폰지급완료! ******");
					break;
				}
			}
		}
	}
}


