package ezen5project.java.EzenRentCar.view;



import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import ezen5project.java.EzenRentCar.controller.ManagerController;
import ezen5project.java.EzenRentCar.model.dto.BranchDto;
import ezen5project.java.EzenRentCar.model.dto.CarDto;
import ezen5project.java.EzenRentCar.model.dto.RentPriceDto;
import ezen5project.java.EzenRentCar.controller.BoardController;
import ezen5project.java.EzenRentCar.controller.MemberController;
import ezen5project.java.EzenRentCar.model.dto.CouponDto;
import ezen5project.java.EzenRentCar.model.dto.ReviewBoardDto;
import ezen5project.java.EzenRentCar.model.dto.BranchSttDto;
import ezen5project.java.EzenRentCar.model.dto.CarSttDto;
import ezen5project.java.EzenRentCar.model.dto.MemberSttDto;


// 렌트 관리자 View 클래스
public class ManagerView {
	
	private static ManagerView managerView = new ManagerView();
	public static ManagerView getInstance() {
		return managerView;
	}
	private ManagerView() {}

	private Scanner sc = new Scanner(System.in);
	private static int cno = 0;
	private static String ctime = "";
	private static boolean couponState = false;
	

	public static int getCno() {
		return cno;
	}
	public static void setCno(int cno) {
		ManagerView.cno = cno;
	}
	public static String getCtime() {
		return ctime;
	}
	public static void setCtime(String ctime) {
		ManagerView.ctime = ctime;
	}
	public static boolean isCouponState() {
		return couponState;
	}
	public static void setCouponState(boolean couponState) {
		ManagerView.couponState = couponState;
	}
	// 1.차량관리		2. 쿠폰관리	3.게시물관리	4. 통계		5. 뒤로 가기
	public void managerView() {

		Scanner sc = new Scanner(System.in);
		ArrayList<BranchDto> list = new ArrayList<>();
		
		while(MemberController.getInstance().getLoginSession()==-1) {


			
			System.out.println("\n\n==================== 관리자페이지 ====================\n");



			System.out.println("1.차량관리 2.쿠폰관리 3.게시물관리 4.통계 5.이벤트관리 6.로그아웃");


			System.out.println("\n======================================================");




			
			int ch = 0;
			try {
				System.out.print("입력 >> "); 				
				ch = sc.nextInt();
			} 
			catch (InputMismatchException e) {
				System.err.println("안내) 1~5의 숫자를 입력해주세요");
			}
			sc.nextLine();
			
			// 1. 차량관리 - 환희
			if(ch == 1) {
				
				// DB에 저장된 모든 지점 출력
				list = ManagerController.getInstance().viewCarpoint();
				
				int ch2 = 0;
				while(true) {
					
					System.out.println("\n\n관리지점을 선택하십시오");

					// 모든 지점 출력
					for(int i=0; i<list.size(); i++) {
						System.out.print((i+1)+" "+list.get(i).getPname()+"   ");
						if((i+1) % 10 == 0) System.out.println("");
					}
					System.out.println("\n뒤로가기 > 0");
					ch2 = sc.nextInt()-1;
					sc.nextLine();
					
					// 뒤로가기
					if(ch2 == -1) break;
					// 유효성 검사 : 입력값이 list 범위를 벗어날시 실패
					if(ch2<0 || ch2>list.size()-1) {
						System.out.println("지점선택이 잘못되었습니다 다시 선택하십시오");
						continue;
					} 
					
					
					System.out.println("\n\n관리지점 : "+list.get(ch2).getPname());
					
					int ch3 = 0;
					while(true) {
						System.out.println("\n1. 차량조회   2. 차량등록   3. 차량삭제   4.가격수정   5. 지점간 차량이동   6. 뒤로가기");
						
						ch3 = sc.nextInt();
						sc.nextLine();
						
						// 유효성 검사 : 입력값이 선택범위를 벗어날시 실패 
						if(ch3<1 || ch3>6) {
							System.out.println("선택이 잘못되었습니다 다시 선택하십시오");
							continue;
						}
						
						// 차량조회
						if( ch3 == 1 ) printCar( list.get(ch2).getPno() );
						// 차량등록
						if( ch3 == 2 ) registrateCar( list.get(ch2).getPno() );
						// 차량삭제
						if( ch3 == 3 ) deleteCar( list.get(ch2).getPno() );
						// 가격변경
						if( ch3 == 4 ) changePrice();
						// 지점간 차량이동
						if( ch3 == 5 ) transferCar( list.get(ch2).getPno() );
						// 뒤로 가기
						if( ch3 == 6 ) break;
					}
				}
			}
			// 2. 쿠폰관리
			if(ch == 2) {
				couponManage();
			}
			// 3. 게시물관리
			if(ch == 3) {
				boardManage();
			}
			// 4. 통계
			if(ch == 4) {
				statisticsMenu();
			}
			if(ch == 5) {
				SuggestView.getInstance().selectSuggestView();
			}
			if(ch == 6) {
				System.out.println("로그아웃 되었습니다. 안녕히가세요");
				MemberController.getInstance().logOut();
			}
			
			
			
			
			
		}
	}
	
		
/*--------------------------------------------------------------------------*/
	// 1. 차량관리 - 환희
		
