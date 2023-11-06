package ezen5project.java.EzenRentCar.model.dao;

import ezen5project.java.EzenRentCar.model.dto.MemberDto;

// 회원기능(로그인/회원가입/아이디찾기/비밀번호 찾기 등) 클래스
public class MemberDao extends Dao {
	private static MemberDao memberDao = new MemberDao();
	public static MemberDao getInstance() {
		return memberDao;
	}
	private MemberDao() {}
	
	// 1 아이디 중복검사
	// 2 아이디 중복검사 통과 후 휴대전화 중복검사
	public boolean duplicationCheck(String feild, String inputValue) {
		
		try {
			//String sql = "select * from member where "+검색할필드명+" = '"+검색할값+"'";
			//" = '"+검색할값+"'"
			//위와 같이할 경우 작은따옴표까지 기입해야하는 번거로움이 있기에 아래와 같이 물음표를 넣어줌
			 
			String sql = "select * from memberlist where "+feild+" = ?";
			ps = conn.prepareStatement(sql);

			ps.setString(1, inputValue);

			rs = ps.executeQuery();

			if(rs.next()) {

				return true;
			}

		} catch (Exception e) {

			System.out.println(e);
		}
		
		return false;
	}
	
	
	// 회원가입 기능
	public int signUp(MemberDto memberDto) {
		
		try {
			
			String sql = "insert into memberlist(mname, msno, mid, mpw, mad, mph) values(?, ?, ?, ?, ?, ?)";

			ps = conn.prepareStatement(sql);

			ps.setString(1, memberDto.getMname() );
			ps.setString(2, memberDto.getMsno() );
			ps.setString(3, memberDto.getMid() );
			ps.setString(4, memberDto.getMpw() );
			ps.setString(5, memberDto.getMad() );
			ps.setString(6, memberDto.getMph() );

			ps.executeUpdate();

			return 0;
			
			
		} catch (Exception e) {
			System.out.println(e);
			return 1000;
		}
	}
	
	
	// 로그인 기능
	public int login(String id, String pw) {
		
		
		try {
			
			String sql = "select mno from memberlist where mid = ? and mpw = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			
			rs = ps.executeQuery();
			
			if( rs.next() ) {
				// 입력 정보가 일치할 경우
				return rs.getInt(1);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		// 입력 정보가 일치하지 않을 경우
		return 0;
	}
	
	// 아이디 찾기
	public String searchId(String mname, String msno, String mph) {
		
		try {
			String sql = "select mid from memberlist where mname = ? and msno = ? and mph = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, mname);
			ps.setString(2, msno);
			ps.setString(3, mph);
			
			rs = ps.executeQuery();
			
			if( rs.next() ) {
				// 입력 정보가 일치할 경우
				return rs.getString(1);
			}

		} catch (Exception e) {
			System.out.println(e);
			// DB예외 발생
			
		}
		// 입력 정보가 일치하지 않을 경우
		return "0";

	}
	
	
	// 비밀번호 찾기
	public String searchPw(String mname, String msno, String mid) {
		
		try {
			String sql = "select mpw from memberlist where mname = ? and msno = ? and mid = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, mname);
			ps.setString(2, msno);
			ps.setString(3, mid);
			
			rs = ps.executeQuery();
			
			if( rs.next() ) {
				// 입력 정보가 일치할 경우
				return rs.getString(1);
			}

		} catch (Exception e) {
			System.out.println(e);
			// DB예외 발생
			
		}
		// 입력 정보가 일치하지 않을 경우
		return "0";

	}
	
	//로그인된 이름 가져오기
	public String loginName( int mno ) {
		
		try {
			String sql = "select mname from memberlist where mno = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, mno);
			
			rs=ps.executeQuery();
			
			if(rs.next()) {
				return rs.getString(1);
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
		
	}
	
	
	
	
	
}













