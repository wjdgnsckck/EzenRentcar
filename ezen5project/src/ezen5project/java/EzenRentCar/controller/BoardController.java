package ezen5project.java.EzenRentCar.controller;

import java.util.ArrayList;

import ezen5project.java.EzenRentCar.model.dao.BoardDao;
import ezen5project.java.EzenRentCar.model.dto.BranchDto;
import ezen5project.java.EzenRentCar.model.dto.RentRecordDto;
import ezen5project.java.EzenRentCar.model.dto.ReviewBoardDto;

// 한 줄 후기게시판 클래스
public class BoardController {

	private static BoardController boardController = new BoardController();
	public static BoardController getinstance() { return boardController; }
	private BoardController() {}
	
	//1. 후기등록
	
	//1-1 해당 회원의 대여내역만 출력 하는 함수
	//필드값 애매해서 렌트내역 dto로 ArrayList 만들었음
	public ArrayList<RentRecordDto> reviewAdmit() {
		
		//dao 로 로그인 세션 보내기
		return BoardDao.getinstance().reviewAdmit( MemberController.getInstance().getLoginSession() );
		
	}
								//렌트번호								
	public boolean reviewWrite( int lno , String title , String content ) {
		//유효성 검사 - 제목 내용 1글자 이상 제목 100글자 내용 300 글자 미만인 경우만 리뷰 등록 
		if( title.length() == 0 &&
				content.length() == 0 &&
					title.length() < 100 &&
					content.length() == 300 
					) { return false; }
		//dto에 렌트번호 , 제목 , 내용 객체화 해서 보내기
		ReviewBoardDto reviewBoardDto = new ReviewBoardDto( lno , title , content );	
		
		//객체 보내고 바로 반환받기
		return BoardDao.getinstance().reviewWrite(reviewBoardDto);
		
	}
		
	//2. 후기보기
		//2-2 후기 보기전 지역선택
	public ArrayList<BranchDto> reviewCityDtoView(int f , int l) {
		
		return BoardDao.getinstance().reviewCityDtoView(f , l);
		
	}
	//pno 일치한 리뷰만 출력하기 
	public ArrayList<ReviewBoardDto> reviewView( int pno , int first , int last ) {
		
		return BoardDao.getinstance().reviewView( pno , first , last );
		
	}
		
	//3. 후기수정
	//3-1 제목수정
	public boolean titleModify( int uno , String title ) {
		
		boolean result = BoardDao.getinstance().titleModify(uno , title);
		
		return result;
	}
	//3-2 내용수정
	public boolean contentModify( int uno , String content ) {
		
		boolean result = BoardDao.getinstance().contentModify(uno , content);
		return result;
	}
	//3-3 제목 , 내용 수정
	public boolean reviewModify( int uno , String title , String content ) {

		boolean result = BoardDao.getinstance().reviewModify(uno , title, content);
		return result;
	}
		
	//4. 후기삭제
		//4-1 내가 쓴 리뷰만 출력
	public ArrayList<ReviewBoardDto> myReview() {
		
		return BoardDao.getinstance().myReview( MemberController.getInstance().getLoginSession()/* 로그인세션 */ );
		
	}
	//선택한 번호의 게시물 삭제 
	public boolean reviewDelete( int uno ) {
		
		boolean result = BoardDao.getinstance().reviewDelete( uno ); 
		
		return result;
	}

	
}
