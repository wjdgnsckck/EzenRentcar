package ezen5project.java.EzenRentCar.model.dto;

public class CarDto {

	// 1.필드
	private int bno;		// 차량pk
	private int bstate;		// 대여상태
	private int pno;		// 지점명
	private int kno;		// 가격
	private String kname;	// 차량명

	// 2.생성자
	public CarDto() {// 기본생성자
	}
	
	
	public CarDto(int bno, int bstate, int pno, int kno, String kname) {
		// 풀생성자
		super();
		this.bno = bno;
		this.bstate = bstate;
		this.pno = pno;
		this.kno = kno;
		this.kname = kname;
	}
	// SuggestDao 에 checkCarPoint() 메서드에 이용되는 생성자
	public CarDto(int bno, int bstate, int pno, int kno) {
		super();
		this.bno = bno;
		this.bstate = bstate;
		this.pno = pno;
		this.kno = kno;
	}

	// 3.메소드
	// getter setter
	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
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


	@Override
	public String toString() {
		return "CarDto [bno=" + bno + ", bstate=" + bstate + ", pno=" + pno + ", kno=" + kno + ", kname=" + kname + "]";
	}


	
}
