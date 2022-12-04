select code, code_name
from code
where group_code = '001'
	  and use_yn = 'Y';
      

select user_name, fun_code('001', user_clsf) from users;

insert into group_code values ('001', '회원 분류');
insert into group_code values ('002', '공지사항 분류');
insert into group_code values ('003', '이벤트 분류');
insert into group_code values ('004', '이벤트 참가 분류');

insert into code values ('001', '001', '관리자', 'Y');
insert into code values ('001', '002', '일반회원', 'Y');

insert into code values ('002', '001', '공지사항', 'Y');
insert into code values ('002', '002', '업데이트', 'Y');

insert into code values ('003', '001', '시작 전', 'Y');
insert into code values ('003', '002', '진행 중', 'Y');
insert into code values ('003', '003', '종료', 'Y');

insert into code values ('004', '001', '미참가', 'Y');
insert into code values ('004', '002', '참가', 'Y');

select * from code;
select * from group_code;


select * from house_info;
select * from house_deal;

select hd.house_deal_id, hi.house_id, hd.deal_amount, hd.area, hd.floor, hi.build_year, hi.road_name, hi.dong, hi.apartment_name, hi.lng, hi.lat
from house_info hi,
	 house_deal hd
where hi.house_id = hd.house_id
	  and dong_code = 1111011500
      and apartment_name like concat("%", "광화문", "%")
limit 5 offset 0;

select hd.house_deal_id, hi.house_id,
	   hd.deal_year, hd.deal_month,
	   hd.deal_amount, hd.area,
       hd.floor, hi.build_year,
       hi.road_name, hi.dong,
       hi.apartment_name, hi.lng, hi.lat
from house_info hi,
	 house_deal hd
where hi.house_id = hd.house_id
	  and dong_code = 1111011500
limit 5 offset 0;

select hd.house_deal_id, hi.house_id,
	   hd.deal_year, hd.deal_month,
	   hd.deal_amount, hd.area,
       hd.floor, hi.build_year,
       hi.road_name, hi.dong,
       hi.apartment_name, hi.lng, hi.lat
from house_info hi,
	 house_deal hd
where hi.house_id = hd.house_id
	  and hd.house_deal_id = '111101501000001';

select * from house_deal;

select hd.house_deal_id, hi.house_id,
	   hd.deal_year, hd.deal_month, hd.deal_day,
	   hd.deal_amount, hd.area,
       hd.floor, hi.build_year,
       hi.road_name, hi.dong,
       hi.apartment_name, hi.lng, hi.lat
from house_info hi,
	 house_deal hd
where hi.house_id = hd.house_id
	  and dong_code = 1111011500
      and apartment_name like concat("%", "광화문", "%")
	  and hd.deal_year between 2015 and 2017
      and hd.deal_month between 1 and 12
      and hd.deal_day between 21 and 25
      and hd.deal_amount between 100 and 120
      and hd.area between 10 and 100
limit 10 offset 0;


select hd.house_deal_id, hi.house_id,
	   hd.deal_year, hd.deal_month, hd.deal_day,
	   hd.deal_amount, hd.area,
       hd.floor, hi.build_year,
       hi.road_name, hi.dong,
       hi.apartment_name, hi.lng, hi.lat
from house_info hi,
	 house_deal hd
where hi.house_id = hd.house_id
	  and dong_code = 1111011700 -- 고정
      and apartment_name like concat("%", "", "%")
	  and hd.deal_year between 2015 and 2022
      and hd.deal_month between 1 and 12
	  and hd.deal_day between 1 and 31
      and hd.deal_amount between 100 and 990
	  and hd.area between 9 and 100
limit 100 offset 0;

select min(area) from house_deal;
select max(area) from house_deal;
select max(deal_year) from house_deal;
select max(deal_day) from house_deal;

select min(deal_amount) from house_deal;
select max(deal_amount) from house_deal;

select deal_amount from house_deal;

select * from house_deal;

select hd.house_deal_id, hi.house_id, hd.deal_amount, hd.area, hd.floor, hi.build_year, hi.road_name, hi.dong, hi.apartment_name, hi.lng, hi.lat
from house_info hi,
	 house_deal hd
where hi.house_id = hd.house_id
	  and hd.house_deal_id = 111101501000001;


-- select min(cast(int, deal_amount)) from house_deal;

-- select max(cast(deal_amount as unsigned)) from house_deal;

-- select convert(deal_amount as int), min(cast(deal_amount as unsigned)) from house_deal;
-- select cast(deal_amount as INT) from house_deal;

