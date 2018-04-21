create table user_tier_scale(id Long NOT NULL AUTO_INCREMENT, points INT,user_tier ENUM('BRONZE','SILVER','GOLDEN'),PRIMARY KEY(id));
insert into user_tier_scale(points,user_tier) values (11,'BRONZE');
insert into user_tier_scale(points,user_tier) values (20,'SILVER');
insert into user_tier_scale(points,user_tier) values (30,'GOLDEN');

INSERT INTO friends(user_one_id,user_two_id,status) VALUES (1,2,1);
INSERT INTO friends(user_one_id,user_two_id,status) VALUES (2,3,1);
INSERT INTO friends(user_one_id,user_two_id,status) VALUES (1,3,1);
INSERT INTO friends(user_one_id,user_two_id,status) VALUES (3,4,1);

INSERT INTO ct(name,address,description,type,ambient) VALUES ('Arena1 ','Bulevar Mihajla Pupina 3, Novi Sad','A short description that is absolutely  pointless','CINEMA','0');
INSERT INTO ct(name,address,description,type,ambient) VALUES ('CineStar1','Sentandrejski Put 11, Novi Sad','A short description that is absolutely  pointless','CINEMA','0');
INSERT INTO ct(name,address,description,type,ambient) VALUES ('Sprsko Narodno Pozoriste','Pozorisni Trg 1, Novi Sad','A short description that is absolutely  pointless','THEATER','0');
INSERT INTO ct(name,address,description,type,ambient) VALUES ('Arena Cineplex','Bulevar Mihajla Pupina 3, Novi Sad','A short description that is absolutely  pointless','CINEMA','0');
INSERT INTO ct(name,address,description,type,ambient) VALUES ('CineStar','Sentandrejski Put 11, Novi Sad','A short description that is absolutely  pointless','CINEMA','0');
INSERT into ct(name,address,description,type,ambient) values('beograd Cinema','addressC','descriptionC','CINEMA','4');
INSERT into ct(name,address,description,type,ambient) values('cacak Theater','addressT','descriptionT','THEATER','2');
INSERT into ct(name,address,description,type,ambient) values('Pozoriste mladih','Novi Sad','pozoriste','THEATER','5');

INSERT INTO halls(rows,cols,name,ct_id) values ('20','20','hall 1','1');
INSERT INTO halls(rows,cols,name,ct_id) values ('5','5','hall 2','1');
INSERT INTO halls(rows,cols,name,ct_id) values ('5','5','hall 3','1');
INSERT INTO halls(rows,cols,name,ct_id) values ('5','5','hall 4','1');
INSERT INTO halls(rows,cols,name,ct_id) values ('5','5','hall 5','1');
INSERT INTO halls(rows,cols,name,ct_id) values ('5','5','hall 6','1');

INSERT INTO ct_halls(cinema_theater_id, halls_id) values ('1','1');
INSERT INTO ct_halls(cinema_theater_id, halls_id) values ('1','2');
INSERT INTO ct_halls(cinema_theater_id, halls_id) values ('1','3');
INSERT INTO ct_halls(cinema_theater_id, halls_id) values ('1','4');
INSERT INTO ct_halls(cinema_theater_id, halls_id) values ('1','5');
INSERT INTO ct_halls(cinema_theater_id, halls_id) values ('1','6');

INSERT into projections(name,actors,genre,director,duration,image_path, description, hall_id, ct_id,date,price, time) values('Inception','Leonardo','Sci-Fy','Nolan','180','path','none','1','1','2018-04-19','250','09:50:00');
INSERT into projections(name,actors,genre,director,duration,image_path, description, hall_id, ct_id,date,price, time) values('Interstellar','Alright','Sci-Fy','Nolan','180','path','none','1','1','2018-05-25','250','19:00:00');
INSERT into projections(name,actors,genre,director,duration,image_path, description, hall_id, ct_id,date,price, time) values('Inception','Leonardo','Sci-Fy','Nolan','180','path','none','2','1','2018-04-19','250','18:00:00');
INSERT into projections(name,actors,genre,director,duration,image_path, description, hall_id, ct_id,date,price, time) values('Inception','Leonardo','Sci-Fy','Nolan','180','path','none','2','1','2018-04-19','250','17:00:00');
INSERT into projections(name,actors,genre,director,duration,image_path, description, hall_id, ct_id,date,price, time) values('Inception','Leonardo','Sci-Fy','Nolan','180','path','none','2','1','2018-04-26','250','19:00:00');