		// A 차량 조회하여 출력
		public void printCar( int pno ) {
			
			ArrayList<CarDto> list = new ArrayList<>();
			list = ManagerController.getInstance().printCar( pno );
			
			System.out.printf("\n\n%-5s| %-20s| %-10s| %-7s|", "순번", "차량명", "상태", "차량가격");
			System.out.print("\n------------------------------------------------------");
			for(int i=0; i<list.size(); i++) {
				String state = "renting";
				// 차량정보 테이블에서 (bstate 1 : 사용가능 / 0 : 대여중)
				if(list.get(i).getBstate() == 1) state = "parking"; 
				
				// kprice를 천단위당 콤마 표시를 위해 DecimalFormat클래스의 df참조변수 선언
				DecimalFormat df = new DecimalFormat("###,###");
				String price = df.format( list.get(i).getKno() );
				
				System.out.printf("\n%5d |%20s %11s %10s", i+1, list.get(i).getKname(), state, price);
			}
			System.out.println("\n\n");
		}
		// B 차량 등록
		public void registrateCar( int pno ) {
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("등록할 차량명을 입력하십시오");
			String newName = sc.next();
			
			System.out.println("등록할 차량의 연식을 입력하십시오");
			String newYear = sc.next();
			
			/*
			 * 기존에 등록된 차량(차량명, 차량연식 동일)일 경우
			 * 기존 차량의 가격을 통일시킴
			 * 신규 등록된 차량일 경우
			 * 새로운 가격을 입력받아 렌트가격테이블에 삽입
			*/
			
			String newCar = newName+" "+newYear;
			// 신규 또는 기존차량인지 확인
			// 기존 차량일시 kno 반환 / 신규차량일 시 0 반환
			int result = ManagerController.getInstance().checkNewCar( newCar );
			

			// 기존 차량일 경우
			if(result > 0) {
				System.out.println("해당 연식의 차량은 기등록된 차량입니다 기등록된 차량의 가격으로 자동 등록됩니다");
			}
			// 신규 차량일 경우 carpricemenu 테이블에 가격 등록
			if(result == 0) {
				System.out.println("기존에 등록되지 않은 차량입니다 가격을 입력하십시오");
				int price = sc.nextInt();
				sc.nextLine();
				result = ManagerController.getInstance().registratePrice( newCar, price );
			}
			
			
			// 기존, 신규차량 관계없이 carprofile 테이블에 차량 등록
				// 차량을 등록하기 위해 등록할 지점, 등록할 가격 을 매개변수로 활용
				// 대여상태 기본값은 1로 지정
				// (bstate 1 : 사용가능 / 0 : 대여중)
			// 정상 등록시 true반환 / DB에 이상이 있을시 false반환
			boolean result2 = ManagerController.getInstance().registrateCar(pno, result);
			
			if(result2) System.out.println("차량등록이 완료되었습니다");
			else System.out.println("차량등록에 실패하였습니다");
			
		}
		
