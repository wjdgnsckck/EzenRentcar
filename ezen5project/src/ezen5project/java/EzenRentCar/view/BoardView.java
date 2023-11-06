package ezen5project.java.EzenRentCar.view;

import java.util.ArrayList;
import java.util.Scanner;

import ezen5project.java.EzenRentCar.controller.BoardController;
import ezen5project.java.EzenRentCar.model.dto.BranchDto;
import ezen5project.java.EzenRentCar.model.dto.RentRecordDto;
import ezen5project.java.EzenRentCar.model.dto.ReviewBoardDto;

// 한 줄 후기게시판 클래스
public class BoardView {

	private static BoardView boardView = new BoardView();
	public static BoardView getinstance() { return boardView; }
	private BoardView() {}
	private Scanner scanner = new Scanner(System.in);
	
	public void BoardView() {
		//무한루프 
		while (true) {// wh s
			
			System.out.println("\n======================= 후기메뉴 "
					+ "=======================\n");
			System.out.println(" 1.후기등록 2.후기보기 3.후기수정 4.후기삭제 5.뒤로가기");
			System.out.println("\n================================="
					+ "=======================");
			System.out.print("입력 >> ");
			
			try {
				//선택한 번호의 함수 실행
				int ch = scanner.nextInt();
				
				if( ch==1 ) { reviewWrite(); }
				else if( ch==2 ) { reviewView(); }
				else if( ch==3 ) { reviewModify(); }
				else if( ch==4 ) { reviewDelete(); }
				else if( ch==5 ) { return; }
				else { System.err.println("경고] 메뉴 중에서 선택해주세요"); }
			
			} catch (Exception e) {
				System.err.println(e);
				scanner = new Scanner(System.in);			
			}//try catch e
		
			
		}// wh end
		
	}// 함수 끝
	
	//1. 후기등록
	public void reviewWrite() {
		 
		
		try {
		 System.out.println("\n===================== 후기작성 가능한 대여내역 "
		 		+ "=====================\n");
		 
		 //렌트완료된 내역 출력
		 ArrayList<RentRecordDto> result1 = BoardController.getinstance().reviewAdmit();
		 //반환값의 인덱스가 0 이면 
		 if( result1.size()==0 ) {
			 System.out.println("안내] 리뷰 작성 가능한 차량이 없습니다.");
			 return;
		 }
		 
		 System.out.printf("%-5s %-5s %-8s %-20s %-20s \n","번호" , "렌트번호" , "렌트차량" , "렌트시작일" , "렌트완료일" );
		 System.out.println("=============================================="
			 		+ "======================");
		 //반환값의 인덱스 번호만큼 반복출력 
		 for(int i = 0; i < result1.size() ; i++) {// for s
			  
			 RentRecordDto dto = result1.get(i);
			  //렌트번호 차량이름 시작시간 완료시간 출력 
			  System.out.printf("%-5s %-5s %-8s %-20s %-20s \n" , 
					  				i+1 , dto.getLno() ,
					  				dto.getLcompletelog() , dto.getLstartlog() , 
					  				dto.getLendlog() );
		  }//for end
		  
		  System.out.print("작성할 번호 입력(0.뒤로가기) >> ");
		  int ch = scanner.nextInt();
		  if(ch==0) {return;}
		  else if( ch > 0 && ch <= result1.size()) {//if s
			  
			  //선택한 항목의 렌트번호
			  int lno = result1.get(ch-1).getLno();
			  
			  scanner.nextLine(); 
			  System.out.print("제목 : "); String title = scanner.nextLine();
			  System.out.print("\n내용 : "); String content = scanner.nextLine();
			  
			  //렌트번호 , 제목 , 내용 컨트롤러에 보내고 true / false 반환받기
			  boolean result2 = BoardController.getinstance().reviewWrite( lno , title , content );
					 
			  if(result2) {
				  System.out.println("리뷰등록 성공했습니다.");
			  }
			  else {
				  System.out.println("리뷰등록 실패했습니다. \n 제목과 내용에 공백없이 작성해주세요");
			  }
			  
		  }//if end
		  else { System.err.println("경고] 올바른 선택이 아닙니다."); }
		}catch (Exception e) {
			System.err.println("경고] 잘못된 입력입니다.");
			scanner = new Scanner(System.in);
		}
	}// 후기 등록 end
	
