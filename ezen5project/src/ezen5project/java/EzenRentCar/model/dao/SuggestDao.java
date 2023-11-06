package ezen5project.java.EzenRentCar.model.dao;

import java.util.ArrayList;

import ezen5project.java.EzenRentCar.model.dto.BranchDto;
import ezen5project.java.EzenRentCar.model.dto.CarDto;
import ezen5project.java.EzenRentCar.model.dto.EventCarDto;
import ezen5project.java.EzenRentCar.model.dto.MapDto;
import ezen5project.java.EzenRentCar.model.dto.RentPriceDto;

public class SuggestDao extends Dao {

	private static SuggestDao suggestDao = new SuggestDao();
	public static SuggestDao getInstance() {
		return suggestDao;
	}
	private SuggestDao() {};
	


	// A-1 이벤트 등록
	public boolean insertEvent( int kno ) {
		
		try {
			
			String sql = "insert eventcar(kno) value(?);";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, kno);
			ps.executeUpdate();
			
			return true;
			
		} catch (Exception e) {
			
		}
			
		return false;

	}
	// A-2 이벤트 진행 중인 차량 테이블 출력
	public ArrayList<EventCarDto> viewEventCarList() {
		
		try {
			
			ArrayList<EventCarDto> list = new ArrayList<>();
			
			String sql = "select * from eventcar";
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				EventCarDto eventCarList = new EventCarDto( rs.getInt(1), rs.getInt(2) );
				list.add(eventCarList);
			}
			return list;
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return null;
	}
	
	
	
	// B-1 이벤트 삭제
	public boolean deleteEvent( int kno ) {
		
		try {
			String sql = "delete from eventcar where kno = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, kno);
			ps.executeUpdate();
			
			return true;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return false;
	}
	// B-2 DB carpricemenu, eventcar의 kno 중복 필드 불러옴
	public ArrayList<RentPriceDto> eventCarInfo() {
		
		try {
			ArrayList<RentPriceDto> list = new ArrayList<>();
			
			String sql = "select a.kno, a.kname, a.kprice from carpricemenu a, eventcar b where a.kno = b.kno";
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				RentPriceDto rentPriceDto = new RentPriceDto( rs.getInt(1), rs.getString(2), rs.getInt(3) );
				list.add( rentPriceDto );
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return null;
	}
	

	// D-0 전국 서비스 지역 출력
	public ArrayList<MapDto> viewLocation() {
		
		try {
			ArrayList<MapDto> list = new ArrayList<>();
			
			String sql = "select * from location";
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				
				MapDto mapDto = new MapDto( rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4), rs.getInt(5) );
				list.add( mapDto );
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println();
		}
		
		return null;
		
	}
	// D-1 전국내 지역 등록
	public int insertLocation( MapDto mapDto ) {
		
		try {
			String sql = "insert location(jname, jlatitude, jlongitude) values(?, ?, ?)";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, mapDto.getJname());
			ps.setDouble(2, mapDto.getJlatitude());
			ps.setDouble(3, mapDto.getJlongitude());
			ps.executeUpdate();
			
			return 1000;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return 10000;
	}
	// D-2 지역 등록 중복검사
		// 기등록된 지역인지 확인
	public int checkLocationName( MapDto mapDto ) {
		
		try {
			String sql = "select * from location where jname = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, mapDto.getJname());
			rs = ps.executeQuery();
			
			if( rs.next() ) {
				return 101;		// 실패) 기등록된 지역으로 식별
			} else return 1000;	// 성공) 등록되지 않은 지역명으로 식별
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return 100;	// 실패) 관리자문의
	}
	
	
	// D-2 전국내 지역 삭제
	public boolean deleteLocation( int jno ) {
		
		try {
			String sql = "delete from location where jno = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, jno);
			ps.executeUpdate();
			
			return true;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return false;
	}
	
	// E 탐색 지점의 차량 정보 불러오기
	public BranchDto checkCarPoint( int pno, int kno ) {
		
		try {
			/*
			String sql = "select a.bno, a.bstate, a.pno, a.kno, b.kname "
					+ "from carprofile a, carpricemenu b "
					+ "where a.kno = b.kno and a.pno = ? and a.kno = ? and a.bstate = 1";
			
			 */
			String sql = "select a.pno, c.pname from carprofile a, carpricemenu b, carpoint c"
					+ " where a.kno = b.kno and a.pno = ? and a.kno = ? and a.bstate "
					+ "= 1 and c.pno = a.pno ";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pno);
			ps.setInt(2, kno);
			rs = ps.executeQuery();
			
			// 지점 내 이벤트 차량이 존재하면 실행
			if( rs.next() ) {
				
				// 이벤트 차량 확인 후 대여 가능한 상태인지 확인확인
				// 모든 대여 조건을 만족할 시 해당 pno 반환
				BranchDto branchDto = new BranchDto( rs.getInt(1), rs.getString(2) );
				return branchDto;
				
			}
			return null;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