		// C 차량 삭제
		public void deleteCar( int pno ) {
			
			Scanner sc = new Scanner(System.in);
			ArrayList<CarDto> list = new ArrayList<>();
			list = ManagerController.getInstance().printCar( pno );
			
			// 해당 지점의 모든 차량 출력
				// 현재 차량이 대여 중인 경우 삭제 불가
			System.out.println("\n\n< 현재 차량이 고객대여 중인 경우 삭제가 불가합니다 >");
			System.out.printf("%-5s| %-20s| %-17s| %-8s|", "순번", "차량명", "삭제가능여부", "차량가격");
			System.out.print("\n--------------------------------------------------------------");
			for(int i=0; i<list.size(); i++) {
				String state = "Unable to Delete";
				// 차량정보 테이블에서 (bstate 1 : 사용가능 / 0 : 대여중)
				if(list.get(i).getBstate() == 1) state = "Able Delete"; 
				
				// kprice를 천단위당 콤마 표시를 위해 DecimalFormat클래스의 df참조변수 선언
				DecimalFormat df = new DecimalFormat("###,###");
				String price = df.format( list.get(i).getKno() );
				
				System.out.printf("\n%5d |%20s %20s %10s", i+1, list.get(i).getKname(), state, price);
			}
			
			int num;
			while(true) {

				System.out.println("\n삭제하실 차량의 순번을 입력하십시오");
				System.out.println("뒤로가기> 0");
				num = sc.nextInt()-1;
				sc.nextLine();
				
				// 입력 범위 유효성 검사
					// 뒤로가기 : 메서드 종료
				if( num == -1 ) return;
					// 입력 범위가 list.size() 범위를 벗어날 시 재선택
				if( num<0 || num>list.size()-1 ) {
					System.out.println("차량 선택이 잘못되었습니다 다시 입력하십시오");
					continue;
				}
					// 현재 고객 대여 중 차량은 삭제 불가하여 재선택
				else if( list.get(num).getBstate() == 0 ) {
					System.out.println("현재차량은 고객 대여중이므로 삭제가 불가합니다 다시 입력하십시오");
					continue;
				} else break;
				
			}
			
			boolean result = ManagerController.getInstance().deleteCar( list.get(num).getBno() );
			if(result) System.out.println("차량 삭제가 완료되었습니다");
			else System.out.println("차량 삭제가 불가합니다");
			
		}
		
