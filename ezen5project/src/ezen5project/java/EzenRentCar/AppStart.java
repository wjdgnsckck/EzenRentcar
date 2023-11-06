package ezen5project.java.EzenRentCar;

import ezen5project.java.EzenRentCar.view.UnloginView;
import ezen5project.java.EzenRentCar.view.BoardView;
import ezen5project.java.EzenRentCar.view.ManagerView;
import ezen5project.java.EzenRentCar.view.MemberView;
import ezen5project.java.EzenRentCar.view.MyMenu;
import ezen5project.java.EzenRentCar.view.RentView;
import ezen5project.java.EzenRentCar.view.SuggestView;


public class AppStart {
	public static void main(String[] args) {

		// 쓰레드 실행
		CouponThread ct = new CouponThread();

		ct.start(); 
		
		// Ezen 렌트카 실행
		UnloginView.getInstance().loginMenu();




	} 
}

