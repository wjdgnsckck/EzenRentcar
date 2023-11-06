package ezen5project.java.EzenRentCar.model.dto;

// carpoint Dto
public class BranchDto {

	//1.필드
	private int pno;
	private String pname;
	//2.생성자
	public BranchDto() {//기본생성자
		}
	
	public BranchDto(int pno, String pname) {//풀생성자
		super();
		this.pno = pno;
		this.pname = pname;
	}

	//3.메소드
	//getter setter
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
	
	//검사용 toString

	@Override
	public String toString() {
		return "BranchDto [pno=" + pno + ", pname=" + pname + "]";
	};
	
	
	
	
}
