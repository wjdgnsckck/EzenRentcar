package ezen5project.java.EzenRentCar.model.dto;

// 회원 통계 Dto
public class MemberSttDto {
	
	private int mno;
	private String mid;
	private String mad;
	private int mage;
	private int rcount;
	private int msum;
	
	public MemberSttDto() {}

	public MemberSttDto(int mno, String mid, String mad, int mage, int rcount, int msum) {
		this.mno = mno;
		this.mid = mid;
		this.mad = mad;
		this.mage = mage;
		this.rcount = rcount;
		this.msum = msum;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMad() {
		return mad;
	}

	public void setMad(String mad) {
		this.mad = mad;
	}

	public int getRcount() {
		return rcount;
	}

	public void setRcount(int rcount) {
		this.rcount = rcount;
	}

	public int getMage() {
		return mage;
	}

	public void setMage(int mage) {
		this.mage = mage;
	}
	

	public int getMsum() {
		return msum;
	}

	public void setMsum(int msum) {
		this.msum = msum;
	}

	@Override
	public String toString() {
		return "MemberSttDto [mno=" + mno + ", mid=" + mid + ", mad=" + mad + ", rcount=" + rcount + "]";
	}
	
	
	
	

}