		// D 렌트가격 변경
		public void changePrice() {
			
			Scanner sc = new Scanner(System.in);
			
			// 가격 변경을 위해 가격 테이블 출력 메서드 실행
			ArrayList<RentPriceDto> list = new ArrayList<>();
			list = ManagerController.getInstance().viewPrice();
			
			System.out.printf("\n%-5s| %-20s| %-12s", "순번", "차량명", "차량가격");
			System.out.print("\n------------------------------------");
			for(int i=0; i<list.size(); i++) {
				
				// kprice를 천단위당 콤마 표시를 위해 DecimalFormat클래스의 df참조변수 선언
				DecimalFormat df = new DecimalFormat("###,###");
				String price = df.format( (list.get(i).getKprice()) );
				
				System.out.printf("\n%5d |%15s %12s", i+1, list.get(i).getKname(), price);
			}
			
			int num;
			// 출력 리스트에서 수정할 차량의 순번을 선택
			while(true) {

				System.out.println("\n\n가격을 수정할 차량의 순번을 입력하십시오");
				System.out.println("뒤로가기> 0");
				num = sc.nextInt()-1;
				sc.nextLine();
				
				// 입력 범위 유효성 검사
					// 뒤로가기 : 메서드 종료
				if( num == -1 ) return;
					// 입력 범위가 list.size() 범위를 벗어날 시 재선택
				if( num<0 || num>list.size()-1 ) {
					System.out.println("순번 선택이 잘못되었습니다 다시 입력하십시오");
					continue;
				} else break;
			}
			
			// 선택한 순번의 차량의 변경할 가격 입력
			System.out.println("\n변경할 가격을 입력하여 주십시오");
			int kprice = sc.nextInt();
			sc.nextLine();
			
			boolean result = ManagerController.getInstance().changePrice( list.get(num).getKno(), kprice );
			
			if(result) System.out.println("차량 가격변경이 완료되었습니다");
			else System.out.println("차량 가격변경에 실패하였습니다");
			
			
			
		}
		// E 지점간 차량이동
		public void transferCar( int pno ) {
			
			ArrayList<BranchDto> carPoint = new ArrayList<>();
			Scanner sc = new Scanner(System.in);
			
			
			ArrayList<CarDto> carInfo = new ArrayList<>();
			carInfo = ManagerController.getInstance().printCar( pno );
			
			
			// 이동시키고자하는 차량을 선택하기 위해 출력
			System.out.printf("\n\n%-5s| %-20s| %-10s|", "순번", "차량명", "상태");
			System.out.print("\n------------------------------------------------------");
			for(int i=0; i<carInfo.size(); i++) {
				String state = "renting";
				// 차량정보 테이블에서 (bstate 1 : 사용가능 / 0 : 대여중)
				if(carInfo.get(i).getBstate() == 1) state = "parking"; 
				System.out.printf("\n%5d |%20s %11s ", i+1, carInfo.get(i).getKname(), state);
			}
			int num;
			while(true) {
				System.out.println("\n\n< 현재 대여중인 차량은 선택이 불가합니다 >");
				System.out.println("타지점으로 이동시킬 차량의 순번을 입력하십시오");
				System.out.println("뒤로가기> 0");
				num = sc.nextInt()-1;
				sc.nextLine();
				
				// 입력 범위 유효성 검사
					// 뒤로가기 : 메서드 종료
				if( num == -1 ) return;
					// 입력 범위가 list.size() 범위를 벗어날 시 재선택
				if( num<0 || num>carInfo.size()-1 ) {
					System.out.println("차량 선택이 잘못되었습니다 다시 입력하십시오");
					continue;
				}
					// 현재 고객 대여 중 차량은 삭제 불가하여 재선택
				else if( carInfo.get(num).getBstate() == 0 ) {
					System.out.println("현재차량은 고객 대여중이므로 이동이 불가합니다 다시 입력하십시오");
					continue;
				} else break;
				
			}
			
			
			// 이동할 지점을 설정하기 위해 DB에 저장된 모든 지점 출력
			carPoint = ManagerController.getInstance().viewCarpoint();
			
			int ch = 0;
			while(true) {
				
				System.out.println("현 지점에서 차량을 이동할 지점을 선택하십시오");
				// 모든 지점 출력
				for(int i=0; i<carPoint.size(); i++) {
					System.out.print((i+1)+" "+carPoint.get(i).getPname()+"   ");
					if((i+1) % 10 == 0) System.out.println("");
				}
				ch = sc.nextInt()-1;
				sc.nextLine();
				
				// 유효성 검사 : 입력값이 list 범위를 벗어날시 실패 
				if(ch<0 || ch>carPoint.size()-1) {
					System.out.println("지점선택이 잘못되었습니다 다시 선택하십시오");
					continue;
				} else break;
			}
			
			// 입력 받은 순번의 차량의 지점을 변경
				// 인수지점, 인계차량을 매개변수로 지정
			boolean result = ManagerController.getInstance().transferCar( carPoint.get(ch).getPno(), carInfo.get(num).getBno() );
			
			if(result) {
				System.out.println("\n\n인수지점 : "+carPoint.get(ch).getPname());
				System.out.println("인계차량 : "+carInfo.get(num).getKname());
				System.out.println("타지점 차량인계가 완료되었습니다");
			} else {
				System.out.println("차량등록에 실패하였습니다 [관리자 문의]");
			}
			
		}
		
		
/*--------------------------------------------------------------------------*/
	// 2. 쿠폰관리 - 민재	
		
		
		public void couponManage() {
			
			System.out.println("======================== 쿠폰관리 페이지 ========================");
			System.out.println("1.쿠폰추가 2.쿠폰수정 3.쿠폰삭제 4. 쿠폰일괄지급");

			int ch = 0;
			try {
				System.out.print("입력 >> "); 				
				ch = sc.nextInt();
			} 
			catch (InputMismatchException e) {
				System.err.println("안내) 1~3의 숫자를 입력해주세요");
			}
			sc.nextLine();
			
			//쿠폰추가
			if(ch==1) {
				
				System.out.println("현재 등록되어있는 쿠폰 목록");
				//쿠폰목록 불러오기
				ArrayList<CouponDto> result = ManagerController.getInstance().couponAllview();
				
				System.out.printf("%-3s %-10s %-4s %-20s\n","no","쿠폰이름","할인율","쿠폰번호");
				
				for(int i = 0; i<result.size(); i++) {
					
					CouponDto dto = result.get(i);
					System.out.printf("%-3s %-10s %-4s %-20s\n", 
							i+1 , dto.getDname() , 
							dto.getDpercentage()+"%" , dto.getDnumber() 
							);
					
				}//for end
				
				System.out.print("추가할 쿠폰 이름 : "); String dname = sc.next();
				System.out.print("추가할 쿠폰 할인율 : "); int dpercent = sc.nextInt();
				System.out.print("추가할 쿠폰 번호 : "); String dnumber = sc.next();
				
				int result2 = ManagerController.getInstance().couponAdd( dname , dpercent , dnumber );
				
				if (result2==1) {
					System.out.println("쿠폰등록 성공했습니다.");
				}
				else if(result2==2) {
					System.err.println("쿠폰 이름은 20자 미만이어야 합니다."); 
				}
				else if(result2==3) {
					System.err.println("쿠폰 번호는 20자 미만이어야 합니다.");
				}
				else if(result2==4) {
					System.err.println("시스템 오류] 쿠폰등록을 실패했습니다.");
				}
			}//ch==1 end
			//쿠폰 수정
			else if(ch==2) {
				
				System.out.println("---------쿠폰수정 페이지---------");
				
				System.out.println("현재 등록되어있는 쿠폰 목록");
				
				ArrayList<CouponDto> result = ManagerController.getInstance().couponAllview();
				
				System.out.printf("%-3s %-10s %-4s %-20s\n","no","쿠폰이름","할인율","쿠폰번호");
				
				for(int i = 0; i<result.size(); i++) {
					
					CouponDto dto = result.get(i);
				
					System.out.printf("%-3s %-10s %-4s %-20s\n", 
							i+1 , dto.getDname() , 
							dto.getDpercentage()+"%" , dto.getDnumber() 
							);
					
				}//for end
				
				System.out.print("수정할 쿠폰을 선택해주세요\n>>>>>>"); int ch2 = sc.nextInt();
				
				if(ch2>0 && ch2<=result.size()) {
					//선택한 번호 -1 = 선택한 쿠폰의 인덱스의 pk
					int dno = result.get(ch2-1).getDno();
					
					System.out.println("수정할 항목 선택");
					
					System.out.println("1.쿠폰명 2.할인율 ");
					int ch3 = sc.nextInt();
					
					if(ch3==1) {
						
						System.out.print("선택한 쿠폰명 : ");
						System.out.println( result.get(ch2-1).getDname() );
						
						System.out.println("수정할 쿠폰명"); String newValue = sc.next();
						
						int result2 = ManagerController.getInstance().couponNameModify( dno , newValue);
						
						if(result2==1) {
							System.out.println("쿠폰수정 성공했습니다.");
						}
						else if(result2==2){
							System.err.println("쿠폰명은 20글자 미만이어야 합니다.");
						}
						else if(result2==3){
							System.err.println("쿠폰수정 실패했습니다.(시스템오류)");
						}
				
					}//ch3==1 end
					else if(ch3==2) {
						
						System.out.print("선택한 쿠폰 할인률 : ");
						System.out.println( result.get(ch2-1).getDpercentage()+"%" );
						
						System.out.println("수정할 쿠폰 할인률"); int newValue = sc.nextInt();
						
						int result2 = ManagerController.getInstance().couponPerModify( dno , newValue);
						
						if(result2==1) {
							System.out.println("쿠폰수정 성공했습니다.");
						}
						else if(result2==2){
							System.err.println("할인률은 1%미만이 될 수 없습니다.");
						}
						else if(result2==3){
							System.err.println("쿠폰수정 실패했습니다.(시스템오류)");
						}
					}//ch3==2 end
				}//if end
				else {System.out.println("올바른 쿠폰 번호를 입력해주세요");}
			}//ch2 end
			//쿠폰삭제
			else if(ch==3) {
				
				System.out.println("---------쿠폰삭제 페이지---------");
				
				System.out.println("현재 등록되어있는 쿠폰 목록");
				
				ArrayList<CouponDto> result = ManagerController.getInstance().couponAllview();
				
				System.out.printf("%-3s %-10s %-4s %-20s\n","no","쿠폰이름","할인율","쿠폰번호");
				
				for(int i = 0; i<result.size(); i++) {
					
					CouponDto dto = result.get(i);
					System.out.printf("%-3s %-10s %-4s %-20s\n", 
							i+1 , dto.getDname() , 
							dto.getDpercentage()+"%" , dto.getDnumber() 
							);
				}//for end
				
				System.out.print("삭제할 쿠폰을 선택해주세요\n>>>>>>"); int ch2 = sc.nextInt();
				
				if(ch2>0 && ch2<=result.size()) {
					//선택한 번호 -1 = 선택한 쿠폰의 인덱스의 pk
					int dno = result.get(ch2-1).getDno();
					
					System.out.println("정말 삭제하시겠습니까?\n1.예 2.아니오");
					int ch4 = sc.nextInt();
					if(ch4==1) {
						
						boolean result2 = ManagerController.getInstance().couponDelete(dno);
						
						if(result2) {
							System.out.println("쿠폰삭제 성공했습니다.");
						}
						else {
							System.err.println("쿠폰삭제 실패했습니다.[시스템 관리자에게 문의]");
						}
					}
					else if(ch4==2){System.out.println("쿠폰삭제 취소했습니다.");}
				}//if end
				else {
					System.err.println("올바른 쿠폰 번호를 입력해주세요");
				}
			}//ch==3 end
			else if(ch == 4) {
				System.out.println("현재 등록되어있는 쿠폰 목록");
				//쿠폰목록 불러오기
				ArrayList<CouponDto> result = ManagerController.getInstance().couponAllview();
				
				System.out.printf("%-3s %-10s %-4s %-20s\n","no","쿠폰이름","할인율","쿠폰번호");
				
				for(int i = 0; i<result.size(); i++) {
					
					CouponDto dto = result.get(i);
					System.out.printf("%-3s %-10s %-4s %-20s\n", 
							i+1 , dto.getDname() , 
							dto.getDpercentage()+"%" , dto.getDnumber() 
							);
					
				}//for end
				
				System.out.print("\n1. 쿠폰이벤트실행 2. 쿠폰이벤트중단"); ch = sc.nextInt();
				if(ch == 1) {
					System.out.print("지급하실 쿠폰번호를 입력해주세요 >> "); cno = sc.nextInt();
					System.out.print("지급하실 시간을 입력해주세요(HH:mm) >> "); ctime = sc.next();
					
					
					couponState = true;
				}else if(ch == 2) {couponState = false;}
				
				
			}
		}//couponManage end
		
		
		
		
/*--------------------------------------------------------------------------*/
	// 3. 게시물관리 - 민재			
		
		
		public void boardManage() {
			
			int f  = 0;
			int l = 10;
			
			positionView : while (true) {

			
			try {
			System.out.println("---------리뷰관리 페이지---------");
			System.out.println("열람 할 지역을 선택해주세요");
			
			//지역 출력하기 요청
			ArrayList<BranchDto> result1 = BoardController.getinstance().reviewCityDtoView( f , l);
			
			System.out.printf("%-2s \t %-4s\n" , "no" , "지역" );
			
			//지역 전부 출력하기
			for(int i = 0; i<result1.size(); i++) {

				BranchDto dto = result1.get(i);
				System.out.printf("%-2s \t %-4s\n" , dto.getPno() , dto.getPname() );
			
			}//for end
			
			//페이지처리
			System.out.println("");
			//두번째 페이지부터 출력
			if(f>0) {
				System.out.print("1.이전페이지");
			}
			//마지막 페이지에선 출력하지 않음
			//마지막 페이지 게시물에서 게시물이 10개일때 출력되어도 유효성 검사해서 페이지 안넘어감 
			if(result1.size()==10) {
				System.out.print("2.다음페이지 ");
			}
			//뒤로가기는 항시출력
			System.out.print("3.뒤로가기 0.지역선택\n");
			int ch2 = sc.nextInt();
			
			if(ch2==1) {
				if(f==0) {
					System.out.println("첫번째 페이지 입니다.");
					continue positionView;
				}
				f-=10; 
				continue positionView;

			}
			else if(ch2==2) {
			
				if(result1.size()<10) {
					System.out.println("마지막 페이지 입니다.");
					continue positionView;
				}
				f+=10;
				continue positionView;

			}
			else if(ch2==3) {
				f=0; 
				return;
			}
			else if(ch2==0) {
			
			System.out.print(">>>>>선택"); int ch = sc.nextInt();
		
			
			if(ch>0 && ch <= result1.size()) {
				//지역번호
				int pno = result1.get(ch-1).getPno();
				//해당 지역의 리뷰만 출력
				ArrayList<ReviewBoardDto> result2 = ManagerController.getInstance().reviewView( pno );
				if(result2.size()==0) {
					System.out.println("표시할 리뷰가 없습니다.");
					continue positionView;
				}
				System.out.printf("%-3s %-10s %-10s %-20s %-5s\n","번호" , "대여차량" , "제목" , "내용" , "상태" );
				
				for(int i = 0; i<result2.size(); i++) {
					ReviewBoardDto dto = result2.get(i);
					System.out.printf("%-3s %-10s %-10s %-20s %-5s\n", i+1 , dto.getKname() , 
							dto.getUtitle() , dto.getUcontent() , dto.getUstatusName()  );
				}//for end
				
				System.out.println("1.리뷰신고 2.신고해제");
				int reportCh = sc.nextInt();
				
				if(reportCh==1) {
					
					System.out.print("신고할 리뷰 번호 선택>>>>>");
					int ch3 = sc.nextInt();
					
					if( ch3>0 && ch3<=result2.size()) {				
						int status = result2.get(ch3-1).getUstatus();
					
						if(status==0) {
							System.err.println("이미 신고된 리뷰입니다.");
							return;
						}
						int newStatus = ch2-1;
						int uno = result2.get(ch3-1).getUno();
					
						boolean result3 = ManagerController.getInstance().boardReport( newStatus , uno);
					
						if(result3) {
							System.out.println("리뷰가 신고되었습니다.");
							return;
						}
						else {
							System.err.println("신고 실패했습니다.(시스템 관리자에게 문의)");
							return;
						}
					}//ch3 end
					else {
						System.err.println("올바른 게시물 번호를 입력해주세요");
					}
				}//ch2==1 end
				else if(reportCh==2) {
					
					System.out.print("신고 해제 할 리뷰 번호 선택>>>>>");
					int ch3 = sc.nextInt();
					
					if( ch3>0 && ch3<=result2.size()) {
						int status = result2.get(ch3-1).getUstatus();
					
						if(status==1) {
						System.err.println("이미 해제된 리뷰입니다.");
						return;
						}
						int newStatus = ch2-1;
						int uno = result2.get(ch3-1).getUno();
					
						boolean result3 = ManagerController.getInstance().boardReport( newStatus , uno);
					
						if(result3) {
							System.out.println("리뷰신고가 해제 되었습니다.");
							return;
						}
						else {
							System.err.println("신고해제가 실패했습니다.(시스템 관리자에게 문의)");
							return;
						}
					}// ch3 end
					else {
						System.err.println("올바른 게시물 번호를 입력해주세요");
					}
				}//ch2==2 end
				
				
			}//if end
			else {
				System.err.println("올바른 게시물 번호를 입력해주세요");
			}
			}
			}//try end
			catch (Exception e) {
				System.err.println(e);
			}
			
			}
		}//boardManage end
		

/*--------------------------------------------------------------------------*/
	// 4. 통계 - 의선	

