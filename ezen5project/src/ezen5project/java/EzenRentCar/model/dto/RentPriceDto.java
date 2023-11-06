package ezen5project.java.EzenRentCar.model.dto;

public class RentPriceDto {
	// 1.필드
	int kno;
	String kname;
	int kprice;

	// 2. 생성자
	public RentPriceDto() {// 기본생성자
	}
	
	
	public RentPriceDto(int kno, String kname, int kprice) {
		// 풀 생성자
		super();
		this.kno = kno;
		this.kname = kname;
		this.kprice = kprice;
	}

	// 3.메소드 
	// getter setter
	public int getKno() {
		return kno;
	}

	public void setKno(int kno) {
		this.kno = kno;
	}

	public String getKname() {
		return kname;
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

	// toString
	@Override
	public String toString() {
		return "RentPriceDto [kno=" + kno + ", kname=" + kname + ", kprice=" + kprice + "]";
	}

}
