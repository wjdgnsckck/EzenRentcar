package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class DBsampleMemberList {
	public static void main(String[] args) throws IOException{
		Random rd = new Random();
		
		
	// 1. 회원이름
		String[] membername = new String[100000];
		
		String[] name1 = {"김", "이", "박", "최", "강", "차", "정", "추"};	// 성
		String[] name2 = {"아", "정", "환", "진", "수", "두", "연", "숙"};	// 가운데 character
		String[] name3 = {"주", "중", "희", "태", "민", "오", "혜", "영"};	// 오른쪽 character
		
		for(int i=0; i<100000; i++) {
			membername[i] = name1[rd.nextInt(name1.length)] + name2[rd.nextInt(name2.length)] + name3[rd.nextInt(name3.length)];
		}
		
		
	// 2. 회원주민번호
		// 주민 앞 번호 앞 두 자리
			// 60년생 ~ 04년생 까지
		String[] sno1 = new String[45];
		
		int w = 0;
		for(int i=1960; i<=2004; i++) {

			sno1[w] = ( String.valueOf(i).substring(2) );
			w++;
			
		}
		// 주민번호 앞 번호 가운데 두 자리	
		String[] sno2 = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
		// 주민번호 앞 번호 뒤 두 자리
		String[] sno3 = new String[30];		
		for(int i=1; i<=sno3.length; i++) {
			
			if(i<10) {
				sno3[i-1] = "0" + String.valueOf(i);
			} else sno3[i-1] = String.valueOf(i);
			
		}
		// 주민번호 뒷 자리
		String[] sno4 = new String[100000];	
		for(int j=0; j<sno4.length; j++) {
			
			if( j<10 ) sno4[j] = "000000"+j;
			
			else if( j<100 ) sno4[j] = "00000"+j;
			
			else if( j<1000 ) sno4[j] = "0000"+j;
			
			else if( j<10000 ) sno4[j] = "000"+j;
			
			else if( j<100000 ) sno4[j] = "00"+j;
			
			else sno4[j] = String.valueOf(j);

		}
		// 주민번호 문자열 결합
		String[] sno = new String[100000];
		for( int i=0; i<sno.length; i++ ) {
			
			sno[i] = sno1[rd.nextInt(sno1.length)] + sno2[rd.nextInt(sno2.length)] + sno3[rd.nextInt(sno3.length)] + "-" +sno4[i]; 

		}

		
		// 아이디
		String[] id = new String[100000];	
		
		for(int j=0; j<id.length; j++) {
			
			if( j<10 ) id[j] = "te000000"+j;
			
			else if( j<100 ) id[j] = "te00000"+j;
			
			else if( j<1000 ) id[j] = "te0000"+j;
			
			else if( j<10000 ) id[j] = "te000"+j;
			
			else if( j<100000 ) id[j] = "te00"+j;
			
			else if( j<1000000 ) id[j] = "te0"+j;
			
			else id[j] = "te"+ String.valueOf(j);

		}
		
		
		// 패스워드
		String[] pw = new String[100000];	
		
		for(int j=0; j<pw.length; j++) {
			
			if( j<10 ) pw[j] = "te015230"+j;
			
			else if( j<100 ) pw[j] = "te07520"+j;
			
			else if( j<1000 ) pw[j] = "te0780"+j;
			
			else if( j<10000 ) pw[j] = "te350"+j;
			
			else if( j<100000 ) pw[j] = "te72"+j;
			
			else if( j<1000000 ) pw[j] = "te6"+j;
			
			else pw[j] = "te"+ String.valueOf(j);
		}
		
		
		 // 도시이름
		String[] cities = {	
	            "서울", "부산", "인천", "대구", "대전",
	            "광주", "울산", "성남", "고양", "용인",
	            "부천", "안산", "화성", "창원", "수원",
	            "의정부", "안양", "김해", "시흥", "파주",
	            "광명", "군포", "안동", "양산", "진주",
	            "제주", "경기", "포항", "경주", "익산",
	            "전주", "여수", "순천", "나주", "경산",
	            "구미", "정읍", "남양주", "홍성", "의왕",
	            "서산", "동해", "원주", "춘천", "논산",
	            "김천", "통영", "안산", "김제", "아산"
		};


		// 휴대폰 번호 가운데 4자리
		String[] ph1 = new String[100000];		

		for(int j=0; j<ph1.length; j++) {
			
			if( j<10 ) ph1[j] = "010-0000-000"+j;
			
			else if( j<100 ) ph1[j] = "010-0000-00"+j;
			
			else if( j<1000 ) ph1[j] = "010-0000-0"+j;
			
			else if( j<10000 ) ph1[j] = "010-0000-"+j; 
			
			else if( j<100000 ) {
	
				ph1[j] = "010-000"+String.valueOf(j).substring(0, 1) + "-";
				ph1[j] += String.valueOf(j).substring(1);
				
			}
		}
		
		
		
		// 파일입출력 사용
		String fileName = "./src/database/memberList.txt";
		FileOutputStream fileOutputStream = new FileOutputStream(fileName, true);
		
		File file = new File( fileName );
		
		
	// DB memberlist테이블 insert문 생성하여 파일처리
		String[] memberList = new String[100000];
		for(int i=0; i<memberList.length; i++) {
			
			String str = "insert memberlist(mname, msno, mid, mpw,  mad, mph) values('"+membername[rd.nextInt(membername.length)]+"', '"+ sno[i] +"', '"+ id[i] +"', '"+ pw[i] +"', '"+ cities[rd.nextInt(cities.length)] +"', '"+ ph1[i] +"');";
			fileOutputStream.write(str.getBytes());
		}

		
		
		
		
		
	}
	
}
