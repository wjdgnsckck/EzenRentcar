package ezen5project.java.EzenRentCar.model.dto;

public class EventCarDto {

	private int eno;	// eventcar테이블 pk
	private int kno;	// 현재 이벤트 중인 차

	// 생성자
	public EventCarDto() {
		
	}

	public EventCarDto(int eno, int kno) {
		super();
		this.eno = eno;
		this.kno = kno;
	}


	// getter setter
	public int getEno() {
		return eno;
	}


	public void setEno(int eno) {
		this.eno = eno;
	}


	public int getKno() {
		return kno;
	}


	public void setKno(int kno) {
		this.kno = kno;
	}
	
	
	

	@Override
	public String toString() {
		return "EventCarDto [eno=" + eno + ", kno=" + kno + "]";
	}
	
	
	
	
	
	
	
	
	
	
}