	// 통계메뉴 프론트 함수
	public void statisticsMenu() {
		while(true) {
			System.out.println("\n\n========================== 통계 메뉴 =============="
					+ "============\n");
			System.out.println(" 1. 차량별 통계  2. 지점별 통계  3. 회원별 통계  4. 뒤로가기 ");
			System.out.println("\n=========================="
					+ "=====================================");
			System.out.print("입력 >> ");	int ch = sc.nextInt();
			
			if(ch == 1) { carStatistics(); }
			else if(ch == 2) { branchStatistics(); }
			else if(ch == 3) { memberStatistics(); }
			else if(ch == 4) { return; }
			else {System.err.println("안내) 잘못된 입력입니다.");}
		}
	}
	
	// 차량별 통계 프론트 함수
	public void carStatistics() {
		String stt = inputYearMonth();
		// 년도/월 입력 실패시 뒤로가기
		if(stt.equals("1")) {
			return;
		}
		
		ArrayList<CarSttDto> list = 
				ManagerController.getInstance().carStatistics(stt);
		
		if(list.size() == 0) {System.out.println("안내) 해당 기간의 대여기록이 존재하지 않습니다."); return;}
		
		System.out.println("\n\n=========================================");
		System.out.printf("%s\t %s\t %s\t %s\n","  ", "차량분류", "매출액", "대여횟수");
		System.out.println("=========================================");
		for(int i = 0; i < list.size(); i++) {
			System.out.printf("%d\t %s\t %d\t %d\n",i+1, list.get(i).getKname(),
					list.get(i).getSum(), list.get(i).getRcount());
		}
	}
	
