package ezen5project.java.EzenRentCar.model.dto;

public class MemberDto {
	// 1.필드
	private int mno;
	private String mname;
	private String msno;
	private String mid;
	private String mpw;
	private String mad;
	private String mph;

	// 2.생성자
	public MemberDto() {// 기본
		// TODO Auto-generated constructor stub
	};

	public MemberDto(int mno, String mname, String msno, String mid, String mpw, String mad, String mph) {
		super();
		this.mno = mno;
		this.mname = mname;
		this.msno = msno;
		this.mid = mid;
		this.mpw = mpw;
		this.mad = mad;
		this.mph = mph;
	}
	
	// int mno를 제외한 생성자
	public MemberDto(String mname, String msno, String mid, String mpw, String mad, String mph) {
		super();
		this.mno = mno;
		this.mname = mname;
		this.msno = msno;
		this.mid = mid;
		this.mpw = mpw;
		this.mad = mad;
		this.mph = mph;
	}

	// 3.메소드 
	//getter setter
	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMsno() {
		return msno;
	}

	public void setMsno(String msno) {
		this.msno = msno;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMpw() {
		return mpw;
	}

	public void setMpw(String mpw) {
		this.mpw = mpw;
	}

	public String getMad() {
		return mad;
	}

	public void setMad(String mad) {
		this.mad = mad;
	}

	public String getMph() {
		return mph;
	}

	public void setMph(String mph) {
		this.mph = mph;
	}

	// 식별용 toString
	@Override
	public String toString() {
		return "MemberDto [mno=" + mno + ", mname=" + mname + ", msno=" + msno + ", mid=" + mid + ", mpw=" + mpw
				+ ", mad=" + mad + ", mph=" + mph + "]";
	}

}
