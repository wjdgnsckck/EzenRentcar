drop database if exists ezenrentcar;
create database ezenrentcar;
use ezenrentcar;

drop table carpoint;
CREATE TABLE carpoint (
  pno int NOT NULL AUTO_INCREMENT,
  pname varchar(20) NOT NULL UNIQUE,
  PRIMARY KEY (pno)
);

drop table carpricemenu;
CREATE TABLE carpricemenu (
  kno int NOT NULL AUTO_INCREMENT,
  kname varchar(30) NOT NULL unique,
  kprice int NOT NULL,
  PRIMARY KEY (kno)
);

drop table carprofile;
CREATE TABLE carprofile (
  bno int NOT NULL AUTO_INCREMENT,
  bstate tinyint(1) NOT NULL,
  pno int NOT NULL,
  kno int NOT NULL,
  PRIMARY KEY (bno),
  FOREIGN KEY (kno) REFERENCES carpricemenu (kno),
  FOREIGN KEY (pno) REFERENCES carpoint (pno)
);

drop table discount;
CREATE TABLE discount (
  dno int NOT NULL AUTO_INCREMENT,
  dname varchar(20) NOT NULL UNIQUE,
  dpercentage int NOT NULL,
  dnumber varchar(20) NOT NULL UNIQUE,
  PRIMARY KEY (dno)
) ;

drop table if exists memberlist;
CREATE TABLE memberlist (
  mno int NOT NULL AUTO_INCREMENT,
  mname varchar(10) NOT NULL,
  msno varchar(15) NOT NULL   UNIQUE ,
  mid varchar(10) NOT NULL  UNIQUE ,
  mpw varchar(15) NOT NULL,
  mad varchar(50) NOT NULL,
  mph varchar(15) NOT NULL   UNIQUE,
  PRIMARY KEY (mno)
  );

drop table if exists rentlog;
CREATE TABLE rentlog (
	lno int NOT NULL AUTO_INCREMENT,
	bno int NOT NULL,
	lpayment int ,
	lstartlog varchar(40) not null,
	lendlog varchar(40),
	lcompletelog varchar(40),
	mno int,
	PRIMARY KEY (lno),
	FOREIGN KEY (bno) REFERENCES carprofile (bno),
	FOREIGN KEY (mno) REFERENCES memberlist (mno)
) ;

drop table if exists unusedcoupon;
CREATE TABLE unusedcoupon (
	tno int AUTO_INCREMENT,
	mno int NOT NULL,
	dno int,
    tupdate varchar(40) not null,
    tusedate varchar(40),
	PRIMARY KEY (tno),
	FOREIGN KEY (mno) REFERENCES memberlist (mno),
	FOREIGN KEY (dno) REFERENCES discount (dno)
) ;

drop table if exists content;
CREATE TABLE content (
	uno int AUTO_INCREMENT,
	lno int,
    utitle varchar(100),
    ucontent varchar(300),
    ustatus int default 1,
	PRIMARY KEY (uno),
	FOREIGN KEY (lno) REFERENCES rentlog(lno)
) ;

drop table if exists location;
create table location(
	jno int AUTO_INCREMENT,
	jname varchar(20) unique,	-- 지역명
	jlatitude FLOAT,			-- 자바 (2차원배열상 row)
	jlongitude FLOAT,			-- 경도 (자바 2차원배열상 collum)
	pno int,					-- 지점번호
	PRIMARY KEY (jno),
	FOREIGN KEY (pno) REFERENCES carpoint(pno)
);


drop table if exists eventcar;
create table eventcar(
	eno int AUTO_INCREMENT,
	kno int unique,
    PRIMARY KEY (eno),
	FOREIGN KEY (kno) REFERENCES carpricemenu(kno)
);	