	// 지점별 통계 프론트 함수
	public void branchStatistics() {
		String stt = inputYearMonth();
		// 년도/월 입력 실패시 뒤로가기
		if(stt.equals("1")) {
			return;
		}
		
		ArrayList<BranchSttDto> list = 
				ManagerController.getInstance().branchStatistics(stt);
		
		if(list.size() == 0) {System.out.println("안내) 해당 기간의 대여기록이 존재하지 않습니다."); return;}
		
		System.out.println("\n\n=========================================");
		System.out.printf("%s\t %s\t %s\t %s\n","  ", "지점명", "매출액", "총대여횟수");
		System.out.println("=========================================");
		for(int i = 0; i < list.size(); i++) {
			System.out.printf("%d\t %s\t %d\t %d\n",i+1, list.get(i).getPname(),
					list.get(i).getSum(), list.get(i).getPcount());
		}
	}
	
	// 회원별 통계 프론트 함수
	public void memberStatistics() {
		String stt = inputYearMonth();
		// 년도/월 입력 실패시 뒤로가기
		if(stt.equals("1")) {
			return;
		}
		
		ArrayList<MemberSttDto> list = 
				ManagerController.getInstance().memberStatistics(stt);
		
		if(list.size() == 0) {System.out.println("안내) 해당 기간의 대여기록이 존재하지 않습니다."); return;}
		
		System.out.println("\n\n========================================="
				+ "=========================================");
		System.out.printf("%s\t %s\t %s\t %s\t %s\t %s\n","  ", "회원아이디", "회원주소", "연령", "서비스이용횟수", "총사용금액");
		System.out.println("========================================="
				+ "=========================================");
		for(int i = 0; i < list.size(); i++) {
			System.out.printf("%d\t %s\t %s\t\t %d\t %d\t\t %d\n",i+1, list.get(i).getMid(),
					list.get(i).getMad(), list.get(i).getMage(), list.get(i).getRcount(), list.get(i).getMsum());
		}

	}
	
	// 년도, 달 입력 프론트 함수
	public String inputYearMonth() {
		String stt = "";
		System.out.print("연도를 입력해주세요(4자리) >> "); String year = sc.next();
		System.out.print("월을 입력해주세요(2자리) >> "); String month = sc.next();
		int result =
				ManagerController.getInstance().validation(year, month);
		
		if(result == 1) {System.err.println("안내) 정확한 자리수를 입력해주세요."); return "1";}
		if(result == 2) {System.err.println("안내) 2000 ~ 2023 사이의 연도를 입력해주세요."); return "1";}
		if(result == 3) {System.err.println("안내) 1 ~ 12 사이의 월을 입력해주세요."); return "1";}
		
		stt = year + "-" + month;
		
		return stt;
		
	}
	
		
	
	
		
		
}





















