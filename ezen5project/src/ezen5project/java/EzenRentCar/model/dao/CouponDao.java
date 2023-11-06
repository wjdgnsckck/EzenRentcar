package ezen5project.java.EzenRentCar.model.dao;

import java.util.ArrayList;

import ezen5project.java.EzenRentCar.model.dto.CouponSignDto;
import ezen5project.java.EzenRentCar.view.CouponView;

// 쿠폰기능 클래스
public class CouponDao extends Dao {
	
	// 싱글톤
	private static CouponDao couponDao = new CouponDao();
	
	public static CouponDao getInstance() {
		return couponDao;
	}
	
	private CouponDao() {}
	
	// 쿠폰등록
	public boolean couponSign(int mno, int dno) {
		try {
			String sql = "insert unusedcoupon(mno, dno, tupdate) values(?, ?, DATE_FORMAT(NOW(),'%Y-%m-%d'))";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, mno);
			ps.setInt(2, dno);
			int row = ps.executeUpdate();
			if(row == 1) {
				return true;
			}
			
		} catch (Exception e) {System.out.println(e);}
		
		
		return false;
	}
	
	// 쿠폰번호 확인
	public int couponCheck(String coupon) {
		try {
			String sql = "select dno from discount where dnumber = ? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, coupon);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (Exception e) {System.out.println(e);}
		
		return -1;
	}
	
	// 쿠폰보유내역 출력
	public ArrayList<CouponSignDto> couponPrint(int mno) {
		ArrayList<CouponSignDto> list = new ArrayList<>();
		try {
			String sql = "select u.*, d.dname, d.dpercentage from unusedcoupon u, discount d\r\n"
					+ "where u.dno = d.dno and mno = ?\r\n"
					+ "order by tupdate desc;";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, mno);
			rs = ps.executeQuery();
			while(rs.next()) {
				CouponSignDto cDto = new
						CouponSignDto(rs.getInt(1), rs.getInt(2), rs.getInt(3), 
								rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
				list.add(cDto);
			}
			return list;
			
			
		} catch (Exception e) {System.out.println(e);}
		
		
		return null;
	}
 
}
