package ezen5project.java.EzenRentCar.model.dto;

public class CouponDto {

	// 1.필드
	private int dno;
	private String dname;
	private int dpercentage;
	private String dnumber;
	private int tno;
	

	// 2.생성자
	public CouponDto() {// 기본생성자
	}
	public CouponDto(int dno, String dname, int dpercentage, String dnumber) { // 풀 생성자
		super();
		this.dno = dno;
		this.dname = dname;
		this.dpercentage = dpercentage;
		this.dnumber = dnumber;
	}
	
	//쿠폰사용자 식별용 생성자
	public CouponDto(String dname, int dpercentage, int tno,int dno) {
		super();
		this.dname = dname;
		this.dpercentage = dpercentage;
		this.tno = tno;
		this.dno = dno;
	}
	// 3.메소드 
	//getter setter
	public int getDno() {
		return dno;
	}

	
	public int getTno() {
		return tno;
	}
	public void setTno(int tno) {
		this.tno = tno;
	}
	public void setDno(int dno) {
		this.dno = dno;
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

	public String getDnumber() {
		return dnumber;
	}

	public void setDnumber(String dnumber) {
		this.dnumber = dnumber;
	}

	// 식별용 to String
	@Override
	public String toString() {
		return "CouponDto [dno=" + dno + ", dname=" + dname + ", dpercentage=" + dpercentage + ", dnumber=" + dnumber
				+ "]";
	};

}// c end