INSERT into ct_projections(cinema_theater_id,projections_id) values('1','1');
INSERT into ct_projections(cinema_theater_id,projections_id) values('1','2');
INSERT into ct_projections(cinema_theater_id,projections_id) values('1','3');
INSERT into ct_projections(cinema_theater_id,projections_id) values('1','4');
INSERT into ct_projections(cinema_theater_id,projections_id) values('1','5');


INSERT into seats(hall_id,row,col,seat_type,projection_id) values('1','1','1','AVAILABLE','1');
INSERT into seats(hall_id,row,col,seat_type,projection_id) values('1','1','2','AVAILABLE','1');
INSERT into seats(hall_id,row,col,seat_type,projection_id) values('1','1','3','TAKEN','1');
INSERT into seats(hall_id,row,col,seat_type,projection_id) values('1','1','4','AVAILABLE','1');
INSERT into seats(hall_id,row,col,seat_type,projection_id) values('1','2','1','VIP','1');
INSERT into seats(hall_id,row,col,seat_type,projection_id) values('1','2','2','AVAILABLE','1');
INSERT into seats(hall_id,row,col,seat_type,projection_id) values('1','3','4','REDACTED','1');
INSERT into seats(hall_id,row,col,seat_type,projection_id) values('1','3','3','AVAILABLE','1');
INSERT into seats(hall_id,row,col,seat_type,projection_id) values('1','2','3','AVAILABLE','1');
INSERT into seats(hall_id,row,col,seat_type,projection_id) values('1','2','4','AVAILABLE','1');
INSERT into seats(hall_id,row,col,seat_type,projection_id) values('1','3','1','AVAILABLE','1');
INSERT into seats(hall_id,row,col,seat_type,projection_id) values('1','3','2','AVAILABLE','1');

INSERT into seats(hall_id,row,col,seat_type,projection_id) values('1','1','1','AVAILABLE','2');
INSERT into seats(hall_id,row,col,seat_type,projection_id) values('1','1','2','AVAILABLE','2');
INSERT into seats(hall_id,row,col,seat_type,projection_id) values('1','1','3','TAKEN','2');
INSERT into seats(hall_id,row,col,seat_type,projection_id) values('1','1','4','AVAILABLE','2');
INSERT into seats(hall_id,row,col,seat_type,projection_id) values('1','2','1','VIP','2');
INSERT into seats(hall_id,row,col,seat_type,projection_id) values('1','2','2','AVAILABLE','2');
INSERT into seats(hall_id,row,col,seat_type,projection_id) values('1','3','4','REDACTED','2');
INSERT into seats(hall_id,row,col,seat_type,projection_id) values('1','3','3','AVAILABLE','2');
INSERT into seats(hall_id,row,col,seat_type,projection_id) values('1','2','3','AVAILABLE','2');
INSERT into seats(hall_id,row,col,seat_type,projection_id) values('1','2','4','AVAILABLE','2');
INSERT into seats(hall_id,row,col,seat_type,projection_id) values('1','3','1','AVAILABLE','2');
INSERT into seats(hall_id,row,col,seat_type,projection_id) values('1','3','2','AVAILABLE','2');

INSERT into halls_seats(halls_id,seats_id) values('1','1');
INSERT into halls_seats(halls_id,seats_id) values('1','2');
INSERT into halls_seats(halls_id,seats_id) values('1','3');
INSERT into halls_seats(halls_id,seats_id) values('1','4');
INSERT into halls_seats(halls_id,seats_id) values('1','5');
INSERT into halls_seats(halls_id,seats_id) values('1','6');
INSERT into halls_seats(halls_id,seats_id) values('1','7');
INSERT into halls_seats(halls_id,seats_id) values('1','8');
INSERT into halls_seats(halls_id,seats_id) values('1','9');
INSERT into halls_seats(halls_id,seats_id) values('1','10');
INSERT into halls_seats(halls_id,seats_id) values('1','11');
INSERT into halls_seats(halls_id,seats_id) values('1','12');
INSERT into halls_seats(halls_id,seats_id) values('2','23');


