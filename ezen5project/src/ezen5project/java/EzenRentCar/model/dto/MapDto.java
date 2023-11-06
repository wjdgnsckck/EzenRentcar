package ezen5project.java.EzenRentCar.model.dto;

public class MapDto {
	// 필드
    private int jno;			// 지역번호
    private String jname;		// 도시명
    private double jlatitude;	// 위도
    private double jlongitude;	// 경도
    private int pno;			// 지점번호
    
    
    // 생성자
	public MapDto(int jno, String jname, double jlatitude, double jlongitude, int pno) {
		super();
		this.jno = jno;
		this.jname = jname;
		this.jlatitude = jlatitude;
		this.jlongitude = jlongitude;
		this.pno = pno;
	}
		// SuggestView D-1 에 매개변수로 사용되는 생성자
	public MapDto(String jname, double jlatitude, double jlongitude) {
		this.jname = jname;
		this.jlatitude = jlatitude;
		this.jlongitude = jlongitude;
	}

	
    // getter setter
	public int getJno() {
		return jno;
	}



	public void setJno(int jno) {
		this.jno = jno;
	}



	public String getJname() {
		return jname;
	}



	public void setJname(String jname) {
		this.jname = jname;
	}



	public double getJlatitude() {
		return jlatitude;
	}



	public void setJlatitude(double jlatitude) {
		this.jlatitude = jlatitude;
	}



	public double getJlongitude() {
		return jlongitude;
	}



	public void setJlongitude(double jlongitude) {
		this.jlongitude = jlongitude;
	}



	public int getPno() {
		return pno;
	}



	public void setPno(int pno) {
		this.pno = pno;
	}



	@Override
	public String toString() {
		return "CityDto [jno=" + jno + ", jname=" + jname + ", jlatitude=" + jlatitude + ", jlongitude="
				+ jlongitude + ", pno=" + pno + "]";
	}



	
    
    
	
	
    
    
    
}


