package ezen5project.java.EzenRentCar.model.dto;

public class RentPayDto {// 사용자가 선택한 것들을 담아두는 클래스
	// 1필드
	private String kname;
	private int kprice;
	private int bstate;
	private int pno ;
	private int bno ;
	private String lstartlog;
	private String lendlog;

	// 2.생성자
	public RentPayDto() {// 기본생성자

	}

	public RentPayDto(String kname, int kprice, int bstate, int pno) {
		super();
		this.kname = kname;
		this.kprice = kprice;
		this.bstate = bstate;
		this.pno = pno;
	
	}
	//입력받은 날짜를 넣어주는 생성자
		public RentPayDto(String lstartlog, String lendlog) {
			super();
			this.lstartlog = lstartlog;
			this.lendlog = lendlog;
		}

	public RentPayDto(String kname, int kprice, int bstate, int pno, int bno) {
		super();
		this.kname = kname;
		this.kprice = kprice;
		this.bstate = bstate;
		this.pno = pno;
		this.bno = bno;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	//3. 메소드 
	//getter setter
	public String getKname() {
		return kname;
	}

	public String getLstartlog() {
		return lstartlog;
	}

	public void setLstartlog(String lstartlog) {
		this.lstartlog = lstartlog;
	}

	public String getLendlog() {
		return lendlog;
	}

	public void setLendlog(String lendlog) {
		this.lendlog = lendlog;
	}

	public void setKname(String kname) {
		this.kname = kname;
	}

	public int getKprice() {
		return kprice;
	}

	public void setKprice(int kprice) {
		this.kprice = kprice;
	}

	public int getBstate() {
		return bstate;
	}

	public void setBstate(int bstate) {
		this.bstate = bstate;
	}

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	@Override
	public String toString() {
		return "RentPayDto [kname=" + kname + ", kprice=" + kprice + ", bstate=" + bstate + ", pno=" + pno + "]";
	}

	


}
