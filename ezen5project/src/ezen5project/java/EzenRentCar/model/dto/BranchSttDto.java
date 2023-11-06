package ezen5project.java.EzenRentCar.model.dto;

// 지점 통계 Dto
public class BranchSttDto {
	private int pno;
	private String pname;
	private int sum;
	private int pcount;
	
	public BranchSttDto() {}

	public BranchSttDto(int pno, String pname, int sum, int pcount) {
		this.pno = pno;
		this.pname = pname;
		this.sum = sum;
		this.pcount = pcount;
	}

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getPcount() {
		return pcount;
	}

	public void setPcount(int pcount) {
		this.pcount = pcount;
	}

	@Override
	public String toString() {
		return "BranchSttDto [pno=" + pno + ", pname=" + pname + ", sum=" + sum + ", pcount=" + pcount + "]";
	}
	
	
	
	
}