	//2. 후기보기
	public void reviewView() {
		
		int f  = 0;
		int l = 10;
		
		positionView : while (true) {
			
		try {
		System.out.println("----------후기 게시판----------");
		System.out.println("열람 할 지역을 선택해주세요");
		//지역 출력하기 요청
		ArrayList<BranchDto> result1 = BoardController.getinstance().reviewCityDtoView( f , l);
		
		System.out.printf("%-2s \t %-4s\n" , "no" , "지역" );
		
		//지역 출력하기
		for(int i = 0; i<result1.size(); i++) {

			BranchDto dto = result1.get(i);
			System.out.printf("%-2s \t %-4s\n" , i+1 , dto.getPname() );
		
		}
		
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
		int ch2 = scanner.nextInt();
		
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
		
		System.out.print(">>>>>선택(0.뒤로가기)"); int ch = scanner.nextInt();
		
		if(ch>0 && ch <= result1.size()) {
			//지역번호
			int pno = result1.get(ch-1).getPno();
			//1페이지당 게시물 시작 번호
			int first = 0;
			//1페이지당 게시물 마지막 번호
			int last = 3;
			//1페이지당 리뷰 3개씩 출력
			
			//리뷰 출력하는 while
			reviewview : while (true) {
			
				//해당 지역의 리뷰만 출력
				ArrayList<ReviewBoardDto> result2 = BoardController.getinstance().reviewView( pno , first , last);
				
				if(result2.size()==0) {
					System.out.println("표시할 리뷰가 없습니다.");
				}
				
				System.out.printf("\n%-3s %-10s %-10s %-20s\n","번호" , "대여차량" , "제목" , "내용" );
				
				//리뷰 개수만큼 반복해서 출력
				for(int i = 0; i<result2.size(); i++) {
					ReviewBoardDto dto = result2.get(i);
					System.out.printf("%-3s %-10s %-10s %-20s\n", dto.getUno() , dto.getKname() , dto.getUtitle() , dto.getUcontent()  );
				}//for end
				
				//페이지처리
				System.out.println("");
				//두번째 페이지부터 출력
				if(first>0) {
					System.out.print("1.이전페이지");
				}
				//마지막 페이지에선 출력하지 않음
				//마지막 페이지 게시물에서 게시물이 3개일때 출력되어도 유효성 검사해서 페이지 안넘어감 
				if(result2.size()==3) {
					System.out.print("2.다음페이지 ");
				}
				//뒤로가기는 항시출력
				System.out.print("3.뒤로가기\n");
				int ch3 = scanner.nextInt();
				
				if(ch3==1) {
					if(first==0) {
						System.out.println("첫번째 페이지 입니다.");
						continue reviewview;
					}
					first-=3; 
				}
				else if(ch3==2) {
				
					if(result2.size()<3) {
						System.out.println("마지막 페이지 입니다.");
						continue reviewview;
					}
					first+=3; 
				}
				else if(ch3==3) {
					first=0; 
					return;
				}
				
			}//리뷰출력 wh end
		
		}
		}//if end
		else {System.out.println("올바른 지역 번호를 입력해주세요");}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		}//wh end
	}//reviewView end
	
