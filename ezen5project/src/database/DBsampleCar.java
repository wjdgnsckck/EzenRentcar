package database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

// 차량가격과 차량 정보를 생성하는 DB샘플 클래스
public class DBsampleCar {

	public static void main(String[] args) throws IOException {
		
		ArrayList<String> kname = new ArrayList<>(); 
		ArrayList<Integer> kprice = new ArrayList<>(); 
		

		String[] kname1 = {"아반떼 2022", "아반떼 2021", "카니발 2022", "카니발 2021", "k3 2022", "k3 2021", "k5 2022", "k5 2021", "모닝 2022"    };
		int[] kprice1 = {3000, 2000, 5000, 4500, 3000, 2000, 5000, 4000, 2000};
		
		for(int i=0; i<kname1.length; i++) {
			kname.add(kname1[i]);
			kprice.add(kprice1[i]);

		}
		
		kname.add( "k7 2022" );
		kprice.add( 6000 );
	
		kname.add( "k7 2021" );
		kprice.add( 5500 );
		
		kname.add( "그랜저 2022" );
		kprice.add( 6000 );
		
		kname.add( "그랜저 2021" );
		kprice.add( 5500 );
		
		kname.add( "소나타 2022" );
		kprice.add( 5000 );
		
		kname.add( "소나타 2021" );
		kprice.add( 4000 );
		
		kname.add( "팰리세이드 2022" );
		kprice.add( 6000 );
		
		kname.add( "펠리세이드 2021" );
		kprice.add( 5000 );
		
		kname.add( "코나 2022" );
		kprice.add( 4000 );
		
		kname.add( "코나 2021" );
		kprice.add( 3000 );
		
		kname.add( "캐스퍼 2022" );
		kprice.add( 3500 );
		
		kname.add( "투싼 2022" );
		kprice.add( 4000 );
		
		kname.add( "투싼 2021" );
		kprice.add( 3500 );
		
		kname.add( "GV80 2022" );
		kprice.add( 7500 );
		
		kname.add( "GV80 2021" );
		kprice.add( 6500 );
		
		kname.add( "G90 2022" );
		kprice.add( 8500 );
		
		kname.add( "G90 2021" );
		kprice.add( 7500 );
		
		kname.add( "GV70 2022" );
		kprice.add( 6500 );
		
		kname.add( "GV70 2021" );
		kprice.add( 6000 );
		
		kname.add( "G80 2022" );
		kprice.add( 7500 );
		
		kname.add( "G80 2021" );
		kprice.add( 6500 );
		
		kname.add( "K8 2022" );
		kprice.add( 7000 );
		
		kname.add( "K8 2021" );
		kprice.add( 6500 );
		
		kname.add( "레이 2022" );
		kprice.add( 4000 );
		
		kname.add( "레이 2021" );
		kprice.add( 3000 );
		
		kname.add( "셀토스 2022" );
		kprice.add( 5000 );
		
		kname.add( "셀토스 2021" );
		kprice.add( 4000 );
		
		kname.add( "스포티지 2022" );
		kprice.add( 5000 );
		
		kname.add( "스포티지 2021" );
		kprice.add( 4500 );
		
		kname.add( "쏘렌토 2022" );
		kprice.add( 7500 );
		
		kname.add( "쏘렌토 2021" );
		kprice.add( 7000 );
		
		
		
		
		for(int i=0; i<kname.size(); i++) {
			System.out.print( kname.get(i) );
			System.out.println( "  가격"+ kprice.get(i) );
		}
		
		
		// 파일입출력 사용 가격 샘플 생성
        
		String fileName = "./src/database/carPriceMenu.txt";
		FileOutputStream fileOutputStream = new FileOutputStream(fileName, true);
		
		File file = new File( fileName );
		
		
		// insert location(jname, jlatitude, jlongitude) 
		//	values('서울', 375665, 1269780);

		
		// DB memberlist테이블 insert문 생성하여 파일처리
		for(int i=0; i<kname.size(); i++) {
			
			String str = "insert carpricemenu(kname, kprice) values('"+ kname.get(i) +"', "+ kprice.get(i) +");\n";
			fileOutputStream.write(str.getBytes());
		}
		
		
		
		
		// DB memberlist테이블 insert문 생성하여 파일처리
		
		String[] carName = new String[kname.size()];
		int[] carPrice = new int[kname.size()];
		
		for(int i=0; i<kname.size(); i++) {
			
			carName[i] = kname.get(i);
			carPrice[i] = kprice.get(i);
		}
		

		Random rd = new Random();
		//carName[rd.nextInt(carName.length)];
		//carPrice[rd.nextInt(carPrice.length)];
		
		
		String fileName1 = "./src/database/carProFile.txt";
		FileOutputStream fileOutputStream1 = new FileOutputStream(fileName1, true);
		
		File file1 = new File( fileName1 );
		
		

		// 무작위 차량을 선택하여 저장
		for(int i=0; i<10000; i++) {
			
			String s = carName[rd.nextInt(carName.length)];
			int p = carPrice[(kname.indexOf( s ))];
			
			
			// rd.nextInt(2);		// 0 ~ 1	까지의 무작위 int 값 리턴	// 차량 상태	bstate
			// rd.nextInt(112) + 1; // 1 ~ 112 	까지의 무작위 int 값 리턴	// 지점 		pno
			// rd.nextInt(40) + 1;	// 1 ~ 40	까지의 무작위 int 값 리턴	// 차량가격테이블 kno

			
			String str = "insert carprofile(bstate, pno, kno) values("+ (rd.nextInt(2)) +", "+ (rd.nextInt(112) + 1) +", "+ ((rd.nextInt(40)) + 1) +");\n";

			fileOutputStream1.write(str.getBytes());
		}
		
		
		
		
		
		
		
		
		
	}
	
	
}
