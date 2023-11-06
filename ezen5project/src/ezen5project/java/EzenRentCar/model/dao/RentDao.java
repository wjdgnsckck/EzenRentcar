package ezen5project.java.EzenRentCar.model.dao;


import java.util.ArrayList;

import ezen5project.java.EzenRentCar.model.dto.BranchDto;
import ezen5project.java.EzenRentCar.model.dto.CouponDto;
import ezen5project.java.EzenRentCar.model.dto.RentPayDto;
import ezen5project.java.EzenRentCar.model.dto.RentRecordDto;



// 렌트기능 데이터베이스 클래스
public class RentDao extends Dao {
   private static final RentRecordDto RentRecrodDto = null;
   // 싱글톤 만들기
   private static RentDao rentDao = new RentDao();

   public static RentDao getInstance() {
      return rentDao;
   }

   private RentDao() {
   };

   // 회원에게 입력받은 정보를 모두 저장하는 배열
   ArrayList<RentPayDto> list = new ArrayList<RentPayDto>();
   ArrayList<CouponDto> listCoupon = new ArrayList<CouponDto>();
   ArrayList<RentRecordDto>carReturn = new ArrayList<RentRecordDto>();
   ArrayList<BranchDto>branchList=new ArrayList<BranchDto>();
   // 회원의 쿠폰을 모두 출력할수있는 배열
   //private String lstartDay;
   //private String lendDay;

   // 입력값을 렌트로그에 대입하기위한 배열선언

   String region = null; // 입력한 지역에 맞는 지역명 저장하는 전역변수

   public  ArrayList<BranchDto> region() { // 지역 선택 메소드
      try {    branchList.clear();
         String sql = "SELECT * FROM carpoint order by pno asc ;";
         ps = conn.prepareStatement(sql);
         rs = ps.executeQuery();
         while (rs.next()) {
        	int pno = rs.getInt("pno");
            region = rs.getString("pname");
            BranchDto branchDto = new BranchDto(pno,region);
        
            branchList.add(branchDto);
         }
      } catch (Exception e) {

      }
    
      return branchList;
     
   }

   public ArrayList<RentPayDto> rentlog(int ch) {//렌트 출력 코드
      try {
         String sql = "SELECT cp.kname, cp.kprice, kp.bstate, cpnt.pno,kp.bno "
               + "FROM carpricemenu cp NATURAL JOIN carprofile "
               + "kp NATURAL JOIN carpoint cpnt WHERE cpnt.pno =?;"; // 입력받은 ch=지점 (pno)에 state(대여중)이 아닌 값 검색
         ps = conn.prepareStatement(sql);
         ps.setInt(1, ch);
         rs = ps.executeQuery();
         list.clear(); // 뒤로가기 할시 중복값 제거를 위한 list 초기화
         while (rs.next()) {
            String kname = rs.getString("kname");
            int kprice = rs.getInt("kprice");
            int bstate = rs.getInt("bstate");
            int pno = rs.getInt("pno");
            int bno = rs.getInt("bno");
            RentPayDto rentPayDto = new RentPayDto(kname, kprice, bstate, pno,bno);
            
            list.add(rentPayDto);
            
         }

      } catch (Exception e) {
         System.out.println(e);
      }
      return list;
   }

   
   public ArrayList<RentPayDto> carLog(int ch) {// 입력 차량이 있는지 확인하는 메소드
      try {
         String sql = "SELECT kp.bno , cp.kname, cp.kprice, kp.bstate, cpnt.pno "
               + "FROM carpricemenu cp NATURAL JOIN carprofile "
               + "kp NATURAL JOIN carpoint cpnt WHERE cpnt.pname = ? AND kp.bno = ?";
         ps = conn.prepareStatement(sql);
         ps.setString(1, region);
         ps.setInt(2, ch);
         rs = ps.executeQuery();
         while (rs.next()) {
            String kname = rs.getString("kname");
            int kprice = rs.getInt("kprice");
            int bstate = rs.getInt("bstate");
            int pno = rs.getInt("pno");
            int bno = rs.getInt("bno");
            RentPayDto rentPayDto = new RentPayDto(kname, kprice, bstate, pno , bno);
            list.clear(); // 뒤로가기 할시 중복값 제거를 위한 list 초기화
            list.add(rentPayDto);
System.out.println(list);
         }

      } catch (Exception e) {
         System.out.println(e);
      }
      return list;
   }

   public boolean pay() {
      // bno는 인덱스의 번호 +1 해야한다
      return true;
   }

   public ArrayList<CouponDto> checkDiscount(int loginSession) {//쿠폰 출력 코드
      
      try {String sql = "select u.tusedate,d.dname,d.dpercentage,d.dno,u.tno from unusedcoupon u natural join discount d where u.tusedate is null and u.mno = ?";
         ps = conn.prepareStatement(sql);
         ps.setInt(1, loginSession);
         rs = ps.executeQuery();
         listCoupon.clear(); // 뒤로가기 할시 중복값 제거를 위한 list 초기화
         while (rs.next()) { 
        	 	int tno = rs.getInt("tno");
        	 	int dno = rs.getInt("dno");
               String dname = rs.getString("dname");
               int dpercentage = rs.getInt("dpercentage");
               CouponDto couponDto = new CouponDto( dname, dpercentage,tno,dno);
               listCoupon.add(couponDto);
         
         } // if end
         return listCoupon;
         

      } // try end
      catch (Exception e) {
         System.out.println(e);
      }
      return null;
   }// p end

