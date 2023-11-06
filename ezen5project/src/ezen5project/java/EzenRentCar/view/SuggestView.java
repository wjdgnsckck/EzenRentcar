package ezen5project.java.EzenRentCar.view;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import ezen5project.java.EzenRentCar.controller.ManagerController;
import ezen5project.java.EzenRentCar.controller.SuggestController;
import ezen5project.java.EzenRentCar.model.dto.BranchDto;
import ezen5project.java.EzenRentCar.model.dto.EventCarDto;
import ezen5project.java.EzenRentCar.model.dto.MapDto;
import ezen5project.java.EzenRentCar.model.dto.RentPriceDto;

public class SuggestView {

	private static SuggestView suggestView = new SuggestView();
	public static SuggestView getInstance() {
		return suggestView;
	}
	private SuggestView() {};
	
	private Scanner sc = new Scanner(System.in);
	
	
	// 이벤트 관리 페이지
	public void selectSuggestView() {
		
		while(true) {
			
		System.out.println("\n\n1.이벤트차량 등록   2.이벤트차량 삭제   3.이벤트차량 조회  4.서비스지역관리  5.뒤로가기");
		int ch = sc.nextInt();
		sc.nextLine();
		
			// 유효성 검사
			if(ch<1 || ch>5) {
				System.out.println("입력이 잘못되었습니다");
				System.out.println("다시 선택하십시오");
				continue;
			}
			if(ch == 1) insertEvent();
			if(ch == 2) deleteEvent();
			if(ch == 3) eventView();
			if(ch == 4) serviceMap();
			if(ch == 5) return;
		
		}
	}
	
	
	// A 이벤트 차량 등록
	public void insertEvent() {
		

		// 차량의 현재 이벤트 등록상태를 알기 위해 이벤트차량테이블을 DB에서 불러냄
		// 만일 가격테이블의 차량 중 이벤트 이벤트차량테이블에 등록된 차량은 현재 이벤트 중이므로, 
		// 출력 콘솔창에 '이벤트 진행차량' 표시
		
		// 이벤트를 등록할 차량을 선택하기 위해 기존 manager클래스의 가격 테이블 출력
		ArrayList<RentPriceDto> list = new ArrayList<>();
		list = ManagerController.getInstance().viewPrice();
		
		// 이미 이벤트 진행 중인 차량을 확인하기 위해 eventcar 테이블 출력 메서드 실행
		ArrayList<EventCarDto> eventCarList = new ArrayList<>();
		eventCarList = SuggestController.getInstance().viewEventCarList();
		
		
		System.out.printf("\n%-5s| %-20s| %-12s| %7s", "순번", "차량명", "차량가격", "이벤트 진행상태");
		System.out.print("\n---------------------------------------------------------");
		for(int i=0; i<list.size(); i++) {
						
			// kprice를 천단위당 콤마 표시를 위해 DecimalFormat클래스의 df참조변수 선언
			DecimalFormat df = new DecimalFormat("###,###");
			String price = df.format( (list.get(i).getKprice()) );
			
			// 이벤트 진행상태를 확인하기 위해 carprice 테이블의 차량이 eventcar테이블에 존재하면
			// ( carprice 의 kno 가 eventcar 에 존재하면 )
			// 해당 row에 "이벤트 진행 중" 출력
			String state = "";
			for(int j=0; j<eventCarList.size(); j++) {
				if( list.get(i).getKno() ==  eventCarList.get(j).getKno() ) {
					state = "이벤트 진행 중";
				}
			}
			
			System.out.printf("\n%5d |%15s %15s %15s", i+1, list.get(i).getKname(), price, state );
		}	
		
		
		// 이벤트를 진행할 차량 선택
		while(true) {
			
			System.out.println("\n\n이벤트에 등록할 차량의 순번을 입력하십시오");
			System.out.println("뒤로가기 > 0");
			int num = sc.nextInt()-1;
			sc.nextLine();
			if( num == -1 )  return;	// 뒤로가기
			
			
			try {
				// 유효성 검사
				// 1 선택 범위를 벗어날 경우 재선택
				if( num < 0 || num > list.size()-1 ) {
					System.out.println("차량 선택 범위를 벗어났습니다");
					System.out.println("차량을 다시 선택하십시오");
					continue;
				}
				// 2 해당 차량이 이미 이벤트가 진행 중인 경우 재선택
				if( list.get(num).getKno() == eventCarList.get(num).getKno() ) {
					System.out.println("해당 차량은 이미 이벤트가 진행 중입니다");
					System.out.println("차량을 다시 선택하십시오");
					continue;
				}
				
			} catch (Exception e) {
				// 유효성 검사 2번에서 만일 이벤트가 진행 중이지 않은 차량을 '정상적으로' 선택할 경우 
				// eventCarList.get(num).getKno() 에서 인덱스 범위 초과 예외 발생
				
				System.out.println("선택하신 차량은 "+ list.get(num).getKname() +" 입니다");
			}
			
			// 선택한 차량을 DB eventcar테이블에 저장 
			boolean result = SuggestController.getInstance().insertEvent( list.get(num).getKno() );
			if(result) {
				System.out.println("이벤트 차량등록이 완료되었습니다");
				break;
			} else {
				System.out.println("해당 차량은 이미 이벤트가 진행 중입니다");
				System.out.println("차량을 다시 선택하십시오");
			}
		}
	}
	
	
	// B 이벤트 삭제
	public void deleteEvent() {
		
		// 기등록된 이벤트차량을 중 삭제할 차량을 선택하기 위해 
		// carpricemenu, eventcar의 kno 중복 필드 출력
		ArrayList<RentPriceDto> list = new ArrayList<>();
		list = SuggestController.getInstance().eventCarInfo();
		
		System.out.printf("\n%-5s| %-20s| %-12s", "순번", "차량명", "차량가격");
		System.out.print("\n------------------------------------");
		for(int i=0; i<list.size(); i++) {
			
			// kprice를 천단위당 콤마 표시를 위해 DecimalFormat클래스의 df참조변수 선언
			DecimalFormat df = new DecimalFormat("###,###");
			String price = df.format( (list.get(i).getKprice()) );
			
			System.out.printf("\n%5d |%15s %12s", i+1, list.get(i).getKname(), price);
		}
		
		
		int num;
		// 이벤트 삭제할 차량 선택
		while(true) {
			
			System.out.println("\n\n삭제할 이벤트차량의 순번을 입력하십시오");
			System.out.println("뒤로가기 > 0");
			num = sc.nextInt()-1;
			sc.nextLine();
			if( num == -1 )  return;	// 뒤로가기
			
			
			// 유효성 검사
			// 1 선택 범위를 벗어날 경우 재선택
			if( num < 0 || num > list.size()-1 ) {
				System.out.println("차량 선택 범위를 벗어났습니다");
				System.out.println("차량을 다시 선택하십시오");
				continue;
			}
			System.out.println("선택하신 차량은 "+ list.get(num).getKname() +" 입니다");
				
			// 선택한 테이블 삭제 실행
			boolean result = SuggestController.getInstance().deleteEvent( list.get(num).getKno() );
			
			if( result ) {
				System.out.println("이벤트차량 삭제가 완료되었습니다");
				break;
			} else {
				System.out.println("이벤트차량 삭제를 실패하였습니다 [관리자 문의]");
			}
		}
	}
	
	
	// C 이벤트 출력
	public void eventView() {
		
		// 차량의 현재 이벤트 등록상태를 알기 위해 이벤트차량테이블을 DB에서 불러냄
		// 만일 가격테이블의 차량 중 이벤트 이벤트차량테이블에 등록된 차량은 현재 이벤트 중이므로, 
		// 출력 콘솔창에 '이벤트 진행차량' 표시
		
		// 이벤트를 등록할 차량을 선택하기 위해 기존 manager클래스의 가격 테이블 출력
		ArrayList<RentPriceDto> list = new ArrayList<>();
		list = ManagerController.getInstance().viewPrice();
		
		// 이미 이벤트 진행 중인 차량을 확인하기 위해 eventcar 테이블 출력 메서드 실행
		ArrayList<EventCarDto> eventCarList = new ArrayList<>();
		eventCarList = SuggestController.getInstance().viewEventCarList();
		
		
		System.out.printf("\n%-5s| %-20s| %-12s| %7s", "순번", "차량명", "차량가격", "이벤트 진행상태");
		System.out.print("\n---------------------------------------------------------");
		for(int i=0; i<list.size(); i++) {
						
			// kprice를 천단위당 콤마 표시를 위해 DecimalFormat클래스의 df참조변수 선언
			DecimalFormat df = new DecimalFormat("###,###");
			String price = df.format( (list.get(i).getKprice()) );
			
			// 이벤트 진행상태를 확인하기 위해 carprice 테이블의 차량이 eventcar테이블에 존재하면
			// ( carprice 의 kno 가 eventcar 에 존재하면 )
			// 해당 row에 "이벤트 진행 중" 출력
			String state = "";
			for(int j=0; j<eventCarList.size(); j++) {
				if( list.get(i).getKno() ==  eventCarList.get(j).getKno() ) {
					state = "이벤트 진행 중";
				}
			}
			
			System.out.printf("\n%5d |%15s %15s %15s", i+1, list.get(i).getKname(), price, state );
		}	
	}
	
	
	// D-0 전국 맵 관리
		// 지도와 같은 개념으로 필요시 지도의 위도, 경도를 넣음으로써 회원 서비스 제공을 위한 데이터로 사용
		// 고객이 이벤트 추천을 수락할 시 원하는 지역을 선택하는 데 사용
	public void serviceMap() {
		
		ArrayList<MapDto> cityMap = new ArrayList<>();
	
		// 1 기등록된 국내 서비스 지역( 지점X ) 출력
		cityMap = SuggestController.getInstance().viewLocation();
		
		// 1-1 map 2차원 배열 Field 선언
		String[][] CityDtoMap = new String[60][40];	
		// 2차원 배열 기본값 "  "로 초기화
		for(int i=0; i<CityDtoMap.length; i++) {
			for(int j=0; j<CityDtoMap[0].length; j++) {
				
				CityDtoMap[i][j] = "  ";
				
			}
		}
		// 1-2 각 지역 지도 구현
		// 각 지역의 위도, 경도를 확인하여 2차원 배열에 도시명 대입
	    for(int i=0; i<cityMap.size(); i++) {
	    	
	    	int row = 60-(((int)(cityMap.get(i).getJlatitude()*10000))-330000)/1000;	// 위도
	    	int collum = (((int)(cityMap.get(i).getJlongitude()*10000))-1260000)/1000;	// 경도
	    	
	    	CityDtoMap[row][collum] = cityMap.get(i).getJname()+" ";	// 지역명
	    	
	    }
        // 1-3 각 지역 출력
    	// ※ 통상 평면으로 보는 지도와 경위도 기준으로 보는 지도가 다름 
    	// 지구는 구 형태이기에 한국 기준으로 북쪽으로 갈수록 점점 간격이 좁아지며, 해당 경위도 방식이 더 정확함
	    for(int i=0; i<CityDtoMap.length; i++) {
			for(int j=0; j<CityDtoMap[0].length; j++) {
				
				System.out.print(CityDtoMap[i][j]);
				
			}
			System.out.println("");
		}

		while(true) {
		System.out.println("\n\n1.서비스지역 등록   2.서비스지역 삭제   3.뒤로가기");
		System.out.println("\n\n※ 위 지역은 회원이 이벤트차량을 이용하고자 하는 지역을 선택하기 위해 사용됩니다");
		int ch = sc.nextInt();
		sc.nextLine();
		
		if(ch == 1) insertLocation();
		if(ch == 2) deleteLocation();
		if(ch == 3) return;
		}
	}
	// D-1 전국내 지역 등록
		// 추가 지역명이 동일할 경우 지역등록 불가
	
