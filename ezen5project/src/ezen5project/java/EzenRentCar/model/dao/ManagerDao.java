package ezen5project.java.EzenRentCar.model.dao;


import java.util.ArrayList;


import ezen5project.java.EzenRentCar.model.dto.BranchDto;
import ezen5project.java.EzenRentCar.model.dto.CarDto;
import ezen5project.java.EzenRentCar.model.dto.RentPriceDto;


import java.sql.PseudoColumnUsage;
import java.util.ArrayList;


import ezen5project.java.EzenRentCar.controller.ManagerController;

import ezen5project.java.EzenRentCar.model.dto.CouponDto;
import ezen5project.java.EzenRentCar.model.dto.ReviewBoardDto;


import ezen5project.java.EzenRentCar.model.dto.BranchSttDto;
import ezen5project.java.EzenRentCar.model.dto.CarSttDto;
import ezen5project.java.EzenRentCar.model.dto.MemberSttDto;


// 렌트 관리자 Dao 클래스


public class ManagerDao extends Dao {

	private static ManagerDao managerDao = new ManagerDao();
	public static ManagerDao getInstance() {
		return managerDao;
	}
	private ManagerDao() {}
	
	
/*--------------------------------------------------------------------------*/
	
	// 1. 차량관리 - 환희
	
