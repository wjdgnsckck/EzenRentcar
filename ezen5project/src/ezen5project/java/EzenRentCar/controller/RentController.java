package ezen5project.java.EzenRentCar.controller;

import java.util.ArrayList;

import ezen5project.java.EzenRentCar.model.dao.RentDao;
import ezen5project.java.EzenRentCar.model.dto.BranchDto;
import ezen5project.java.EzenRentCar.model.dto.CouponDto;
import ezen5project.java.EzenRentCar.model.dto.RentPayDto;
import ezen5project.java.EzenRentCar.model.dto.RentRecordDto;


// 렌트기능  기능 클래스
public class RentController {
   // 싱글톤 만들기
   private static RentController rentController = new RentController();
   public static RentController getInstance() {return rentController;}
   private RentController() {}
   
   public ArrayList<BranchDto> region () { //지역 선택 메소드
	   ArrayList<BranchDto> region=RentDao.getInstance().region();
      return region;
   }
   public ArrayList<RentPayDto> rentlog(int ch) {
      return   RentDao.getInstance().rentlog(ch);
      
   }
   
   public  ArrayList<RentPayDto> carLog(int ch) {
   ArrayList<RentPayDto> result =RentDao.getInstance().carLog(ch);
   return result;
   }
   
   // 결제 전까지의 정보를 임시로 저장.
   private String startDay = null;
   private String endDay = null;
   private int bno = 0;
   private int pay = 0;
   
   public void inputDay(int bno , int pay,String startDay ,String endDay) {//입력받은 날짜 정보 배열에 저장하는 메소드
      this.startDay = startDay;
      this.endDay = endDay;
      this.bno = bno ;
      this.pay = pay;
   }
   
   public boolean pay () {
      RentDao.getInstance().pay();
      return true;
   }
   
    public ArrayList<CouponDto>  checkDiscount(int loginSession) {
       ArrayList<CouponDto> result=RentDao.getInstance().checkDiscount(loginSession);
       return result;
      
    }
    //dno를 받기위한 변수
    private int ch1 = 0;
    public int payDiscount(int ch,int loginSession) {   //쿠폰이름을 받고 그 쿠폰이 맞으면 총 금액에서 차감하기
       int result=RentDao.getInstance().payDiscount(ch,loginSession);
       ch1=ch;
       return result;
      
      }
    public boolean inputDayRent(int loginSession,int price) {// 마지막 결제시 모든내용을 db에 삽입하는 메소드
       boolean result = RentDao.getInstance().inputDayRent( this.bno , this.startDay , this.endDay , loginSession,price);
       if(result) {return true;}
       else return false;
       
       }
    public boolean inputCoupon() { // 마지막 결제시 쿠폰이 있을때 db에 삽입하는 코드
      boolean result=
       RentDao.getInstance().inputCoupon(ch1);
       if(result) {return true;}
       else return false;
      }

    
    public boolean inputBstate() {// 결제시 차량의 상태를 바꿔주는 코드
      System.out.println(bno);
      boolean result=RentDao.getInstance().inputBstate(bno);
       if(result) {return true;}
       else {return false;}
    }
    
    
    
    //----------------------------------------차량 반납하기------------------------//
   public ArrayList<RentRecordDto> carReturn(int loginSession) {
      ArrayList<RentRecordDto> result = RentDao.getInstance().carReturn(loginSession);
      return result;
      }
       
    
   public ArrayList<RentRecordDto> chooseCar(int ch,int loginSession) {
      ArrayList<RentRecordDto> result = RentDao.getInstance().chooseCar(ch,loginSession);
      return result;
      
   }
   
  
   
   public boolean returnComplet(int ch , String day) {// 반납시간을 계산해서 가격을 담아주는 메소드
      boolean result=RentDao.getInstance().returnComplet(ch, day);
      if(result)return true;
      else {return false;}
      
   }
   
 
    
   
}//c end

