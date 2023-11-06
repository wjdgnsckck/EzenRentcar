package ezen5project.java.EzenRentCar.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import ezen5project.java.EzenRentCar.model.dao.SuggestDao;
import ezen5project.java.EzenRentCar.model.dto.BranchDto;
import ezen5project.java.EzenRentCar.model.dto.CarDto;
import ezen5project.java.EzenRentCar.model.dto.EventCarDto;
import ezen5project.java.EzenRentCar.model.dto.MapDto;
import ezen5project.java.EzenRentCar.model.dto.RentPriceDto;

public class SuggestController {

	private static SuggestController suggestController = new SuggestController();
	public static SuggestController getInstance() {
		return suggestController;
	}
	private SuggestController() {};
	

	
	
	// A-1 이벤트 등록
	public boolean insertEvent( int kno ) {
		
		return SuggestDao.getInstance().insertEvent( kno );
		

	}
	// A-2 이벤트 진행 중인 차량 테이블 출력
	public ArrayList<EventCarDto> viewEventCarList() {
		
		return SuggestDao.getInstance().viewEventCarList();
	}
	
	
	// B-1 이벤트 삭제
	public boolean deleteEvent( int kno ) {
		
		return SuggestDao.getInstance().deleteEvent( kno );
	}
	// B-2 DB carpricemenu, eventcar의 kno 중복 필드 불러옴
	public ArrayList<RentPriceDto> eventCarInfo() {
		
		return SuggestDao.getInstance().eventCarInfo();
	}
	
	
	// D-0 기등록된 국내 서비스 지역 출력
		// 
	public ArrayList<MapDto> viewLocation() {
		
		return SuggestDao.getInstance().viewLocation();
	}
	// D-1 전국내 지역 등록
	public int insertLocation( MapDto mapDto ) {
		
		// 기등록된 지역인지 확인
		int result = SuggestDao.getInstance().checkLocationName( mapDto );
		// 중복검사
			// 1 실패) DB 오류발생
		if( result == 100 ) return 100;
			// 2 실패) 기등록된 지역으로 식별
		if( result == 101 ) return 101;
		
		
		// 중복검사 통과 후 지역 등록
		result = SuggestDao.getInstance().insertLocation( mapDto );
		
		// 실패) DB 오류발생
		if(result == 10000) return 100;
		
		// DB 등록 성공
		return 1000;
	}
	// D-2 전국내 지역 삭제
	public boolean deleteLocation( int jno ) {
		
		return SuggestDao.getInstance().deleteLocation( jno );
	}
	
	
	/*--------------------------------------------------------*/
	
	// 2차원배열에서 상 하 좌 우 를 의미
	private int[] dx = {1, 0, -1, 0};
	private int[] dy = {0, 1, 0, -1};
	
	
	private boolean[][] visited;	// 초기값 false 설정을 위해 인스턴스 변수로 사용
	private int[][] map;			// 초기값 0 설정을 위해 인스턴스 변수로 사용
	
	// E-1 BFS( 너비우선 탐색 실행 ) 탐색 전 인접행렬 생성
		// 사용자가 선택한 지역 근처의 해당 차량을 보유한 지점 탐색
	public ArrayList<BranchDto> carPointBFS( int jno, int kno ) {
		
		visited = new boolean[60][40];	// 방문여부를 확인하기 위해 선언
		map = new int[60][40];			// 탐색하기 위한 인접행렬 생성
		
		// 2차원 인접행렬 불러오기
		ArrayList<MapDto> cityMap = new ArrayList<>();
		cityMap = SuggestController.getInstance().viewLocation();
		
		int startY = -1;
		int startX = -1;
		
		// 인접행렬 생성
	    for(int i=0; i<cityMap.size(); i++) {
	    	
	    	int row = 60-(((int)(cityMap.get(i).getJlatitude()*10000))-330000)/1000;	// 위도
	    	int collum = (((int)(cityMap.get(i).getJlongitude()*10000))-1260000)/1000;	// 경도
	    	
	    	// 탐색을 시작할 Y축, X축 좌표 저장
	    	if( cityMap.get(i).getJno() == jno ) {
	    		startY = row;
	    		startX = collum;

	    	}	
	    	map[row][collum] = cityMap.get(i).getJno();	// 지역pk저장
	    }
	    
		// 탐색 실시
	    return runCarPointBFS( startY, startX, kno );
		
	}
	// E-2 BFS 탐색 실시
	public ArrayList<BranchDto> runCarPointBFS( int startY, int startX, int kno ) {
		
		ArrayList<BranchDto> list = new ArrayList<>();
		// 3개의 지점을 찾아내기 위해 3번 bfs실행
		int count = 3;
		
		Queue<int[]> queue = new LinkedList<>();	
		queue.offer( new int[] { startY, startX } );	// 큐를 사용하여 현재 위치(좌표) 저장
		
		while( !queue.isEmpty() ) {	
		     
			int[] now = queue.poll();

			for(int i=0; i<4; i++) {
				// 현재 위치에서 다음 위치 4방향을 돌아가며 탐색 실시
				int nextY = dy[i] + now[0];
				int nextX = dx[i] + now[1];


				// 2차원 인접행렬 범위를 벗어날 경우 continue
				if( nextX<0 || nextY<0 || nextX>=map[0].length || nextY>=map.length ) continue;
				if( visited[nextY][nextX] ) continue;
				 
				if( map[nextY][nextX]!=0 ) {
					BranchDto result = SuggestDao.getInstance().checkCarPoint( map[nextY][nextX], kno );
					if( result != null ) {
						list.add(result);
						count--;
						if(count == 0) return list;
					}
				}
				visited[nextY][nextX] = true;	// 방문표시
				queue.offer( new int[] { nextY, nextX } );
			}
		}
		// 조건에 부합하지 못하였어도 탐색 종료
		return list;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