	// 1-1 DB carpoint 테이블 불러오기
	public ArrayList<BranchDto> viewCarpoint(){
		
		try {
			
			// BranchDto 타입 리스트에 저장하여 반환
			ArrayList<BranchDto> carpointList = new ArrayList<>();
			String sql = "select * from carpoint order by pno asc";
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while( rs.next() ) {
				
				BranchDto branchDto = new BranchDto(rs.getInt(1), rs.getString(2));
				carpointList.add( branchDto );
				
			}
			return carpointList;
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return null;
		
	}
		
	// A 차량 조회하여 출력
	public ArrayList<CarDto> printCar( int pno ) {
		
		try {
			
			ArrayList<CarDto> carpointList = new ArrayList<>();
			String sql = "select bno, kname, pno, bstate, kprice from carprofile a, carpricemenu b where a.kno = b.kno and pno = ? order by kprice asc";
			

			ps = conn.prepareStatement(sql);
			ps.setInt(1, pno);
			rs = ps.executeQuery();

			
			while( rs.next() ) {
				
				CarDto carDto = new CarDto(rs.getInt(1), rs.getInt(4), rs.getInt(3), rs.getInt(5), rs.getString(2));
				
				carpointList.add( carDto );
				
			}
			
			return carpointList;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return null;
		
	}
	// B-1 차량 등록
	public boolean registrateCar(int pno, int kno) {
		
		try {
			
			String sql = "insert into carprofile ( bstate , pno , kno  ) values( 1 , ? , ? )";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pno);
			ps.setInt(2, kno);
			ps.executeUpdate();
			
			return true;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return false;
		
	}
	// B-2 신규 또는 기존차량인지 확인
	public int checkNewCar( String newCar ) {

		try {
			
			String sql = "select kno from carpricemenu where kname = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, newCar);
			rs = ps.executeQuery();
			
			if(rs.next()) return rs.getInt(1);
			return 0;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return -1000;
	}
	// B-3 신규 차량일 경우 carpricemenu테이블에 가격 등록
		// 신규 등록 후 kno 반환
	public int registratePrice( String newCar, int price ) {
		
		try {
			String sql = "insert into carpricemenu ( kname , kprice ) values ( ?, ? );";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, newCar);
			ps.setInt(2, price);
			ps.executeUpdate();
			
			// 가격 테이블에 등록 후 checkNewCar메서드 실행하여 kno반환
			return checkNewCar( newCar );
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return -1000;
	}
	
	
	// C 차량 삭제
	public boolean deleteCar( int bno ) {
		
		try {
			String sql = "delete from carprofile where bno = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt( 1, bno );
			ps.executeUpdate();
			return true;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
		
	}
	
	
	// D-1 가격 변경
	public boolean changePrice( int kno, int kprice) {
		
		try {
			String sql = "update carpricemenu set kprice = ? where kno = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, kprice);
			ps.setInt(2, kno);
			ps.executeUpdate();
			
			return true;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
		
	}
	// D-2 가격 테이블 출력
	public ArrayList<RentPriceDto> viewPrice() {
		
		try {
			
			ArrayList<RentPriceDto> list = new ArrayList<>();
			
			String sql = "select * from carpricemenu order by kname asc;";
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				RentPriceDto rentPriceDto = new RentPriceDto( rs.getInt(1), rs.getString(2), rs.getInt(3) );
				list.add(rentPriceDto);
				
			}
			return list;
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return null;
	}
	
	
	// E 지점간 차량이동
	public boolean transferCar( int pno, int bno ) {
		
		try {
			
			String sql = "update carprofile set pno = ? where bno = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pno);
			ps.setInt(2, bno);
			ps.executeUpdate();
			
			return true;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
	
	
	
/*--------------------------------------------------------------------------*/
	// 2. 쿠폰관리 - 민재	
		
	public ArrayList<CouponDto> couponAllview() {
		
		ArrayList<CouponDto> list = new ArrayList();
		
		try {
			
			String sql = "select * from discount";
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
				CouponDto dto = new CouponDto(
						rs.getInt(1) , rs.getString(2) ,
						rs.getInt(3) , rs.getString(4)
						);
				list.add(dto);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}	
	//쿠폰등록함수
	public int couponAdd( String dname , int dpercent , String dnumber ) {
		
		try {
			
			String spl = "insert discount(dname, dpercentage, dnumber) "
					+ "values( ? , ? , ?)";
			
			ps = conn.prepareStatement(spl);
			ps.setString( 1 , dname);
			ps.setInt( 2 , dpercent);
			ps.setString( 3 , dnumber);
			
			int row = ps.executeUpdate();
			if(row==1) {
				return 1;
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return 4;
	}	
	//쿠폰 이름 수정
	public int couponNameModify( int dno , String newValue ) {
		
		try {
			String spl = "update discount set dname = ? where dno = ?";
			ps =conn.prepareStatement(spl);
			ps.setString(1, newValue);
			ps.setInt(2, dno);
			
			int row = ps.executeUpdate();
			if(row==1) {return 1;}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return 3;
	}
	//쿠폰 할인률 수정
	public int couponPerModify( int dno , int newValue) {
		
		try {
			String spl = "update discount set dpercentage = ? where dno = ?";
			ps =conn.prepareStatement(spl);
			ps.setInt(1, newValue);
			ps.setInt(2, dno);
			
			int row = ps.executeUpdate();
			if(row==1) {return 1;}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return 3;
		
	}
	
	//쿠폰삭제함수
	public boolean couponDelete( int dno ) {
		try {
			String sql = "delete from discount where dno = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, dno);
			
			int row =ps.executeUpdate();
			if(row==1) { return true; }
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
		
/*--------------------------------------------------------------------------*/
	// 3. 게시물관리 - 민재			
		
		
	public ArrayList<ReviewBoardDto> reviewView( int pno ){
		
		ArrayList<ReviewBoardDto> list = new ArrayList<>();
		
		try {
	
			String sql = "SELECT ct.uno , cp.pno, cp2.kname, ct.utitle , ct.ucontent , ustatus , "
					+ "case when ustatus = 0 then '신고중' when ustatus = 1 then '정상리뷰' end as 상태 "
					+ "FROM carpoint cp "
					+ "JOIN carprofile cpr ON cp.pno = cpr.pno "
					+ "JOIN carpricemenu cp2 ON cpr.kno = cp2.kno "
					+ "JOIN rentlog rl ON cpr.bno = rl.bno "
					+ "JOIN content ct ON rl.lno = ct.lno "
					+ "WHERE cp.pno = ?";
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
			rs = ps.executeQuery();
			
			while (rs.next()) {
				//리뷰 번호 , 차량이름 , 제목 , 내용 , 리뷰상태 받이오기
				ReviewBoardDto dto = new ReviewBoardDto(
						rs.getInt(1) , rs.getString(3) , 
						rs.getString(4) , rs.getString(5), rs.getInt(6) , rs.getString(7)
						);
				list.add(dto);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
		
	
	
	public boolean boardReport( int newStatus , int uno) {
		try {
			
			String sql = "update content set ustatus = ? where uno = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, newStatus);
			ps.setInt(2, uno);
			
			int row = ps.executeUpdate();
			if(row==1) { return true; }
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return false ;
	}
	

		

/*--------------------------------------------------------------------------*/
	// 4. 통계 - 의선	
		
	// 차량별 통계 Dao 함수
	public ArrayList<CarSttDto> carStatistics(String stt) {
		ArrayList<CarSttDto> list = new ArrayList();
		
		try {
			String sql = "SELECT r.bno, cp.kname, sum(kprice) as 매출액, COUNT(r.bno) AS count\r\n"
					+ "FROM rentlog r, carprofile c, carpricemenu cp\r\n"
					+ "where lstartlog like '%"+stt+"%' and r.bno = c.bno and c.kno = cp.kno\r\n"
					+ "GROUP BY bno\r\n"
					+ "ORDER BY 매출액 DESC";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				CarSttDto cDto = new
						CarSttDto(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
				list.add(cDto);
			}
			return list;
			
			
		} catch (Exception e) {System.out.println(e);}
		
		return null;
		
	}
	
	// 지점별 통계 Dao 함수
	public ArrayList<BranchSttDto> branchStatistics(String stt) {
		ArrayList<BranchSttDto> list = new ArrayList<>();
		
		try {
			String sql = "SELECT ca.pno, ca.pname, sum(kprice) as 매출액, COUNT(ca.pno) AS count\r\n"
					+ "FROM rentlog r, carprofile c, carpricemenu cp, carpoint ca\r\n"
					+ "where r.lstartlog like '%"+stt+"%' and r.bno = c.bno and c.kno = cp.kno and c.pno = ca.pno\r\n"
					+ "GROUP BY pno\r\n"
					+ "ORDER BY 매출액 DESC";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				BranchSttDto bDto = new
						BranchSttDto(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
				list.add(bDto);
			}
			return list;
			
		} catch (Exception e) {System.out.println(e);}
		
		return null;
	}
	
	// 회원별 통계 Dao 함수
	public ArrayList<MemberSttDto> memberStatistics(String stt) {
		ArrayList<MemberSttDto> list = new ArrayList();
		
		try {
			String sql = "SELECT r.mno, m.mid, m.mad, (100 + substring(now(), 3, 2) - substring(m.msno, 1, 2) + 1) as 연령,"
					+ "COUNT(r.mno) AS 대여횟수, sum(cp.kprice) as 총사용금액\r\n"
					+ "FROM rentlog r, carprofile c, carpricemenu cp, memberlist m\r\n"
					+ "where lstartlog like '%"+stt+"%' and r.mno = m.mno and c.kno = cp.kno and r.bno = c.bno\r\n"
					+ "GROUP BY r.mno\r\n"
					+ "ORDER BY 총사용금액 DESC";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				MemberSttDto sDto = new
						MemberSttDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
				list.add(sDto);
			}
			return list;
			
			
		} catch (Exception e) {System.out.println(e);}
		
		return null;
	}

		
		
	

}