# ------------ 샘플데이터 -------------------
# memberlist
insert memberlist(mno, mname, msno, mid, mpw,  mad, mph) 
values('유재석', '940326-1036221', 'ddkre', '12123', '안산', '010-6633-5699');
insert memberlist(mname, msno, mid, mpw,  mad, mph) 
values('강호동', '881126-2653585', 'kkkno', '123123', '서울', '010-5522-7788');
insert memberlist(mname, msno, mid, mpw,  mad, mph) 
values('도민준', '910511-2426587', 'khan', '123123', '서울', '010-2569-6767');
insert memberlist(mname, msno, mid, mpw,  mad, mph) 
values('이시우', '861201-2126359', 'oracle', '123123', '수원', '010-8888-1212');
insert memberlist(mname, msno, mid, mpw,  mad, mph) 
values('이하윤', '960911-1052477', 'rinch', '123123', '부산', '010-4566-6622');
insert memberlist(mname, msno, mid, mpw,  mad, mph) 
values('김은서', '910526-1072668', 'apple', '123123', '제주도', '010-8599-5252');
insert memberlist(mname, msno, mid, mpw,  mad, mph) 
values('성우진', '821109-2266547', 'banana', '123123', '서울', '010-6666-7777');
insert memberlist(mname, msno, mid, mpw,  mad, mph) 
values('최다은', '891022-1545478', 'dogma', '123123', '수원', '010-7788-8000');
insert memberlist(mname, msno, mid, mpw,  mad, mph) 
values('김준혁', '990221-2526684', 'prote', '123123', '광주', '010-9966-7788');
insert memberlist(mname, msno, mid, mpw,  mad, mph) 
values('이지안', '920817-1061800', 'nicket', '123123', '서울', '010-7711-1234');

# carpoint
insert carpoint(pname) values('서울');
insert carpoint(pname) values('수원');
insert carpoint(pname) values('부산');
insert carpoint(pname) values('제주도');
insert carpoint(pname) values('광주');
insert carpoint(pname) values('경주');

# unusedcoupon
insert unusedcoupon(mno, dno, tupdate, tusedate)
values(7, 3, '2020-08-22', '2020-12-11');
insert unusedcoupon(mno, dno, tupdate, tusedate)
values(3, 4, '2020-09-30', '2020-11-10');
insert unusedcoupon(mno, dno, tupdate, tusedate)
values(1, 3, '2021-05-11', '2021-05-22');
insert unusedcoupon(mno, dno, tupdate, tusedate)
values(3, 1, '2021-07-02', '2021-10-17');
insert unusedcoupon(mno, dno, tupdate, tusedate)
values(9, 2, '2021-08-12', '2021-12-11');
insert unusedcoupon(mno, dno, tupdate, tusedate)
values(10, 2, '2022-01-24', '2022-03-22');
insert unusedcoupon(mno, dno, tupdate, tusedate)
values(4, 3, '2022-12-07', '2023-02-07');
insert unusedcoupon(mno, dno, tupdate, tusedate)
values(7, 5, '2023-01-09', '2023-01-15');

# discount
insert discount(dname, dpercentage, dnumber)
values('웰컴쿠폰', 30, 'HELLO1122');
insert discount(dname, dpercentage, dnumber)
values('핫썸머쿠폰', 20, 'HOTSUMMER');
insert discount(dname, dpercentage, dnumber)
values('떠나요우리쿠폰', 25, 'LETGO5555');
insert discount(dname, dpercentage, dnumber)
values('VIP쿠폰', 30, 'AAAA0124');
insert discount(dname, dpercentage, dnumber)
values('모범운전사쿠폰', 15, 'BBBB8888');

#차량별 단가표 테이블 사전 데이터(carpricemenu)
insert into carpricemenu ( kname , kprice ) values ( '아반떼 2023', 3000 );
insert into carpricemenu ( kname , kprice ) values ( '아반떼 2021', 2000 );
insert into carpricemenu ( kname , kprice ) values ( '카니발 2022', 5000 );
insert into carpricemenu ( kname , kprice ) values ( '카니발 2021', 4500 );
insert into carpricemenu ( kname , kprice ) values ( 'k3 2023', 3000 );
insert into carpricemenu ( kname , kprice ) values ( 'k3 2020', 2000 );
insert into carpricemenu ( kname , kprice ) values ( 'k9 2022', 5000 );
insert into carpricemenu ( kname , kprice ) values ( 'k5 2021', 3500 );
insert into carpricemenu ( kname , kprice ) values ( '포르쉐 911', 10000 );
insert into carpricemenu ( kname , kprice ) values ( '모닝 2023', 2000 );


