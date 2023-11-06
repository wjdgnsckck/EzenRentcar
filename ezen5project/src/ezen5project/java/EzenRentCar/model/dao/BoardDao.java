package ezen5project.java.EzenRentCar.model.dao;

import java.util.*;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import ezen5project.java.EzenRentCar.model.dto.BranchDto;
import ezen5project.java.EzenRentCar.model.dto.RentRecordDto;
import ezen5project.java.EzenRentCar.model.dto.ReviewBoardDto;

// 한 줄 후기게시판 클래스
public class BoardDao extends Dao {
 
	private static BoardDao boardDao = new BoardDao();
	public static BoardDao getinstance() { return boardDao; }
	private BoardDao() {}

	//1. 후기등록
		//1-1 당사자의 후기작성 가능한 렌트내역만 출력
	public ArrayList<RentRecordDto> reviewAdmit(int mno){
		
		ArrayList<RentRecordDto> list = new ArrayList<>();
		
		try {
			String sql =
					"select r.lno , r.lstartlog , r.lcompletelog , cp.kname from rentlog r " 
						+"natural join carprofile c natural join carpricemenu cp " 
							+"where lcompletelog is not null and mno = ? "
							+"and lno not in (select lno from content)"; 
					//최종 완료 대여내역값이 있는것 (null값이 아닌것)
					//mno 가 일치하는거
					//리뷰테이블에 lno가 없는거 = 아직 리뷰작성 안된것
			
			ps = conn.prepareCall(sql);
			ps.setInt(1 , mno);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				//렌트번호 , 시작시간 , 최종완료시간 , 대여차량 받아오기
				RentRecordDto dto = new RentRecordDto(
						rs.getInt(1) , rs.getString(2) ,
						rs.getString(3) , rs.getString(4)
						);
				list.add(dto);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
	
		//2.후기 작성 저장
	public boolean reviewWrite( ReviewBoardDto reviewBoardDto ) {
		
		try {
			String sql = "insert into content ( lno , utitle , ucontent ) values ( ? , ? , ? ) ";
			
			ps = conn.prepareStatement(sql);
			//렌트번호 , 제목 , 내용 각각 ? 자리에 넣기
			ps.setInt(1, reviewBoardDto.getLno());
			ps.setString(2, reviewBoardDto.getUtitle());
			ps.setString(3, reviewBoardDto.getUcontent());
			
			int row = ps.executeUpdate();
			
			if(row ==1 ) {
				return true;
			}
		}catch (Exception e) {
			System.out.println(e);
		}
		return false;
		
	}
		
	//2. 후기보기
		//2-1 후기 보기 전 지역 선택
	public ArrayList<BranchDto> reviewCityDtoView( int f, int l ){
		
		ArrayList<BranchDto> list = new ArrayList<>();
		
		try {
			//지역 번호 순으로 오름차순 정렬 출력
			String sql ="select * from carpoint order by pno asc limit ? , ? ";
			
			ps = conn.prepareCall(sql);
			ps.setInt(1, f);
			ps.setInt(2, l);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				//지역번호 , 지역이름 받아오기
				BranchDto dto = new BranchDto(
						rs.getInt(1) , rs.getString(2)
						);
				list.add(dto);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
		
		
	}
	//지역 선택 했으면 그 지역의 있는 리뷰만 출력 
	public ArrayList<ReviewBoardDto> reviewView( int pno , int first , int last  ) {
		
		ArrayList<ReviewBoardDto> list = new ArrayList<>();
		
		try {
	
			String sql = 
					"SELECT ct.uno , cp.pno, cp2.kname, " 
					+"case when ustatus = 0 then '신고된 후기' when ustatus = 1 then ct.utitle end , " 
					+"case when ustatus = 0 then '열람할 수 없습니다.' when ustatus = 1 then ct.ucontent end, "
					+"ustatus "
					+"FROM carpoint cp " 
					+"JOIN carprofile cpr ON cp.pno = cpr.pno " 
					+"JOIN carpricemenu cp2 ON cpr.kno = cp2.kno " 
					+"JOIN rentlog rl ON cpr.bno = rl.bno "
					+"JOIN content ct ON rl.lno = ct.lno "
					+"WHERE cp.pno = ? limit ? , ?";
			//게시물번호 , 지역번호 , 차이름 , 제목 , 내용 출력
			//만약 status = 0 이면 utitle을 '신고된 후기'로 표시 / 1이면 정상출력
			//만약 status = 0 이면 utitle을 '열람할 수 없습니다'로 표시 / 1이면 정상출력
			//지역 기준으로 검색
			//차량정보 테이블에서 pno 값이 일치하는것 기준으로 join
			//차량단가표 kno 와 차량정보 kno 값이 일치하는것 기준으로 join
			//렌트로그 bno와 차량정보 bno 값이 일치하는것 기준으로 join
			//리뷰 lno 와 렌트로그 lno 가 일치하는것 기준으로 join
			//이중에서 입력한 지역번호를 포함하는것만 출력
			
			ps = conn.prepareCall(sql);
			ps.setInt(1, pno);
			ps.setInt(2, first);
			ps.setInt(3, last);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				//리뷰 번호 , 차량이름 , 제목 , 내용 , 리뷰상태 받이오기
				ReviewBoardDto dto = new ReviewBoardDto(
						rs.getInt(1) , rs.getString(3) , 
						rs.getString(4) , rs.getString(5), rs.getInt(6)
						);
				list.add(dto);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
	
	//3. 후기수정
	
		//3-1 제목수정
		public boolean titleModify( int uno , String title ) {
			
			try {
				
				String sql = "update content set utitle = ? where uno = ?";
				
				ps = conn.prepareStatement(sql);
				ps.setString(1, title);
				ps.setInt(2, uno);
				
				int row = ps.executeUpdate();
				
				if(row == 1) {return true;}
				
			} catch (Exception e) {
				System.out.println(e);
			}
			return false;
			
		}
		
		//3-2 내용수정
		public boolean contentModify( int uno , String content ) {
			
			try {
				
				String sql = "update content set ucontent = ? where uno = ?";
				ps = conn.prepareStatement(sql);
				
				ps.setString(1, content);
				ps.setInt(2, uno);

				int row = ps.executeUpdate();
				if(row == 1) {return true;}
				
			} catch (Exception e) {
				System.out.println(e);
			}
			return false;
		}
		
		//3-3 제목 , 내용수정
		public boolean reviewModify( int uno , String title , String content ) {

			
			try {
				
				String sql = "update content set utitle = ? , ucontent = ? where uno = ?";
				ps = conn.prepareStatement(sql);
				
				ps.setString(1, title);
				ps.setString(2, content);
				ps.setInt(3, uno);

				int row = ps.executeUpdate();
				
				if(row == 1) {return true;}
				
			} catch (Exception e) {
				System.out.println(e);
			}
			return false;
		}
		
	//4. 후기삭제
		//4-1 내가쓴 리뷰만 출력
	public ArrayList<ReviewBoardDto> myReview( int login ){
		
			ArrayList<ReviewBoardDto> list = new ArrayList<>();
		
		 try {
			 
			 String sql ="SELECT ct.uno , cp.pname , cp2.kname, "
			 		+ "case when ustatus = 0 then '신고된 후기' when ustatus = 1 then ct.utitle end, "
			 		+ "case when ustatus = 0 then '열람할 수 없습니다.' when ustatus = 1 then ct.ucontent end , ct.ustatus "
					 +"FROM content ct "
					 +"JOIN rentlog rl ON ct.lno = rl.lno "
					 +"JOIN carprofile cpr  ON cpr.bno = rl.bno "
					 +"JOIN carpoint cp  ON cp.pno = cpr.pno "
					 +"JOIN carpricemenu cp2 ON cpr.kno = cp2.kno "
					 +"JOIN memberlist ml ON ml.mno = rl.mno "
					 +"WHERE ml.mno = ?";
			 	//게시물 번호 , 지역이름 , 대여차량 , 제목 , 내용 출력 
			 	//리뷰기준으로 검색
			 	//렌트로그 lno 와 리뷰 lno 가 일치
			 	//차량정보 bno 와 렌트로그 bno가 일치
			 	//지역 pno와 차량정보 pno 가 일치
			 	//차량단가표 kno와 차량정보 kno가 일치
			 	//회원정보 mno와 렌트로그 mno 가 일치
			 	//입력한 mno 값을 포함하는것만 출력
			 
			 
			 ps = conn.prepareStatement(sql);
			 ps.setInt( 1 , login);
			 rs = ps.executeQuery();
			 
			 while (rs.next()) {
				//리뷰번호 , 지역이름 , 대여차량 , 제목 , 내용 받아오기
				 ReviewBoardDto dto = new ReviewBoardDto(
						rs.getInt(1) , rs.getString(2) , 
						rs.getString(3) , rs.getString(4) , 
						rs.getString(5) , rs.getInt(6)
						);
				 list.add(dto);
			 }
			
			 
		 }catch (Exception e) {
			 System.out.println(e);
		 }
		 return list;
		
	}
	//리뷰 삭제하기
	public boolean reviewDelete( int uno ) {
		
		try {
			//선택한 리뷰번호의 리뷰 삭제
			String sql = "delete from content where uno  = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt( 1 , uno);
			
			int row = ps.executeUpdate();
			//삭제한게 있으면 true
			if(row == 1) { return true; }
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
	
}