	//3. 후기수정
	public void reviewModify() {
		try {
		System.out.println("----------내가 쓴 후기----------");
		//내가쓴 후기만 출력
		ArrayList<ReviewBoardDto> result1 = BoardController.getinstance().myReview();
		
		if( result1.size() == 0 ) {
			System.out.println("안내] 내가 쓴 후기가 없습니다.");
			return;
		}
		
		System.out.printf("%-3s %-4s %-10s %-10s \t %-20s\n", "번호" ,"지역", "이용차량" , "제목" , "내용" );
		
		//내가쓴 후기 전부 출력하기
		for(int i = 0; i<result1.size(); i++) {
			ReviewBoardDto dto = result1.get(i);
			System.out.printf("%-3s %-4s %-10s %-10s \t %-20s\n",
							i+1 , dto.getPname() , dto.getKname() ,
							dto.getUtitle() , dto.getUcontent());
		}//for end
		
		System.out.println("----------수정할 게시물 번호 선택----------");
		System.out.print(">>>>>>>(0.뒤로가기)"); int ch = scanner.nextInt();
		if(ch==0) {return;}
		else if(ch > 0 && ch <= result1.size() ) {
			
			//선택한 게시물의 status 번호
			int status = result1.get(ch-1).getUstatus();
			if(status==0) {
				System.err.println("신고된 게시물은 수정할수 없습니다.(관리자에게 문의)");
				return;
			}
			
			//선택한 게시물의 pk번호
			int uno = result1.get(ch-1).getUno();
			
			System.out.print("수정할 항목 선택/n>>>>");
			System.out.println("1.제목 2.내용 3.제목,내용");
			int ch2 = scanner.nextInt();
			
			//제목수정하기
			if(ch2==1) {
				System.out.printf( "%-10s  %-20s \n", "제목" , "내용" );
				System.out.printf( "%-10s  %-20s \n", result1.get(ch-1).getUtitle() , result1.get(ch-1).getUcontent() );

				System.out.print("수정할 제목 : ");
				scanner.nextLine();
				String title = scanner.nextLine();
				
				boolean result2 = BoardController.getinstance().titleModify( uno , title);
				
				if(result2) {
					System.out.println("안내] 리뷰수정이 완료되었습니다.");
				}
				else {
					System.err.println("안내] 리뷰수정이 실패했습니다. (시스템오류)");
				}
			}//ch2 == 1 end
			//내용 수정하기
			else if(ch2==2) {
				
				System.out.printf( "%-10s  %-20s \n", "제목" , "내용" );
				System.out.printf( "%-10s  %-20s \n", result1.get(ch-1).getUtitle() , result1.get(ch-1).getUcontent() );
				
				System.out.print("수정할 내용 : ");
				scanner.nextLine();
				String content = scanner.nextLine();
				
				boolean result2 = BoardController.getinstance().contentModify( uno , content);
				
				if(result2) {
					System.out.println("안내] 리뷰수정이 완료되었습니다.");
				}
				else {
					System.err.println("안내] 리뷰수정이 실패했습니다. (시스템오류)");
				}
			}//ch2 == 2 end
			//제목 , 내용 수정하기
			else if(ch2==3) {
				
				System.out.printf( "%-10s  %-20s \n", "제목" , "내용" );
				System.out.printf( "%-10s  %-20s \n", result1.get(ch-1).getUtitle() , result1.get(ch-1).getUcontent() );
				
				System.out.print("수정할 제목 : ");
				scanner.nextLine();
				String title = scanner.nextLine();
				System.out.print("수정할 내용 : ");
				
				String content = scanner.nextLine();
				
				boolean result2 = BoardController.getinstance().reviewModify( uno  , title , content);
				
				if(result2) {
					System.out.println("안내] 리뷰수정이 완료되었습니다.");
				}
				else {
					System.err.println("안내] 리뷰수정이 실패했습니다. (시스템오류)");
				}
			}//ch2 == 3 end
			
		}
		}//try end
		catch (Exception e) {
			System.out.println(e);
		}
		
	}//reviewModify end
	
	//4. 후기삭제
	public void reviewDelete() {
		try {
		System.out.println("----------내가 쓴 후기----------");
		//내가쓴 후기만 출력
		ArrayList<ReviewBoardDto> result1 = BoardController.getinstance().myReview();
		
		if( result1.size() == 0 ) {
			System.out.println("안내] 내가 쓴 후기가 없습니다.");
			return;
		}
		
		System.out.printf("%-3s %-4s %-10s %-10s \t %-20s\n", "번호" ,"지역", "이용차량" , "제목" , "내용" );
		
		//내가쓴 후기 전부 출력하기
		for(int i = 0; i<result1.size(); i++) {
			ReviewBoardDto dto = result1.get(i);
			System.out.printf("%-3s %-4s %-10s %-10s \t %-20s\n",
							i+1 , dto.getPname() , dto.getKname() ,
							dto.getUtitle() , dto.getUcontent());
		}//for end
		
		System.out.println("----------삭제할 게시물 번호 선택----------");
		System.out.print(">>>>>>>(0.뒤로가기)"); int ch = scanner.nextInt();
		if(ch==0) {return;}
		if(ch > 0 && ch <= result1.size() ) {
			
			//선택한 게시물의 status 번호
			int status = result1.get(ch-1).getUstatus();
			if(status==0) {
				System.err.println("신고된 게시물은 삭제할수 없습니다.(관리자에게 문의)");
				return;
			}
			//선택한 게시물의 pk번호
			int uno = result1.get(ch-1).getUno();
			System.out.println("안내] 정말 삭제하시겠습니까?");
			System.out.println("1.예 2.아니오");

			int ch2 = scanner.nextInt();
			
			if(ch2 == 1 ) {
			
				//pk 번호보내고 true/false 받기
				boolean result2 = BoardController.getinstance().reviewDelete(uno);
				if(result2) {
					System.out.println("안내] 게시물이 삭제되었습니다.");
				}
				else {
					System.err.println("안내] 게시물 삭제에 실패했습니다.\n(시스템 오류) 관리자에게 문의하세요");
				}
			}
			else if(ch2 == 2) {
				System.out.println("이전화면으로 돌아갑니다.");
				return;
			}
			
		}
		
		
	}//try end
		catch (Exception e) {
			System.out.println(e);
		}
	
	}//reviewDelete end
	
}//class end