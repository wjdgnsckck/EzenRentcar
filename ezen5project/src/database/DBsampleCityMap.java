package database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

// location 과 지점 DB샘플
public class DBsampleCityMap {

	private static String[][] CityDtoMap;
	
	public static void main(String[] args) throws IOException {
		
		ArrayList<sampleCityDto> cityMap = new ArrayList<>();

		 
		cityMap.add(new sampleCityDto("서울", 37.5665, 126.9780));
        cityMap.add(new sampleCityDto("부산", 35.1796, 129.0756));
        cityMap.add(new sampleCityDto("인천", 37.4563, 126.7052));
        cityMap.add(new sampleCityDto("대구", 35.8714, 128.6014));
        cityMap.add(new sampleCityDto("대전", 36.3504, 127.3845));
        cityMap.add(new sampleCityDto("광주", 35.1595, 126.8526));
        cityMap.add(new sampleCityDto("울산", 35.5384, 129.3114));
        cityMap.add(new sampleCityDto("성남", 37.4185, 127.5183));
        cityMap.add(new sampleCityDto("수원", 37.2636, 127.0286));
        cityMap.add(new sampleCityDto("창원", 35.2272, 128.6811));
        cityMap.add(new sampleCityDto("고양", 37.6586, 126.8327));
        cityMap.add(new sampleCityDto("용인", 37.2411, 127.1775));
        cityMap.add(new sampleCityDto("안산", 37.3188, 126.8369));
        cityMap.add(new sampleCityDto("평택", 36.9949, 127.0879));
        cityMap.add(new sampleCityDto("부천", 37.5035, 126.7664));
        cityMap.add(new sampleCityDto("은평", 37.6024, 126.9292));
        cityMap.add(new sampleCityDto("남양주", 37.6363, 127.2165));
        cityMap.add(new sampleCityDto("화성", 37.1995, 126.8312));
        cityMap.add(new sampleCityDto("안양", 37.3945, 126.9578));
        cityMap.add(new sampleCityDto("장안", 37.6520, 127.0730));
        cityMap.add(new sampleCityDto("광명", 37.4781, 126.8663));
        cityMap.add(new sampleCityDto("김포", 37.6153, 126.7158));
        cityMap.add(new sampleCityDto("진주", 35.1796, 128.0780));
        cityMap.add(new sampleCityDto("진해", 35.1495, 128.6592));
        cityMap.add(new sampleCityDto("안동", 36.5662, 128.7292));
        cityMap.add(new sampleCityDto("구미", 36.1195, 128.3446));
        cityMap.add(new sampleCityDto("거제", 34.8807, 128.6144));
        cityMap.add(new sampleCityDto("양산", 35.3381, 129.0264));
        cityMap.add(new sampleCityDto("군산", 35.9743, 126.7321));
        cityMap.add(new sampleCityDto("여수", 34.7604, 127.6622));
        cityMap.add(new sampleCityDto("경주", 35.8562, 129.2242));
        cityMap.add(new sampleCityDto("익산", 35.9511, 126.9574));
        cityMap.add(new sampleCityDto("정읍", 35.5694, 126.8506));
        cityMap.add(new sampleCityDto("함양", 35.5209, 127.7257));
        cityMap.add(new sampleCityDto("김천", 36.1394, 128.1123));
        cityMap.add(new sampleCityDto("목포", 34.8121, 126.3957));
        cityMap.add(new sampleCityDto("포항", 36.0187, 129.3435));
        cityMap.add(new sampleCityDto("창녕", 35.5449, 128.5006));
        cityMap.add(new sampleCityDto("강릉", 37.7519, 128.8760));
        cityMap.add(new sampleCityDto("김해", 35.2272, 128.8810));
        cityMap.add(new sampleCityDto("곡성", 35.2762, 127.2887));
        cityMap.add(new sampleCityDto("삼척", 37.4455, 129.1680));
        cityMap.add(new sampleCityDto("통영", 34.8588, 128.4259));
        cityMap.add(new sampleCityDto("사천", 35.0030, 128.2893));
        cityMap.add(new sampleCityDto("경산", 35.8235, 128.7376));
        cityMap.add(new sampleCityDto("영주", 36.8056, 128.6235));
        cityMap.add(new sampleCityDto("상주", 36.4163, 128.1532));
        cityMap.add(new sampleCityDto("전주", 35.8242, 127.1480));
        cityMap.add(new sampleCityDto("완주", 35.9162, 127.1168));
        cityMap.add(new sampleCityDto("부안", 35.7336, 126.7217));
        cityMap.add(new sampleCityDto("나주", 35.0159, 126.7106));
        cityMap.add(new sampleCityDto("고흥", 34.6111, 127.2755));
        cityMap.add(new sampleCityDto("순천", 34.9506, 127.4875));
        cityMap.add(new sampleCityDto("담양", 35.3188, 126.9893));
        cityMap.add(new sampleCityDto("해남", 34.5730, 126.5987));
        cityMap.add(new sampleCityDto("무안", 34.9932, 126.4849));
        cityMap.add(new sampleCityDto("영암", 34.8048, 126.6902));
        cityMap.add(new sampleCityDto("구례", 35.1989, 127.4822));
        cityMap.add(new sampleCityDto("순창", 35.3740, 127.1416));
        cityMap.add(new sampleCityDto("화순", 35.0541, 127.0283));
        cityMap.add(new sampleCityDto("장성", 35.2992, 126.7851));
        cityMap.add(new sampleCityDto("강진", 34.6328, 126.7708));
        cityMap.add(new sampleCityDto("장흥", 34.6874, 126.9196));
        cityMap.add(new sampleCityDto("완도", 34.3119, 126.7383));
        cityMap.add(new sampleCityDto("진도", 34.4797, 126.2700));
        cityMap.add(new sampleCityDto("거창", 35.6866, 127.9117));
        cityMap.add(new sampleCityDto("합천", 35.5668, 128.1665));
        cityMap.add(new sampleCityDto("속초", 38.2503, 128.5647));
        cityMap.add(new sampleCityDto("양양", 38.0584, 128.6192));
        cityMap.add(new sampleCityDto("세종", 36.4801, 127.2892));
        cityMap.add(new sampleCityDto("서산", 36.7841, 126.4503));
        cityMap.add(new sampleCityDto("충주", 36.9705, 127.9308));
        cityMap.add(new sampleCityDto("청주", 36.6355, 127.4892));
        cityMap.add(new sampleCityDto("여주", 37.2958, 127.6375));
        cityMap.add(new sampleCityDto("이천", 37.2794, 127.4422));
        cityMap.add(new sampleCityDto("안성", 37.0071, 127.2817));
        cityMap.add(new sampleCityDto("제천", 37.1332, 128.2127));
        cityMap.add(new sampleCityDto("원주", 37.3496, 127.9205));
        cityMap.add(new sampleCityDto("태백", 37.1645, 128.9866));
        cityMap.add(new sampleCityDto("평창", 37.3705, 128.3927));
        cityMap.add(new sampleCityDto("정선", 37.3797, 128.6809));
        cityMap.add(new sampleCityDto("춘천", 37.8821, 127.7356));
        cityMap.add(new sampleCityDto("홍천", 37.6917, 127.8983));
        cityMap.add(new sampleCityDto("인제", 38.0654, 128.1694));
        cityMap.add(new sampleCityDto("양구", 38.1059, 127.9897));
        cityMap.add(new sampleCityDto("화천", 38.1052, 127.7125));
        cityMap.add(new sampleCityDto("파주", 37.7598, 126.7883));
        cityMap.add(new sampleCityDto("동두천", 37.9031, 127.0609));
        cityMap.add(new sampleCityDto("포천", 37.8964, 127.2003));
        cityMap.add(new sampleCityDto("철원", 38.1465, 127.3048));
        cityMap.add(new sampleCityDto("울진", 36.9912, 129.4126));
        cityMap.add(new sampleCityDto("영덕", 36.5333, 129.4113));
        cityMap.add(new sampleCityDto("광양", 34.9407, 127.6962));
        cityMap.add(new sampleCityDto("제주", 33.4996, 126.5312));
        cityMap.add(new sampleCityDto("당진", 36.9057, 126.6769));
        cityMap.add(new sampleCityDto("태안", 36.6238, 126.2997));
        cityMap.add(new sampleCityDto("청양", 36.4582, 126.7925));
        cityMap.add(new sampleCityDto("홍성", 36.6017, 126.6725));
        cityMap.add(new sampleCityDto("보령", 36.3491, 126.5783));
        cityMap.add(new sampleCityDto("공주", 36.4558, 127.1212));
        cityMap.add(new sampleCityDto("부여", 36.2729, 126.9126));
        cityMap.add(new sampleCityDto("아산", 36.7917, 127.0023));
        cityMap.add(new sampleCityDto("천안", 36.8151, 127.1134));
        cityMap.add(new sampleCityDto("문경", 36.6415, 128.1935));
        cityMap.add(new sampleCityDto("단양", 36.9832, 128.3696));
        cityMap.add(new sampleCityDto("봉화", 36.8896, 128.7412));
        cityMap.add(new sampleCityDto("영양", 36.6684, 129.1151));
        cityMap.add(new sampleCityDto("의성", 36.3554, 128.6994));
        cityMap.add(new sampleCityDto("영천", 35.9771, 128.9469));
        cityMap.add(new sampleCityDto("군위", 36.2396, 128.5729));
        cityMap.add(new sampleCityDto("강화", 37.7451, 126.5006));
        cityMap.add(new sampleCityDto("고성", 34.9765, 128.3230));
		
		// row(행)		: 위도 latitude
		// collum(열)	: 경도 longitude
		CityDtoMap = new String[60][40];
	
		/*
			경위도 데이터를 불러와서 저장할 시 초기값
			 	위도 -340000
			 	경도 -1260000
			
			인접행렬 최대 범위
			인접행렬 row 최대범위 = (위도 최대값 - 위도 인접행렬 초기(최소)값)
			인접행렬 collum 최대범위 = (경도 최대값 - 경도 인접행렬 초기(최소)값)
			 	위도 380000 - 340000 		-> 산출값에서 0자리 버림
			 		=> 4000
			 	경도 1300000 - 1260000	-> 산출값에서 0자리 버림
			 		=> 4000
			 		- 위도 380000을 최대로 한 기준은 국내 위도의 공통값이기 때문
			 		- 위도 1300000을 최대로 한 기준은 국내 경도의 공통값이기 때문
	 	*/
		
		
		
		// 2차원 배열 기본값 "  "로 초기화
		for(int i=0; i<CityDtoMap.length; i++) {
			for(int j=0; j<CityDtoMap[0].length; j++) {
				
				CityDtoMap[i][j] = "  ";
				
			}
		}
		
		
		// 각 지역 지도 구현
			// 각 지역의 위도, 경도를 확인하여 2차원 배열에 대입
        for(int i=0; i<cityMap.size(); i++) {
        	
        	int row = 60-(((int)(cityMap.get(i).getJlatitude()*10000))-330000)/1000;
        	int collum = (((int)(cityMap.get(i).getJlongitude()*10000))-1260000)/1000;
        	

        	CityDtoMap[row][collum] = cityMap.get(i).getJloname()+" ";
        	
        }
        



        

        // 지도 출력 test
        	// ※ 통상 평면으로 보는 지도와 경위도 기준으로 보는 지도가 다름 
        	// 지구는 구 형태이기에 한국 기준으로 북쪽으로 갈수록 점점 간격이 좁아지며, 해당 경위도 방식이 더 정확함
        
        for(int i=0; i<CityDtoMap.length; i++) {
			for(int j=0; j<CityDtoMap[0].length; j++) {
				
				System.out.print(CityDtoMap[i][j]);
				
			}
			System.out.println("");
		}
        


		// 파일입출력 사용 지점 샘플 생성
        
		String fileName1 = "./src/database/carPoint.txt";
		FileOutputStream fileOutputStream1 = new FileOutputStream(fileName1, true);
		
		File file1 = new File( fileName1 );
		
		
		// insert location(jname, jlatitude, jlongitude) 
		//	values('서울', 375665, 1269780);

		
		// DB memberlist테이블 insert문 생성하여 파일처리
		for(int i=0; i<cityMap.size(); i++) {
			
			String str = "insert carpoint(pname) values('"+ cityMap.get(i).getJloname() +"');\n";
			fileOutputStream1.write(str.getBytes());
		}
      
        
        
        
		// 파일입출력 사용 지역 샘플 생성
  
			
		
		String fileName = "./src/database/locationList.txt";
		FileOutputStream fileOutputStream = new FileOutputStream(fileName, true);
		
		File file = new File( fileName );
		
		
		// insert location(jname, jlatitude, jlongitude) 
		//	values('서울', 375665, 1269780);

		
		// DB memberlist테이블 insert문 생성하여 파일처리
		for(int i=0; i<cityMap.size(); i++) {
			
			String str = "insert location(jname, jlatitude, jlongitude) values('"+ cityMap.get(i).getJloname() +"', "+ cityMap.get(i).getJlatitude() +", "+ cityMap.get(i).getJlongitude() +");\n";
			fileOutputStream.write(str.getBytes());
		}

        
		
		
		
        
	}

}

class sampleCityDto {
	// 필드
    private String jloname;		// 도시명
    private double jlatitude;	// 위도
    private double jlongitude;	// 경도
    
    
    // 생성자
	public sampleCityDto(String jloname, double jlatitude, double jlongitude) {

		this.jloname = jloname;
		this.jlatitude = jlatitude;
		this.jlongitude = jlongitude;

	}

	
    // getter setter
	public String getJloname() {
		return jloname;
	}



	public void setJloname(String jloname) {
		this.jloname = jloname;
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


	@Override
	public String toString() {
		return "samplesampleCityDto [jloname=" + jloname + ", jlatitude=" + jlatitude + ", jlongitude=" + jlongitude + "]";
	}


}