# 차량정보 테이블 (bstate 1 : 사용가능 / 0 : 대여중)(carprofile)
insert into carprofile ( bstate , pno , kno  ) values( 1 , 1 , 1 );
insert into carprofile ( bstate , pno , kno  ) values( 0 , 1 , 2 );
insert into carprofile ( bstate , pno , kno  ) values( 0 , 1 , 2 );
insert into carprofile ( bstate , pno , kno  ) values( 1 , 2 , 3 );
insert into carprofile ( bstate , pno , kno  ) values( 1 , 2 , 4 );
insert into carprofile ( bstate , pno , kno  ) values( 0 , 3 , 5 );
insert into carprofile ( bstate , pno , kno  ) values( 0 , 3 , 5 );
insert into carprofile ( bstate , pno , kno  ) values( 1 , 3 , 6 );
insert into carprofile ( bstate , pno , kno  ) values( 1 , 4 , 7 );
insert into carprofile ( bstate , pno , kno  ) values( 1 , 4 , 7 );
insert into carprofile ( bstate , pno , kno  ) values( 0 , 5 , 8 );
insert into carprofile ( bstate , pno , kno  ) values( 1 , 5 , 8 );
insert into carprofile ( bstate , pno , kno  ) values( 1 , 5 , 9 );
insert into carprofile ( bstate , pno , kno  ) values( 0 , 5 , 10 );


#대여내역 테이블(rentlog)
insert into rentlog ( bno , lstartlog , lendlog , lcompletelog , mno ) 
	values( 1 , '2023-07-01 15:20:30' , '2023-07-04 15:20:30' , '2023-07-04 15:20:30' , 1 );
insert into rentlog ( bno , lstartlog , lendlog , lcompletelog , mno ) 
	values( 1 , '2023-07-05 14:10:10' , '2023-07-08 14:10:10' , '2023-07-08 14:10:10' , 1 );
insert into rentlog ( bno , lstartlog , lendlog , lcompletelog , mno ) 
	values( 2 , '2023-07-06 08:20:40' , '2023-07-07 08:20:40' , '2023-07-07 08:20:40' , 2 );
insert into rentlog ( bno , lstartlog , lendlog , lcompletelog , mno )
	values( 3 , '2023-07-06 11:40:10' , '2023-07-08 11:40:10' , '2023-07-08 11:40:10' , 2 );
insert into rentlog ( bno , lstartlog , lendlog , lcompletelog , mno ) 
	values( 4 , '2023-07-07 16:10:20' , '2023-07-08 16:10:20' , '2023-07-08 16:10:20' , 3 );
insert into rentlog ( bno , lstartlog , lendlog , lcompletelog , mno ) 
	values( 9 , '2023-07-08 20:30:50' , '2023-07-09 20:30:50' , '2023-07-09 20:30:50' , 4 );
insert into rentlog ( bno , lstartlog , lendlog , lcompletelog , mno ) 
	values( 10 , '2023-07-09 11:50:30' , '2023-07-09 20:50:30' , '2023-07-09 20:50:30' , 5 );
insert into rentlog ( bno , lstartlog , lendlog , lcompletelog , mno ) 
	values( 6 , '2023-08-01 17:10:20' , '2023-08-10 17:10:20' , '2023-08-10 17:10:20' , 6 );
insert into rentlog ( bno , lstartlog , lendlog , lcompletelog , mno ) 
	values( 5 , '2023-08-02 10:20:10' , '2023-08-03 10:20:10' , '2023-08-03 10:20:10' , 1 );
insert into rentlog ( bno , lstartlog , lendlog , lcompletelog , mno ) 
	values( 7 , '2023-08-03 21:30:10' , '2023-08-05 21:30:10' , '2023-08-05 21:30:10' , 2 );


#후기테이블(content) # 차량정보와 상이해서 lno bno 맞춰서 수정해놓았음 
insert into content ( lno , utitle , ucontent , ustatus ) values ( 1 , '아반23' , '좋아요' ,0 ); # pno1
insert into content ( lno , utitle , ucontent , ustatus ) values ( 2 , '아반23-2' , '좋아서 두번 이용했어요' , 1 );#pno1
insert into content ( lno , utitle , ucontent , ustatus) values ( 3 , '아반21' , '하하하' , 0 );#pno1
insert into content ( lno , utitle , ucontent , ustatus) values ( 4 , '아반21' , '즐거운 여행 했습니다.' , 1 );#pno1
insert into content ( lno , utitle , ucontent , ustatus) values ( 5 , '카니22' , '카니발발' , 1 );#pno2
insert into content ( lno , utitle , ucontent , ustatus) values ( 6 , 'k9 22' , '가격이 싸요' , 1 );#pno4
insert into content ( lno , utitle , ucontent , ustatus) values ( 7 , 'k9 22' , '가성비 좋아요' , 1 );#pno




















