package ezen5project.java.EzenRentCar.model.dto;

// 차량 통계 Dto
public class CarSttDto {
	
	private int bno;
	private String kname;
	private int sum;
	private int rcount;
	
	public CarSttDto() {}

	public CarSttDto(int bno, String kname, int sum, int rcount) {
		this.bno = bno;
		this.kname = kname;
		this.sum = sum;
		this.rcount = rcount;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getKname() {
		return kname;
	}

	public void setKname(String kname) {
		this.kname = kname;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getRcount() {
		return rcount;
	}

	public void setRcount(int rcount) {
		this.rcount = rcount;
	}

	@Override
	public String toString() {
		return "CarSttDto [bno=" + bno + ", kname=" + kname + ", sum=" + sum + ", rcount=" + rcount + "]";
	}
	
	
	

}
