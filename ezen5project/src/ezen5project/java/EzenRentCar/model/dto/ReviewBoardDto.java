package ezen5project.java.EzenRentCar.model.dto;

public class ReviewBoardDto {

	//1.필드
	private int uno ;	//게시판 번호
	private int lno ;	//대여번호
	private String utitle ;	//제목
	private String ucontent ;//내용
	
	private String kname;//차 이름 
	
	private String pname;//지역이름 
	private int ustatus;
	private String ustatusName;
	//2.생성자
	
	public ReviewBoardDto() { // 기본생성자
	}

	public ReviewBoardDto(int uno, int lno, String utitle, String ucontent) {
		//풀생성자
		super();
		this.uno = uno;
		this.lno = lno;
		this.utitle = utitle;
		this.ucontent = ucontent;
	}
	
	//글 등록용 생성자
	public ReviewBoardDto(int lno, String utitle, String ucontent) {
		super();
		this.lno = lno;
		this.utitle = utitle;
		this.ucontent = ucontent;
	}
	
	//리뷰 출력용 생성자
	public ReviewBoardDto(int uno, String kname ,String utitle, String ucontent , int ustatus) {
		super();
		this.uno = uno;
		this.kname = kname;
		this.utitle = utitle;
		this.ucontent = ucontent;
		this.ustatus = ustatus;
		
	}
	//관리자 리뷰 출력용
	public ReviewBoardDto(int uno, String kname ,String utitle, String ucontent , int ustatus , String ustatusName) {
		super();
		this.uno = uno;
		this.kname = kname;
		this.utitle = utitle;
		this.ucontent = ucontent;
		this.ustatus = ustatus;
		this.ustatusName = ustatusName;
		
	}
	//나의 게시물 출력용 생성자 
	public ReviewBoardDto(int uno, String pname , String kname , String utitle, String ucontent , int ustatus) {
		super();
		this.uno = uno;
		this.pname = pname;
		this.kname = kname;
		this.utitle = utitle;
		this.ucontent = ucontent;
		this.ustatus = ustatus;
		
	}
	
	//3.메소드
		//getter setter
	
	

	public int getUno() {
		return uno;
	}

	public void setUno(int uno) {
		this.uno = uno;
	}

	public int getLno() {
		return lno;
	}

	public void setLno(int lno) {
		this.lno = lno;
	}

	public String getUtitle() {
		return utitle;
	}

	public void setUtitle(String utitle) {
		this.utitle = utitle;
	}

	public String getUcontent() {
		return ucontent;
	}

	public void setUcontent(String ucontent) {
		this.ucontent = ucontent;
	}

	public String getKname() {
		return kname;
	}

	public void setKname(String kname) {
		this.kname = kname;
	}
	
	
	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}
	
	
	
	public int getUstatus() {
		return ustatus;
	}

	public void setUstatus(int ustatus) {
		this.ustatus = ustatus;
	}

	
	public String getUstatusName() {
		return ustatusName;
	}

	public void setUstatusName(String ustatusName) {
		this.ustatusName = ustatusName;
	}
	
	//toString
	@Override
	public String toString() {
		return "ReviewBoardDto [uno=" + uno + ", lno=" + lno + ", utitle=" + utitle + ", ucontent=" + ucontent
				+ ", kname=" + kname + ", pname=" + pname + ", ustatus=" + ustatus + ", ustatusName=" + ustatusName
				+ "]";
	}

	
	
	

	
	

	
	
	
	
	
	
	
	

	
	
	
}