   public int payDiscount(int ch,int loginSession) {   //쿠폰이름을 받고 그 쿠폰이 맞으면 총 금액에서 차감하기
       try {      
            String sql =" select  u.tusedate,d.dname,d.dpercentage,u.tno from unusedcoupon u natural join discount d where u.tusedate is null and u.mno = ?" ;;
            ps=conn.prepareStatement(sql);
            ps.setInt(1, loginSession);
            rs=ps.executeQuery();
            list.clear(); // 뒤로가기 할시 중복값 제거를 위한 list 초기화
            while(rs.next()) {int tno=rs.getInt("tno");
               if(tno==ch)   {int dpercentage =rs.getInt("dpercentage");
                  return dpercentage;
               }
               }
       }
       catch(Exception e){
          System.out.println(e);
       }
       return 1;
      
    }
   
      public boolean inputDayRent( int bno , String startDay , String endDay , int loginSession,int price) {// 마지막 결제시 모든내용을 db에 삽입하는 메소드
      
       try {
             String sql ="insert  into  rentlog( bno , lstartlog,lendlog , mno,lpayment)values(?,?,?,?,?); ";
             ps=conn.prepareStatement(sql);
               ps.setInt(1, bno);
               ps.setString(2, startDay);
               ps.setString(3, endDay);
               ps.setInt(4, loginSession );
               ps.setInt(5, price);
               ps.executeUpdate();
               return true; // 모두 성공한 경우
               
           } catch (Exception e) { System.out.println(e);  } 
        return false;
      }
      public boolean inputCoupon(int ch1) {//쿠폰사용시 쿠폰의 사용날짜를 넣어주는 메소드
         try {String sql = "update  unusedcoupon set tusedate=curdate() where tno=?";
         ps=conn.prepareStatement(sql);
         ps.setInt(1, ch1);
         int rs=ps.executeUpdate();
         if(rs>0) {
            listCoupon.clear();
            return true;
            
         }
         }catch (Exception e) {
            System.out.println(e);
            
         }
         return false;
         
      }
      
   
      public boolean inputBstate(int bno) {
         try {String sql="update carprofile set bstate=0 where bno=?; ";
         ps=conn.prepareStatement(sql);
        ps.setInt(1, bno);
      int   rs=ps.executeUpdate();
         if(rs>0) {
            return true;
         }
            
         }catch (Exception e) {
            System.out.println(e);
            return false;
         }
         return false;
         
      }
      //-------------------------------------차량 반납하기-----------------------------//

   public ArrayList<RentRecordDto> carReturn(int loginSession) {
         try {String sql = "select lno,bno,lstartlog,lendlog from rentlog where mno=? and lcompletelog is null";
            ps=conn.prepareStatement(sql);
            ps.setInt(1, loginSession);
            rs=ps.executeQuery();
            while(rs.next()) {
               int lno=rs.getInt("lno");
               int bno=rs.getInt("bno");
               String lstartlog=rs.getString("lstartlog");
               String lendlog=rs.getString("lendlog");
               RentRecordDto recordDto = new RentRecordDto(lno,bno,lstartlog,lendlog);
               carReturn.clear();
               carReturn.add(recordDto);
            }
            
         } catch (Exception e) {
            System.out.println(e);
         }
         return carReturn;
      }
      
   public ArrayList<RentRecordDto> chooseCar(int ch,int loginSession) {
      try {
         String sql ="select lno,bno,lstartlog,lendlog,lpayment from rentlog where mno=? and lcompletelog is null and lno=?;";
         ps=conn.prepareStatement(sql);
         ps.setInt(1, loginSession);
         ps.setInt(2, ch);
         rs=ps.executeQuery();
         carReturn.clear();
         while (rs.next()) {
            int lno=rs.getInt("lno");
            int bno=rs.getInt("bno");
            String lstartlog=rs.getString("lstartlog");
            String lendlog=rs.getString("lendlog");
            int lpayment=rs.getInt("lpayment");
            RentRecordDto recordDto = new RentRecordDto(lno,bno,lstartlog,lendlog,lpayment);
            
            carReturn.add(recordDto);
         }
         
      }
      
      
      catch (Exception e) {System.out.println(e);}
      return carReturn;
   }
 
   
   public boolean returnComplet(int ch , String day) {// 반납시간을 저장해주는 메소드
      try { String sql="update rentlog r, carprofile c set lcompletelog=?, c.bstate = 1 where lno=? and r.bno = c.bno";
         ps=conn.prepareStatement(sql);
         ps.setString(1, day);
         ps.setInt(2, ch);
         int rs=ps.executeUpdate();
         while(rs>0) {return true;}
      } catch (Exception e) {
         System.out.println(e);
      }
      return false;
   }

 
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
}// class end