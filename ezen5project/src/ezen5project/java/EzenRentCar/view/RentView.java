package ezen5project.java.EzenRentCar.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import ezen5project.java.EzenRentCar.controller.MemberController;
import ezen5project.java.EzenRentCar.controller.RentController;
import ezen5project.java.EzenRentCar.model.dto.BranchDto;
import ezen5project.java.EzenRentCar.model.dto.CouponDto;
import ezen5project.java.EzenRentCar.model.dto.RentPayDto;
import ezen5project.java.EzenRentCar.model.dto.RentRecordDto;

//흐름!! region ->date->price->inputday->totalPrice->checkDiscount->payDiscount->pay

// 렌트기능 뷰 클래스
public class RentView {
	// 싱글톤 만들기
	private static RentView rentView = new RentView();

	public static RentView getInstance() {
		return rentView;
	}

	private RentView() {
	}

	// 입력객체 생성
	private Scanner sc = new Scanner(System.in);
	int loginSession = MemberController.getInstance().getLoginSession();

	public void region() { // 지역 선택 메소드
		while (true) {
			System.out.println("\n\n======= Rent VIew Page=======");
			System.out.printf("\n\n%-10s %-10s\n","지점번호","지점명");
			ArrayList<BranchDto> result=RentController.getInstance().region();
			for(int i = 0 ; i<result.size() ; i++) {
				BranchDto dto = result.get(i);
				int pno = dto.getPno();
				String pname = dto.getPname();
				System.out.printf("%-10s %-10s\n",pno,pname);
			}
			try {
			System.out.println("지점을 입력해주세요");
			System.out.println(">>>>>입력 :            (뒤로가기 0번)");
			int ch = sc.nextInt();
			if(ch==0) {return;}
			else if (ch>0) {rentlog(ch);}
			}
			catch (Exception e) {
				System.err.println("올바른 번호를 입력해주세요");
				sc = new Scanner(System.in);
				continue;
			}
		}
	}
	public void rentlog(int ch) {// 차량의 모든 정보를 확인하는 메소드

		ArrayList<RentPayDto> result = RentController.getInstance().rentlog(ch);
		System.out.printf("%-7s %-20s %-15s %-10s\n", "차량번호", "차이름", "이용금액(시간당 금액)", "대여상태");
		System.out.println("---------------------------------------------------------");
		int bno = 0;
		for (int i = 0; i < result.size(); i++) {
			RentPayDto dto = result.get(i);
			bno = dto.getBno();
			String kname = dto.getKname();
			String rentPriceInfo = dto.getKprice() + "원";
			String rentStatus = (dto.getBstate() == 0) ? "대여 중" : "대여 가능"; // 숫자를 문자열로 변환
			System.out.printf("%-7s %-20s %-15s %-10s\n", bno, kname, rentPriceInfo, rentStatus);
		}
		carLog();
	}

	public void carLog() {
		while (true) {
			try {
			System.out.println("================차량 선택[차량번호를 입력해주세요]====================(뒤로가기0번)");
			int ch = sc.nextInt();
			sc.nextLine();

			if (ch == 0) {
				return; // 뒤로가기: carLog 메소드 종료
			}

			ArrayList<RentPayDto> result = RentController.getInstance().carLog(ch);

			boolean found = false;

			for (int i = 0; i < result.size(); i++) {
				RentPayDto dto = result.get(i);
				int bno = dto.getBno();
				int bstate = dto.getBstate();

				if (ch == bno) {found = true;
					if (bstate == 1) {inputDay(bno, dto.getKprice());} 
					else {System.out.println(dto.getKname() + "은(는) 대여 불가능한 차량입니다.");}
					break;}
			}

			if (!found) {
				System.out.println("입력한 차량 번호를 찾을 수 없습니다.");
			} else {
				break; // 루프를 종료하여 carLog 메소드도 종료하고 rentlog로 돌아감
			}
			}
			catch (Exception e) {
				System.err.println("올바른 번호를 입력해주세요");
				sc = new Scanner(System.in);
				continue;
			}
			}
	}

	public void inputDay(int bno, int pay) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH");

	    System.out.println("대여 시작시간을 yyyy-MM-dd-HH 형식으로 입력하세요:");
	    String startDay = sc.nextLine();

	    System.out.println("반납 시간을 yyyy-MM-dd-HH 형식으로 입력하세요:");
	    String endDay = sc.nextLine();
	    RentController.getInstance().inputDay(bno, pay, startDay, endDay);