-- select CAST(`12345` as int);

select hd.house_deal_id, hi.house_id, hd.deal_amount, hd.area, hd.floor, hi.build_year, hi.road_name, hi.dong, hi.apartment_name, hi.lng, hi.lat
from house_info hi,
	 house_deal hd
where hi.house_id = hd.house_id
	  and hd.house_deal_id = 111101501000001;
      
select *
from house_deal
where house_deal_id = 111101501000001;

select * from house_deal;

select max(deal_amount) from house_deal
where house_id = '11110000000027';

select * from house_deal
where house_id = '11110000000027'
order by deal_amount asc;

select hd.house_deal_id, hi.house_id,
	   hd.deal_year, hd.deal_month, hd.deal_day,
	   hd.deal_amount, hd.area,
       hd.floor, hi.build_year,
       hd.floor, hi.build_year,
       hi.road_name, hi.dong,
       hi.apartment_name, hi.lng, hi.lat
from house_info hi,
	 house_deal hd
where hi.house_id = hd.house_id
	  and dong_code = 1111011500
limit 10 offset 0;

set sql_safe_updates = 0;
update house_deal
   set deal_amount = cast( replace(deal_amount, ',','') as unsigned );
   
select * from house_deal;
select * from house_info;

select * from review;
select * from house_score;
select * from users;

select r.review_id,
	   hs.score_id, hs.house_id, hs.score,
       u.user_id, u.user_nickname, u.user_score,
       r.content, r.register_date
from review r,
	 users u,
     house_score hs
where r.user_id = u.user_id
	  and hs.score_id = r.score_id
      and hs.house_id = 11110000000001
limit 5 offset 0;

insert into house_score (house_id, score, user_id)
values ('11110000000001', '5', '1');

set sql_safe_updates = 0;
update house_deal
   set deal_amount = cast( replace(deal_amount, ',','') as unsigned );

insert into users (user_name, user_nickname, user_password, user_email, user_register_date, code)
values ('염정아', 'yeomss', '1234', 'yeom@yeom.com', now(), '001');

insert into house_review (house_id, content, score, user_id) values (11110000000001, '너무 편하고 좋아요!', 5, 1);
insert into house_review (house_id, content, score, user_id) values (11110000000001, '짱!!!!!!!!', 5,  2);
insert into house_review (house_id, content, score, user_id) values (11110000000001, '최고입니도', 5, 5);

insert into house_review (house_id, content, score, user_id) values (11110000000002, '너무 편하고 좋아요!', 3, 1);
insert into house_review (house_id, content, score, user_id) values (11110000000002, '짱!!!!!!!!', 3,  2);
insert into house_review (house_id, content, score, user_id) values (11110000000002, '최고입니도', 4, 5);

select * from house_review;

select avg(score) avg
from house_review
where house_id = 11110000000002;

select * from house_info;

