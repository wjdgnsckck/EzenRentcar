package ezen5project.java.EzenRentCar.model.dao;

import java.util.ArrayList;

import ezen5project.java.EzenRentCar.controller.Mymenu;
import ezen5project.java.EzenRentCar.model.dto.MemberDto;
import ezen5project.java.EzenRentCar.model.dto.RentRecordDto;

// 마이메뉴 클래스(Dao)
public class MyMenu extends Dao {
	
	private static MyMenu mymenu = new MyMenu();
	
	public static MyMenu getInstance() {
			return mymenu;
	}
		
	private MyMenu() {}
	
	// --------- 메소드 ----------------------------------------
	
	// 내정보 조회
	public MemberDto memberInfo(int mno) {
		try {
			String sql = "select*from memberlist where mno = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, mno);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				MemberDto mDto = new 
						MemberDto(rs.getInt(1), rs.getString(2), rs.getString(3),
								rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
				return mDto;
			}
			
		} catch (Exception e) {System.out.println(e);}
		return null;
	}
	
	// 대여내역 조회
	public ArrayList<RentRecordDto> rentRecord(int mno) {
		
		ArrayList<RentRecordDto> list = new ArrayList<>();
		
		try {
			String sql = "select rl.lno, rl.bno, rl.lstartlog,\r\n"
					+ "rl.lendlog, rl.lcompletelog, rl.mno, cm.kname from rentlog rl, carprofile cp, carpricemenu cm where rl.bno = cp.bno and cp.kno = cm.kno and rl.mno = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, mno);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				RentRecordDto rDto = new
						RentRecordDto(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
								rs.getString(5), rs.getInt(6), rs.getString(7));
				list.add(rDto);
			}
			
			
		} catch (Exception e) {System.out.println(e);}
		
		return list;
	}
	
	// 개인정보변경
	public int infoModify(int mno, String modify, String text) {
		
		// 변경하려는 정보가 변경 전 정보와 같으면 업데이트x
		try {
			String sql = "select "+modify+" from memberlist where mno = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, mno);
			rs = ps.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(text)) {return 3;}
			}
			
		} catch (Exception e) {System.out.println(e);}
		
		try {
			String sql = "update memberlist set "+modify+" = ? where mno = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, text);
			ps.setInt(2, mno);
			int row = ps.executeUpdate();
			
			if(row == 1) {return 1;}
			
		} catch (Exception e) {System.out.println(e);}
		
		return 2;
	}
	
	// 비밀번호 확인
	public boolean passwordCheck(int mno, String pw) {
		
		try {
			String sql ="select mpw from memberlist where mno = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, mno);
			rs = ps.executeQuery();
			
			if(rs.next() && rs.getString(1).equals(pw)) {return true;}
			
		} catch (Exception e) {System.out.println(e);}
		
		return false;
	} 
	
	// 회원탈퇴
	public boolean memberDelete(int mno) {
		try {
			String sql = "delete from memberlist where mno = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, mno);
			int row = ps.executeUpdate();
			if(row == 1) {
				return true;
			}
			
		} catch (Exception e) {System.out.println(e);}
		
		return false;
		
	}

}
 