	    try {
	        LocalDateTime startTime = LocalDateTime.parse(startDay, formatter);
	        LocalDateTime endTime = LocalDateTime.parse(endDay, formatter);

	        if (startTime.isBefore(LocalDateTime.now()) || endTime.isBefore(LocalDateTime.now())) {
	            System.out.println("현재 시간 이전의 날짜는 입력할 수 없습니다.");
	            return;
	        }

	        Duration duration = Duration.between(startTime, endTime);
	        long hours = duration.toHours();

	        System.out.println("대여 기간: " + hours + "시간");
	        totalPrice(hours, pay);

	    } catch (DateTimeParseException e) {
	        System.out.println("잘못된 형식입니다. yyyy-MM-dd-HH 형식으로 입력하세요.");
	    }
	}
		







	public void totalPrice(long hours, int pay) {
		float discount = 0;
		while (true) {
			try {
			System.out.println("===========결제 창 ===========\n\n");
			System.out.println("1.결제 2. 쿠폰사용 3.뒤로가기");
			int ch = sc.nextInt();
			sc.nextLine();
			if (ch == 1) {
				pay(discount, hours, pay);
				break;
			} else if (ch == 2) {
				checkDiscount(loginSession, hours, pay);
			} else if (ch == 3) {
				return;
			}
			}
			catch (Exception e) {
				System.err.println("올바른 번호를 입력해주세요");
				sc = new Scanner(System.in);
				continue;
			}
		} // w end
	}// p end

	public void checkDiscount(int loginSession, long hours, int pay) {
	    System.out.println("=====나의 쿠폰 정보=====");

	    ArrayList<CouponDto> result = RentController.getInstance().checkDiscount(loginSession);
	   

	    while (true) {
	    	try {
	        if (result == null) {
	            System.out.println("사용 가능한 쿠폰이 없습니다.");
	            System.out.println("1. 뒤로가기");
	            int choice = sc.nextInt();
	            sc.nextLine();
	            if (choice == 1) {
	                return; // 뒤로가기: 루프 종료
	            }
	        } else {
	            System.out.printf("%-10s %-10s %-10s\n", "쿠폰번호", "쿠폰이름", "할인률");
	            int tno = 0;
	            int dno = 0;
	            for (int i = 0; i < result.size(); i++) {
	                CouponDto dto = result.get(i);
	                tno = dto.getTno();
	                dno = dto.getDno();
	                String dname = dto.getDname();
	                int dpercentage = dto.getDpercentage();
	                System.out.printf("%-10s %-10s%-10s\n", tno, dname, dpercentage);
	            }
	            
	            System.out.println("사용하고 싶은 쿠폰번호 (뒤로가기 0번):");
	            int ch = sc.nextInt();
	          
	            
	               if (ch==0) {
	                   return; // 뒤로가기: 루프 종료
	               } else  {
	                  System.out.println("쿠폰사용완료");
	                   payDiscount(loginSession, hours, pay,ch,dno); // 선택한 쿠폰번호에 해당하는 payDiscount 실행
	               } 
	           }

	          }

	    	catch (Exception e) {
	    		System.err.println("올바른 번호를 입력해주세요");
				sc = new Scanner(System.in);
				continue;
	    	}
	    }
	    
	}

	public void payDiscount(int loginSession, long hours, int pay,int ch,int dno) { // 쿠폰이름을 받고 그 쿠폰이 맞으면 할인률 가져오기
		float discount = 0; // 할인율 적용하기 위한 변수
		int result = RentController.getInstance().payDiscount(ch, loginSession);
		discount = (float) result / 100; // 할인율을 퍼센트로 변환
		System.out.println(result);
		if (result == 1) {
			System.out.println("==오류==");
		}
		else{pay(discount, hours, pay);}
	}

	public void pay(float discount, long hours, int pay) { // 결제하는 메소드
		System.out.println("==========최종결제========\n\n\n");
		System.out.printf("===========이용 금액 ==========:\n%.1f원\n", (hours * pay) - (hours * pay * discount));
		System.out.println("==========카드번호입력=======");
		int card = sc.nextInt();
		System.out.println("==========CVC번호[뒤에3자리]=======");
		int cvc = sc.nextInt();
		System.out.println("==========금액입력=======");
		int price = sc.nextInt();
		sc.nextLine();

		if (price >= (hours * pay) - (hours * pay * discount)) {
			Boolean result = RentController.getInstance().pay();
			if (result) {
				System.out.println("결제가 완료되었습니다!");
				inputCoupon(discount); // 쿠폰사용시 쿠폰 넣어주는 메소드
				inputDayRent(loginSession,price);// 렌트날짜 메소드
				inputBstate(); // 최종결제시 선택받은 차량의 상태를 변경해주는 코드
				} else {
				System.out.println("=====금액을 맞게 넣어주세요=======");
			}
		}

	}

	public void inputDayRent(int loginSessionm,int price) {// 마지막 결제시 mno에 맞는 날짜 db에 삽입
		boolean result = RentController.getInstance().inputDayRent(loginSession,price); // 로그인세션을 이용한 코드
		if (result) {
			System.out.println("날짜삽입완료");
		} else {
			System.out.println("입력오류!");
		}
	}

	public void inputCoupon(float discount) {// 마지막 결제시 쿠폰
		if (discount != 0) {
			Boolean result = RentController.getInstance().inputCoupon();
			if (result) {
				System.out.println("쿠폰적용완료");
			}
		} else {
			System.out.println("쿠폰없는 결제");
		}
	}

	public void inputBstate() {// 결제시 차량의 상태를 바꿔주는 코드
		boolean result = RentController.getInstance().inputBstate();
		if (result) {
			System.out.println("차량 상태 수정완료");
		} else {
			System.out.println("안내]없는차량입니다.");
		}
	}

	// -------------------------------------------차량
	// 반납하기---------------------------------//
	public void carReturn() {
		ArrayList<RentRecordDto> result = RentController.getInstance().carReturn(loginSession);
		System.out.printf("%-10s %-10s %-10s %-10s\n", "렌트번호", "차량번호", "대여시작시간","대여완료시간");
		int lno = 0;
		for (int i = 0; i < result.size(); i++) {
			RentRecordDto dto = result.get(i);
			lno = dto.getLno();
			int bno = dto.getBno();
			String lstart = dto.getLstartlog();
			String lendlog =dto.getLendlog();
			System.out.printf("%-10s %-10s%-10s %-10s\n", lno, bno, lstart,lendlog);
		}
		chooseCar(lno);
	}

	public void chooseCar(int lno) {
		System.out.println("렌트번호 : ");
		int ch = sc.nextInt(); 
		System.out.println(lno);
		try {
			if(ch==lno){
			
		
		 ArrayList<RentRecordDto> result=  RentController.getInstance().chooseCar(ch, loginSession);
		 String lendlog = null;
		 String lstart=null;
		 int lpayment=0;
		 System.out.println("========================선택한 차량의 상태==========================");
		 System.out.printf("%-10s %-10s %-10s %-10s\n", "렌트번호", "차량번호", "대여시작시간","대여완료시간");
			for (int i = 0; i < result.size(); i++) {
				RentRecordDto dto = result.get(i);
				lno = dto.getLno();
				int bno = dto.getBno();
				lstart = dto.getLstartlog();
				lendlog =dto.getLendlog();
				lpayment=dto.getLpayment();
				System.out.printf("%-10s %-10s%-10s %-10s\n", lno, bno, lstart,lendlog);
			}
			returnDay(ch,lendlog,lstart,lpayment);
			
			}else {System.out.println("잘못된 번호입니다.");}
		}catch (Exception e) {
			System.out.println("잘못된 번호입니다."+e.getMessage());
		}
	
	}

	
	public void returnDay(int ch,String lendlog,String lstart,int lpayment) {
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH");
		System.out.println("반납 날짜를 yyyy-mm-dd-HH형식으로 입력해주세요"); String day=sc.next();
		try {
			java.util.Date returntTime = dateFormat.parse(day);
			java.util.Date endTime = dateFormat.parse(lendlog);

			long timeDifferenceMillis = returntTime.getTime() - endTime.getTime();

			long hours = timeDifferenceMillis / (60 * 60 * 1000);

			System.out.println("연체 기간: " + hours + "시간");
			if(hours>=1) {LatePrice(lendlog,lstart,hours,lpayment,day,ch);}
			else {returnComplet(ch,day);}
		} catch (ParseException e) {
			System.out.println("잘못된 형식입니다. yyyy-MM-dd-HH 형식으로 입력하세요.");

		}
	
	}

	public void LatePrice(String lendlog,String lstart,long hours,int lpayment,String day,int ch) {
		  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH");
		  int carPrice=0;
		  
		try {
            java.util.Date startTime = dateFormat.parse(lstart);
            java.util.Date endTime = dateFormat.parse(lendlog);

            long timeDifferenceMillis = endTime.getTime() - startTime.getTime();
            int hours1 = (int) TimeUnit.MILLISECONDS.toHours(timeDifferenceMillis);
            carPrice=lpayment/hours1;
           
           
		}
		catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("연체료 : "+carPrice*hours);
		System.out.println("금액을 넣어주세요:"); int ch1=sc.nextInt();
		if(ch1>=(carPrice*hours)) {
			System.out.println("결제가 완료되었습니다.");
			returnComplet(ch, day);
		}else {System.out.println("안내]금액을 맞게 넣어주세요");}
		
	}
	
	public void returnComplet(int ch,String day) {//완료시 sql에 complelt 에 넣는 코드
		boolean result= RentController.getInstance().returnComplet(ch, day);
		if(result) {System.out.println("반납이 완료되었습니다.");}
		else {System.out.println("반납실패");}
	}

}// v end