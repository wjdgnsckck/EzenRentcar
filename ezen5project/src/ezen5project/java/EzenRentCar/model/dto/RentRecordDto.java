package ezen5project.java.EzenRentCar.model.dto;

public class RentRecordDto {

	// 1.필드
	private int lno;	// 대여기록 번호
	private int bno;	// 차량정보 번호
	private String lstartlog;
	private String lendlog;
	private int lpayment; // 차량의 최종 금액
	private String lcompletelog;
	private int mno;		// 빌린 회원 번호
	private String kname;	// 차량 이름

	// 2.생성자
	public RentRecordDto() {// 기본생성자
	}
	
	

	//렌트시간 입력받는 생성자
	public RentRecordDto(String lstartlog, String lendlog,int bno) {
		super();
		this.lstartlog = lstartlog;
		this.lendlog = lendlog;
		this.bno = bno;
	}



	//렌트반납시 출력해주는 생성자
	public RentRecordDto(int lno, int bno, String lstartlog ,String lendlog) {
		super();
		
		this.lno = lno;
		this.bno = bno;
		this.lstartlog = lstartlog;
		this.lendlog = lendlog;
		
	}
	


	//렌트 반납시 연체료를 찾기위한 생성자
	public RentRecordDto(int lno, int bno, String lstartlog, String lendlog, int lpayment) {
		super();
		this.lno = lno;
		this.bno = bno;
		this.lstartlog = lstartlog;
		this.lendlog = lendlog;
		this.lpayment = lpayment;
	}



	public RentRecordDto(int lno, int bno, String lstartlog, String lendlog, String lcompletelog, int mno) {
		// 풀 생성자
		super();
		this.lno = lno;
		this.bno = bno;
		this.lstartlog = lstartlog;
		this.lendlog = lendlog;
		this.lcompletelog = lcompletelog;
		this.mno = mno;
	}
	
	

	public RentRecordDto(int lno, int bno, String lstartlog, String lendlog, int lpayment, String lcompletelog,
			int mno, String kname) {//풀 생성자
		super();
		this.lno = lno;
		this.bno = bno;
		this.lstartlog = lstartlog;
		this.lendlog = lendlog;
		this.lpayment = lpayment;
		this.lcompletelog = lcompletelog;
		this.mno = mno;
		this.kname = kname;
	}



	public RentRecordDto(int lno, int bno, String lstartlog, String lendlog, String lcompletelog, int mno,
			String kname) {
		this.lno = lno;
		this.bno = bno;
		this.lstartlog = lstartlog;
		this.lendlog = lendlog;
		this.lcompletelog = lcompletelog;
		this.mno = mno;
		this.kname = kname;
	}
	
	
	// BoardDao에서 사용한 후기등록 생성자
	public RentRecordDto(int lno, String lstartlog, String lendlog, String lcompletelog) {
		super();
		this.lno = lno;
		this.lstartlog = lstartlog;
		this.lendlog = lendlog;
		this.lcompletelog = lcompletelog;
	}

	
	// 3.메소드
	// getter setter
	public int getLno() {
		return lno;
	}

	

	public void setLno(int lno) {
		this.lno = lno;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
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

	public int getLpayment() {
		return lpayment;
	}



	public void setLpayment(int lpayment) {
		this.lpayment = lpayment;
	}



	public void setLendlog(String lendlog) {
		this.lendlog = lendlog;
	}

	public String getLcompletelog() {
		return lcompletelog;
	}

	public void setLcompletelog(String lcompletelog) {
		this.lcompletelog = lcompletelog;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}
	
	public String getKname() {
		return kname;
	}

	public void setKname(String kname) {
		this.kname = kname;
	}

	// toString
	@Override
	public String toString() {
		return "RentRecordDto [lno=" + lno + ", bno=" + bno + ", lstartlog=" + lstartlog + ", lendlog=" + lendlog
				+ ", lcompletelog=" + lcompletelog + ", mno=" + mno + "]";
	}

}
