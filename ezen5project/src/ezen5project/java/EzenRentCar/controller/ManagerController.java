package ezen5project.java.EzenRentCar.controller;

import java.util.ArrayList;




import ezen5project.java.EzenRentCar.model.dto.BranchDto;
import ezen5project.java.EzenRentCar.model.dto.CarDto;
import ezen5project.java.EzenRentCar.model.dto.RentPriceDto;
import ezen5project.java.EzenRentCar.model.dao.BoardDao;
import ezen5project.java.EzenRentCar.model.dao.ManagerDao;
import ezen5project.java.EzenRentCar.model.dto.CouponDto;
import ezen5project.java.EzenRentCar.model.dto.ReviewBoardDto;

import ezen5project.java.EzenRentCar.model.dao.ManagerDao;
import ezen5project.java.EzenRentCar.model.dto.BranchSttDto;
import ezen5project.java.EzenRentCar.model.dto.CarSttDto;
import ezen5project.java.EzenRentCar.model.dto.MemberSttDto;

import ezen5project.java.EzenRentCar.view.ManagerView;


// 렌트 관리자 Controller 클래스
public class ManagerController {

	private static ManagerController managerController = new ManagerController();
	public static ManagerController getInstance() {
		return managerController;
	}
	private ManagerController() {}
	
	
/*--------------------------------------------------------------------------*/
	// 1. 차량관리 - 환희
	
	// 지점출력
	public ArrayList<BranchDto> viewCarpoint(){
		return ManagerDao.getInstance().viewCarpoint();
	}
	
	// A 차량 조회하여 출력
	public ArrayList<CarDto> printCar( int pno ) {
	
		return ManagerDao.getInstance().printCar(pno);
		
	}
	// B-1 차량 등록
	public boolean registrateCar( int pno, int kno ) {
		
		boolean result = ManagerDao.getInstance().registrateCar(pno, kno);
		if(result) return true;
		else return false;
		
	}
	// B-2 신규 또는 기존차량인지 확인
	public int checkNewCar( String newCar ) {
		/*
		 * 차량등록 입력값 중 띄어쓰기가 2번 이상 있는 경우 실패
		 * 
		 * 	차량등록 입력연도가 4자리 초과일 경우 실패
			현재 연도 초과할 경우 실패
		 * 
		 * 기존 차량이 존재할 경우 kno를 반환하기때문에 유효성검사 반환값은
		 * 음수로 지정해야함
		 * */
		
		return ManagerDao.getInstance().checkNewCar(newCar);
	}
	// B-3 신규 차량일 경우 가격 등록
	public int registratePrice( String newCar, int price ) {
		
		return ManagerDao.getInstance().registratePrice(newCar, price);
		
	}
	// C 차량 삭제
	public boolean deleteCar( int bno ) {
		
		return ManagerDao.getInstance().deleteCar(bno);
		
	}
	
	

	// D-1 가격 변경
	public boolean changePrice( int kno, int kprice ) {
		
		return ManagerDao.getInstance().changePrice( kno, kprice );
		
	}
	// D-2 가격 테이블 출력
	public ArrayList<RentPriceDto> viewPrice() {
		
		return ManagerDao.getInstance().viewPrice();
		
	}
	
	
	
	
	// E 지점간 차량이동
	public boolean transferCar( int pno, int bno ) {
		
		return ManagerDao.getInstance().transferCar( pno, bno );
	}
	
		
		
		
		
		
/*--------------------------------------------------------------------------*/
	// 2. 쿠폰관리 - 민재	
	//쿠폰출력함수
	public ArrayList<CouponDto> couponAllview() {
		
		ArrayList<CouponDto> result = ManagerDao.getInstance().couponAllview();
		return result;
	}  
	//쿠폰등록 함수
	public int couponAdd( String dname , int dpercent , String dnumber ) {
		
		if(dname.length()>20) { return 2; }
		else if(dnumber.length()>20) {return 3;}
		else {
		int result = ManagerDao.getInstance().couponAdd( dname , dpercent , dnumber );
		return result;
		}
	}	
	//쿠폰 이름 수정
	public int couponNameModify( int dno , String newValue) {
		
			
			if(newValue.length()>20) {
				return 2;
			}
			int result = ManagerDao.getInstance().couponNameModify(dno , newValue);
			return result;
		
		 
		
	};
	//할인률 수정
	public int couponPerModify( int dno , int newValue ) {
		
		if(newValue<=0) {
			return 2;
		}
		int result = ManagerDao.getInstance().couponPerModify(dno , newValue);
		return result;
		
	}
	
	//쿠폰삭제함수
	public boolean couponDelete( int dno ) {
		boolean result = ManagerDao.getInstance().couponDelete(dno);
		return result;
	}
		
		
/*--------------------------------------------------------------------------*/
	// 3. 게시물관리 - 민재			
	
	public ArrayList<ReviewBoardDto> reviewView( int pno ) {
		
		return ManagerDao.getInstance().reviewView( pno );

	}
	
	public boolean boardReport( int newStatus , int uno ) {
		
		boolean result = ManagerDao.getInstance().boardReport( newStatus , uno);
		return result;
	}
		
		

/*--------------------------------------------------------------------------*/
	// 4. 통계 - 의선	
		
	// 차량별 통계 컨트롤러 함수
	public ArrayList<CarSttDto> carStatistics(String stt) {
		return ManagerDao.getInstance().carStatistics(stt);
	}
	
	// 지점별 통계 컨트롤러 함수
	public ArrayList<BranchSttDto> branchStatistics(String stt) {
		return ManagerDao.getInstance().branchStatistics(stt);
	}
	
	// 회원별 통계 컨트롤러 함수
	public ArrayList<MemberSttDto> memberStatistics(String stt) {
		return ManagerDao.getInstance().memberStatistics(stt);
	}
	
	// 년도,달 유효성 검사 함수
	public int validation(String year, String month) {
		
		if(year.length() != 4 || month.length() != 2) {return 1;}
		if(Integer.parseInt(year) < 2000 || Integer.parseInt(year) > 2023) {
			return 2;
		}
		if(Integer.parseInt(month) < 1 || Integer.parseInt(month) > 12) {
			return 3;
		}
		
		return 0;
	}

	
	
	
	
	
}