    /*
     	테스트 샘플
		    충북 옥천군 위도 경도 
		    	- 대전 우측에 위치
		    	
		    위도 36.3048
		    경도 127.5686
     */
	
	
	
	public void insertLocation() {
		
		while(true) {
			
			// 추가할 지역명 입력받기
			System.out.println("\n추가할 지역의 정보를 입력해주세요");
			System.out.println("지역명 : ");
			String jname = sc.next();
			System.out.println("위도 : ");
			System.out.println("범위 > 33.1 ~ 38.9");
			
			double jlatitude = sc.nextDouble();
			sc.nextLine();
			// 위도 유효성 검사
			if(jlatitude<33.1 || jlatitude>38.9) {
				System.out.println("입력범위를 벗어났습니다");
				System.out.println("다시 입력하십시오");
				continue;
			}
			
			System.out.println("경도 : ");
			System.out.println("범위 > 126.1 ~ 129.9");
			double jlongitude = sc.nextDouble();
			sc.nextLine();
			// 경도 유효성 검사
			if(jlongitude<126.1 || jlongitude>129.9) {
				System.out.println("입력범위를 벗어났습니다");
				System.out.println("다시 입력하십시오");
				continue;
			}
			
			MapDto  mapDto = new MapDto( jname, jlatitude, jlongitude );
			// 지역 추가 메서드 실행
			int result = SuggestController.getInstance().insertLocation( mapDto );
			
			// 1 실패) DB 오류발생
			if( result == 100 ) {
				System.out.println("실패) 지역등록에 실패하였습니다 [관리자문의]");
				continue;
			}
			// 2 실패) 기등록된 지역으로 식별
			if( result == 101 ) {
				System.out.println("실패) 기등록된 지역입니다");
				System.out.println("다시 입력하십시오");
				continue;
			}
			if( result == 1000 ) {
				System.out.println("지역등록에 성공하였습니다");
				break;
			}
			
		}
	}
	// D-2 전국내 지역 삭제
	public void deleteLocation() {
		
		ArrayList<MapDto> cityMap = new ArrayList<>();
		
		// 1 기등록된 국내 서비스 지역( 지점X ) 출력
		cityMap = SuggestController.getInstance().viewLocation();
		System.out.printf("\n\n%-5s| %-10s| %-15s| %-15s ", "순번", "지역명", "위도", "경도");
		
		for(int i=0; i<cityMap.size(); i++) {
			System.out.printf("\n%5d| %8s %15f %18f ",
					i+1, cityMap.get(i).getJname(), cityMap.get(i).getJlatitude(), cityMap.get(i).getJlongitude());
		}
		
		int ch;
		while(true) {
			System.out.println("\n\n삭제할 지역의 순번을 입력하십시오");
			System.out.println("뒤로가기 > 0");
			ch = sc.nextInt()-1;
			sc.nextLine();
			
			if(ch == -1) return;						// 뒤로가기
			if(ch<0 || ch>cityMap.size()-1) {			// 유효성 검사
				System.out.println("입력범위를 벗어났습니다");
				System.out.println("다시 입력하시오");
				continue;
			} else break;
		}
		// 선택한 지역 삭제
		System.out.println("삭제할 지역 >"+cityMap.get(ch).getJname());
		boolean result = SuggestController.getInstance().deleteLocation( cityMap.get(ch).getJno() );
		
		if( result ) {
			System.out.println("지역 삭제가 완료되었습니다");
		} else {
			System.out.println("지역삭제를 실패하였습니다 [관리자문의]");
			
		}
		
	}
	// E 이벤트 실행 - 회원 프론트뷰
		/*
		 	포르쉐 벤츠 BMW 등 고급 외제차 이용 권유
		 		1. 이벤트 차량의 종류를 랜덤으로 회원 콘솔창에 출력 
		 		2. 이벤트를 수락할 시 차량을 이용하고자 하는 지역 선택
		 		3. 지역 선택 후 해당 지역 근처에 있는 가장 가까운 순서로 이벤트차량 이용이 가능한 지점 3개 출력
		 			※ 이벤트차량을 이용할 수 있는 지점 선정되는 조건 2가지
		 				1. 해당 지점에 해당 이벤트 차량이 존재할 경우
		 				2. 해당 이벤트차량이 대여 중이 아닌 상태인 경우
		 */
	public void runEvent() {

		// 1 기등록된 이벤트차량을 불러오기
		ArrayList<RentPriceDto> list = new ArrayList<>();
		list = SuggestController.getInstance().eventCarInfo();
		if(list.size() == 0) return;
		
		Random rd = new Random();
		
			// 이벤트차량 랜덤 선택하여 출력
		RentPriceDto eventCar = list.get(rd.nextInt(list.size()));
		while(true) {
			
			System.out.println("\n\n===================== TODAY CAR =====================");
			
			System.out.println( "\n       ★  "+eventCar.getKname()+"를 저렴한 가격에 이용해보세요!  ★" );
			System.out.println( "          가고 싶은 여행지만 입력하면 지점을 안내해드립니다" );
			System.out.println( "\n1 확인하기   2 닫기" );
			System.out.println("=====================================================");
			try {
			int ch = sc.nextInt();
			sc.nextLine();
			
			if(ch == 1) break;	// 다음 단계(지역선택)로 이동
			if(ch == 2) return;	// 메서드 종료 후 기존 로그인 이후의 기능 정상 실행
			}
			catch (Exception e) {
				System.err.println("올바른 번호를 입력해주세요");
				sc = new Scanner(System.in);
				continue;
			}
		}
		
		// 2 기등록된 국내 서비스 지역( 지점X ) 출력
		ArrayList<MapDto> cityMap = new ArrayList<>();
		cityMap = SuggestController.getInstance().viewLocation();
		
		System.out.println("\n\n");
		for(int i=0; i<cityMap.size(); i++) {
			System.out.print((i+1)+" "+cityMap.get(i).getJname()+"   ");
			if( (i+1) % 10 == 0 ) System.out.println("");
			
		}
		
		int ch;
		while(true) {
			System.out.println("\n\n방문할 지역의 순번을 입력하십시오");
			System.out.println("뒤로가기 > 0");
			ch = sc.nextInt()-1;
			sc.nextLine();
			
			if(ch == -1) return;						// 뒤로가기
			if(ch<0 || ch>cityMap.size()-1) {			// 유효성 검사
				System.out.println("입력범위를 벗어났습니다");
				System.out.println("다시 입력하시오");
				continue;
			} else break;
		}
		
		// 3 BFS실행하여 해당 차량 보유지점 출력
		ArrayList<BranchDto> branchDto = new ArrayList<>();
		branchDto = SuggestController.getInstance().carPointBFS( 
				cityMap.get(ch).getJno(), eventCar.getKno() );
		
		System.out.println("\n\n오늘의 렌트차량 지점안내입니다");
		
		for(int i=0; i<branchDto.size(); i++) {
			System.out.print((i+1)+"번 지점 ");
			System.out.println(branchDto.get(i).getPname());
		}
		System.out.println("\n가장 가까운 지점 순입니다");
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
