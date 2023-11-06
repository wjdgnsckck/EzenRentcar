package ezen5project.java.EzenRentCar.model.dto;

public class CouponSignDto {
	// 1필드
	private int tno;
	private int mno;
	private int dno;
	private String tupdate;
	private String tusedate;
	private String dname;
	private int dpercentage;

	// 2 생성자
	public CouponSignDto() {// 기본생성자
	}

	public CouponSignDto(int tno, int mno, int dno, String tupdate, String tusedate) {
		// 풀 생성자
		super();
		this.tno = tno;
		this.mno = mno;
		this.dno = dno;
		this.tupdate = tupdate;
		this.tusedate = tusedate;
	}

	public CouponSignDto(int tno, int mno, int dno, String tupdate, String tusedate, String dname, int dpercentage) {
		super();
		this.tno = tno;
		this.mno = mno;
		this.dno = dno;
		this.tupdate = tupdate;
		this.tusedate = tusedate;
		this.dname = dname;
		this.dpercentage = dpercentage;
	}

	// 3.메소드
	// getter setter
	public int getTno() {
		return tno;
	}

	public void setTno(int tno) {
		this.tno = tno;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public int getDno() {
		return dno;
	}

	public void setDno(int dno) {
		this.dno = dno;
	}

	public String getTupdate() {
		return tupdate;
	}

	public void setTupdate(String tupdate) {
		this.tupdate = tupdate;
	}

	public String getTusedate() {
		return tusedate;
	}

	public void setTusedate(String tusedate) {
		this.tusedate = tusedate;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public int getDpercentage() {
		return dpercentage;
	}

	public void setDpercentage(int dpercentage) {
		this.dpercentage = dpercentage;
	}

	// toString
	@Override
	public String toString() {
		return "CouponSignDto [tno=" + tno + ", mno=" + mno + ", dno=" + dno + ", tupdate=" + tupdate + ", tusedate="
				+ tusedate + "]";
	}

}// c end