INSERT into halls_seats(halls_id,seats_id) values('1','13');
INSERT into halls_seats(halls_id,seats_id) values('1','14');
INSERT into halls_seats(halls_id,seats_id) values('1','24');
INSERT into halls_seats(halls_id,seats_id) values('1','15');
INSERT into halls_seats(halls_id,seats_id) values('1','16');
INSERT into halls_seats(halls_id,seats_id) values('1','17');
INSERT into halls_seats(halls_id,seats_id) values('1','18');
INSERT into halls_seats(halls_id,seats_id) values('1','19');
INSERT into halls_seats(halls_id,seats_id) values('1','20');
INSERT into halls_seats(halls_id,seats_id) values('1','21');
INSERT into halls_seats(halls_id,seats_id) values('1','22');


create table user_tier_scale(id Long NOT NULL AUTO_INCREMENT, points INT,user_tier ENUM('BRONZE','SILVER','GOLDEN'),PRIMARY KEY(id));

insert into isadb.users(first_name, last_name,password_user,email,city,phone,enabled, role,user_tier,first_login,points) values('admin', 'admin','admin','admin@gmail.com','novi sad','021', TRUE, 'ADMIN','BRONZE',true,'10');
insert into isadb.users(first_name, last_name,password_user,email,city,phone,enabled,role,user_tier,first_login,points) values('jefimija', 'jefimija','jefimija','isamejl811@gmail.com','jefimija','jefimija', TRUE,'GUEST','BRONZE',true,'10');
insert into isadb.users(first_name, last_name,password_user,email,city,phone,enabled,role,user_tier,first_login,points) values('doda', 'doda','doda','doda@gmail.com','doda','doda', TRUE,'GUEST','BRONZE',true,'6');
insert into isadb.users(first_name, last_name,password_user,email,city,phone,enabled, role,user_tier,first_login,points) values('isidora', 'isidora','isidora','isidora@gmail.com','novi sad','021',TRUE,'ADMINFZ','BRONZE',true,'5');
insert into isadb.users(first_name, last_name,password_user,email,city,phone,enabled, role,user_tier,first_login,points) values('njari', 'njari','njari','njari@gmail.com','novi sad','021',TRUE,'ADMINFZ','BRONZE',true,'14');

insert into user_tier_scale(points,user_tier) values (11,'BRONZE');
insert into user_tier_scale(points,user_tier) values (20,'SILVER');
insert into user_tier_scale(points,user_tier) values (30,'GOLDEN');

/*CREATE TABLE reservations_merchandise(id Long NOT NULL AUTO_INCREMENT, user_id long,merchandise_id LONG,PRIMARY KEY(id));
INSERT INTO reservations_merchandise(user_id,merchandise_id) VALUES (2,1);*/

INSERT INTO ads(name_ad,description,image_ad,user_id,price_ad,date_end_of_bids, ad_bid_status,version) VALUES ('badge','starwars badge','/images/jediresize.jpg',5,4,'2019-11-11','ACCEPTED',0);
INSERT INTO ads(name_ad,description,image_ad,user_id,price_ad,date_end_of_bids,ad_bid_status,version) VALUES ('cup','frozen cup','/images/frozen.jpg',2,5,'2018-11-11','WAITING',0);



INSERT INTO notifications(id_receiver, topic, message) VALUES (1,'Bid notification','Your bid has been rejected.');
INSERT INTO notifications(id_receiver, topic, message) VALUES (1,'Ad notification','Your ad has been approved.');


INSERT INTO reservations_merchandise(user_id,merchandise_id,version) VALUES (1,1,0);


INSERT INTO isadb.merchandise(user_id, name_merchandise,description,price_merchandise,image_merchandise) VALUES (1,'badge','starwars badge',5,'/images/jediresize.jpg');
INSERT INTO isadb.merchandise(user_id, name_merchandise,description,price_merchandise,image_merchandise) VALUES (1,'cup','frozen cup',8,'/images/frozen.jpg');


INSERT INTO isadb.bids(id_ad,id_guest_bid,price_bid,ad_bid_status,version) VALUES (1,3,3,'WAITING',0);

INSERT INTO isadb.notifications(id_receiver, topic, message) VALUES (1,'Bid notification','Your bid has been rejected.');
INSERT INTO isadb.notifications(id_receiver, topic, message) VALUES (1,'Ad notification','Your ad has been approved.');


INSERT INTO notifications(id_receiver, topic, message) VALUES (1,'Bid notification','Your bid has been rejected.');
INSERT INTO notifications(id_receiver, topic, message) VALUES (1,'Ad notification','Your ad has been approved.');


UPDATE ct set user_id = 4 where id = 1;