CREATE TABLE `house_review` (
  `review_id` int NOT NULL AUTO_INCREMENT,
  `house_id` bigint NOT NULL,
  `content` text,
  `score` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `register_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`review_id`),
  KEY `fk_house_review_house_id_idx` (`house_id`),
  KEY `fk_house_review_user_id_idx` (`user_id`),
  CONSTRAINT `fk_house_review_house_id` FOREIGN KEY (`house_id`) REFERENCES `house_info` (`house_id`),
  CONSTRAINT `fk_house_review_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `dong_review` (
  `review_id` int NOT NULL AUTO_INCREMENT,
  `dong_code` varchar(10) NOT NULL,
  `score` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `register_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`review_id`),
  KEY `fk_dong_review_dong_code_idx` (`dong_code`),
  KEY `fk_dong_review_user_id_idx` (`user_id`),
  CONSTRAINT `fk_dong_review_dong_code` FOREIGN KEY (`dong_code`) REFERENCES `dong_code` (`dong_code`),
  CONSTRAINT `fk_dong_review_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

select * from house_review;

select * from house_deal;

select hr.review_id, hr.score, hr.house_id,
	   u.user_id, u.user_nickname, u.user_score,
	   hr.content, hr.register_date
from house_review hr,
	 users u
where u.user_id = hr.user_id
	  and hr.house_id = 11110000000003
order by hr.review_id desc
limit 5 offset 0;


insert into house_review (house_id, content, score, user_id)
values (11110000000002, '최고입니도', 4, 5);

update house_review
set content = "수정입니도1",
	score = 4
where review_id = 10;

select * from house_review;

delete from house_review
where review_id = 11;

select * from dong_review;
select * from dong_code;

insert into dong_review (dong_code, score, user_id) values (1111010100, 3, 1);
insert into dong_review (dong_code, score, user_id) values (1111010100, 3,  2);
insert into dong_review (dong_code, score, user_id) values (1111010100, 4, 5);

insert into dong_review (dong_code, score, user_id) values (1111010500, 2, 1);
insert into dong_review (dong_code, score, user_id) values (1111010500, 3,  2);
insert into dong_review (dong_code, score, user_id) values (1111010500, 4, 5);

select avg(score) avg
from dong_review
where dong_code = 1111010100;

insert into dong_review (dong_code, score, user_id)
values (1111010100, 4, 5);

update dong_review
set score = 1
where review_id = 5;

select * from dong_review;

delete from dong_review
where review_id = 4;

select count(*)
from house_deal
where dong_code = 1111010100;

select count(*)
from house_review;

select count(*)
from house_deal hd,
	 house_info hi
where hd.house_id = hi.house_id
	  and dong_code = 1111010100;
      
select count(*)
from house_review
where house_id = 11110000000001;

select count(*)
from house_review
where house_id = 11110000000002;

select ifnull(avg(score), 0) avg
from house_review
where house_id = 11110000000003;

select * from house_review;

select * from users;

select * from house_deal;

-- 리스트 조회 
select 
	   hd.house_deal_id, hi.house_id,
	   hd.deal_year, hd.deal_month, hd.deal_day,
	   hd.deal_amount, hd.area,
       hd.floor, hi.build_year,
       hi.road_name, hi.dong,
       hi.apartment_name, hi.lng, hi.lat
from house_info hi,
	 house_deal hd
where hi.house_id = hd.house_id
	  and hi.dong_code = 1111011700 -- 고정
      and hi.apartment_name like concat("%", "", "%")
	  and hd.deal_year between 2015 and 2022
      and hd.deal_month between 1 and 12
	  -- and hd.deal_day between 1 and 31
      and hd.deal_amount between 100 and 999999;
	  -- and hd.area between 9 and 100
-- limit 100 offset 0;

select avg(hi.lng), avg(hi.lat)
from house_info hi,
	 house_deal hd
where hi.house_id = hd.house_id
	  and hi.dong_code = 1111010100 -- 고정
      and hi.apartment_name like concat("%", "", "%")
	  and hd.deal_year between 2015 and 2022
      and hd.deal_month between 1 and 12
	  and hd.deal_day between 1 and 31
      and hd.deal_amount between 100 and 999999
	  -- and hd.area between 9 and 100
limit 100 offset 0;

select * from house_info
where apartment_name like "세종";

select *
from house_deal hd, house_info hi
where hd.house_id = hi.house_id
	  and dong_code = "1111011700";
      
select max(deal_amount)
from house_deal;

set sql_safe_updates = 0;
update house_deal
set deal_amount_int = cast( deal_amount as unsigned );


select * 
from house_deal;

select max(deal_amount_int)
from house_deal;

select max(deal_amount_int)
from house_deal;

select hd.house_deal_id, hi.house_id, hi.dong_code,
	   hd.deal_year, hd.deal_month, hd.deal_day,
	   hd.deal_amount, hd.area,
	   hd.floor, hi.build_year,
	   hi.road_name, hi.dong,
	   hi.apartment_name, hi.lng, hi.lat
from house_info hi,
	 house_deal hd
where hi.house_id = hd.house_id
	  and hi.dong_code = 1111010100
	  and hi.apartment_name like concat("%", "", "%")
	  and hd.deal_year between 2015 and 2022
	  and hd.deal_month between 1 and 12
	  and hd.deal_amount_int between 400 and 1200000
      and hd.area between 80 and 120 
limit 5 offset 0;

select min(area)
from house_deal;

select max(area)
from house_deal;

select * from house_info;

set sql_safe_updates = 0;
update house_deal
set deal_amount_int = cast(deal_amount as unsigned);

-- select hi.dong_code, dc.gugun_name, count(*) house_count
-- from house_deal hd,
-- 	 house_info hi,
--      dong_code dc
-- where hd.house_id = hi.house_id
-- 	  and dc.dong_code = hi.dong_code
-- 	  and right(dc.dong_code = "1111000000"
-- group by hi.dong_code;

SELECT @@sql_mode;

select b.dong_code, b.gugun_name, count(*) house_count
from
(select hd.house_id, ANY_VALUE(hi.dong_code), ANY_VALUE(dc.gugun_name)
from house_deal hd,
	 house_info hi,
     dong_code dc
where hd.house_id = hi.house_id
	  and dc.dong_code = hi.dong_code
	  and left(dc.dong_code, 4) like left("1117000000", 4)
group by hd.house_id) a, (select dong_code, gugun_name
from dong_code
where dong_code = "1117000000") b;

select count(*)
from
(select hd.house_id, ANY_VALUE(hi.dong_code), ANY_VALUE(dc.gugun_name)
from house_deal hd,
	 house_info hi,
     dong_code dc
where hd.house_id = hi.house_id
	  and dc.dong_code = hi.dong_code
	  and left(dc.dong_code, 4) like left("1156000000", 4)
group by hd.house_id) a;

select count(*)
from
(select hi.house_id
from house_deal hd,
	 house_info hi
where hi.house_id = hd.house_id
	  and left(dong_code, 4) = left("1156000000", 4)
	  and hi.apartment_name like concat("%", "", "%")
	  and hd.deal_year between 2015 and 2022
	  and hd.deal_month between 1 and 12
	  and hd.deal_amount_int between 400 and 1200000
      and hd.area between 0 and 120
group by hi.house_id) a;

select ANY_VALUE(hi.dong_code), ANY_VALUE(dc.gugun_name)
from house_deal hd,
	 house_info hi,
     dong_code dc
where hd.house_id = hi.house_id
	  and dc.dong_code = hi.dong_code
	  and left(dc.dong_code, 4) like left("1117000000", 4)
group by dc.gugun_name;

select dong_code, gugun_name
from dong_code
where dong_code = "1117000000";

SELECT *
FROM house_info hi
LEFT JOIN house_deal hd
ON hi.house_id = hd.house_id;

select distinct ANY_VALUE( hi.dong_code), ANY_VALUE(dc.gugun_name), ifnull(count(*), 0) houseCount
from house_deal hd,
	 house_info hi,
     dong_code dc
where hd.house_id = hi.house_id
	  and dc.dong_code = hi.dong_code
	  and left(dc.dong_code, 4) like left("1117000000", 4)
group by left(hi.dong_code, 4);

select *
from dong_code;

select *
from house_info;

select *
from dong_code
where dong_code = "1111000000";

DROP TABLE IF EXISTS `users_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_file` (
  `file_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `file_name` varchar(500) NOT NULL,
  `file_size` int NOT NULL,
  `file_content_type` varchar(500) NOT NULL,
  `file_url` varchar(500) NOT NULL,
  `register_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `code` char(3) DEFAULT NULL,
  PRIMARY KEY (`file_id`),
  KEY `fk_users_file_user_id_idx` (`user_id`),
  CONSTRAINT `fk_users_file_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

select count(*) from house_deal;

-- select hd.house_deal_id, hi.house_id, hi.dong_code,
-- 	   hd.deal_year, hd.deal_month, hd.deal_day,
-- 	   hd.deal_amount, hd.area,
-- 	   hd.floor, hi.build_year,
-- 	   hi.road_name, hi.dong,
-- 	   hi.apartment_name, hi.lng, hi.lat
-- from house_info hi,
-- 	 house_deal hd
-- where hi.house_id = hd.house_id
-- 	  and dong_code = #{dongCode}
-- 	  and apartment_name like concat("%", #{searchWord}, "%")
-- 	  and hd.deal_year between #{minYear} and #{maxYear}
-- 	  and hd.deal_month between #{minMonth} and #{maxMonth}
-- 	  and hd.deal_amount_int between #{minDealAmount} and #{maxDealAmount}
-- 	  and hd.area between #{minArea} and #{maxArea} 
-- limit #{limit} offset #{offset};

select hd.house_deal_id, hi.house_id, hi.dong_code,
	   hd.deal_year, hd.deal_month, hd.deal_day,
	   hd.deal_amount, hd.area,
	   hd.floor, hi.build_year,
	   hi.road_name, hi.dong,
	   hi.apartment_name, hi.lng, hi.lat
from house_info hi,
	 house_deal hd
where hi.house_id = hd.house_id
	  and left(dong_code, 4) = left(1159000000, 4)
	  and hi.apartment_name like concat("%", "", "%")
	  and hd.deal_year between 2015 and 2022
	  and hd.deal_month between 1 and 12
	  and hd.deal_amount_int between 400 and 1200000
      and hd.area between 80 and 120
limit 10 offset 0;

select *
from dong_code
where dong_code = 1159000000;