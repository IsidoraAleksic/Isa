
insert into users(first_name, last_name,password_user,email,city,phone,enabled, role,user_tier,first_login,points) values('Jefimija', 'Zivkovic','123','isamejl811@gmail.com','Novi Sad','021', TRUE, 'GUEST','BRONZE',true,'10');
insert into users(first_name, last_name,password_user,email,city,phone,enabled,role,user_tier,first_login,points) values('Isidora', 'Aleksic','1234','isidora@gmail.com','Novi Sad','021', TRUE,'ADMINFZ','BRONZE',true,'10');
insert into users(first_name, last_name,password_user,email,city,phone,enabled, role,user_tier,first_login,points) values('Daniel', 'Njari','12345','daniel@gmail.com','Novi Sad','021',TRUE,'ADMINCT','BRONZE',true,'5');
insert into users(first_name, last_name,password_user,email,city,phone,enabled, role,user_tier,first_login,points) values('Pera', 'Markovic','123','zivkovic.jefimijaa@gmail.com','Beograd','011',TRUE,'ADMINCT','BRONZE',true,'14');
insert into users(first_name, last_name,password_user,email,city,phone,enabled, role,user_tier,first_login,points) values('Admin', 'Sistema','123','admin@gmail.com','Beograd','011',TRUE,'ADMIN','BRONZE',true,'14');
create table user_tier_scale(id Long NOT NULL AUTO_INCREMENT, points INT,user_tier ENUM('BRONZE','SILVER','GOLDEN'),PRIMARY KEY(id));

insert into users(first_name, last_name,password_user,email,city,phone,enabled, role,user_tier,first_login,points) values('admin', 'admin','admin','admin@gmail.com','novi sad','021', TRUE, 'ADMIN','BRONZE',true,'10');
insert into users(first_name, last_name,password_user,email,city,phone,enabled,role,user_tier,first_login,points) values('jefimija', 'jefimija','jefimija','isamejl811@gmail.com','jefimija','jefimija', TRUE,'GUEST','BRONZE',true,'10');
insert into users(first_name, last_name,password_user,email,city,phone,enabled, role,user_tier,first_login,points) values('isidora', 'isidora','isidora','isidora@gmail.com','novi sad','021',TRUE,'ADMINFZ','BRONZE',true,'5');
insert into users(first_name, last_name,password_user,email,city,phone,enabled, role,user_tier,first_login,points) values('njari', 'njari','njari','njaridaniel@gmail.com','novi sad','021',TRUE,'ADMINCT','BRONZE',true,'14');
insert into users(first_name, last_name,password_user,email,city,phone,enabled,role,user_tier,first_login,points) values('doda', 'doda','doda','doda@gmail.com','doda','doda', TRUE,'GUEST','BRONZE',true,'6');

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


INSERT INTO ct(name,address,description,type,ambient,user_id) VALUES ('Arena Cineplex','Bulevar Mihajla Pupina 3, Novi Sad','Bioskop u Novom Sadu','CINEMA','4','3');
INSERT INTO ct(name,address,description,type,ambient,user_id) VALUES ('CineStar1','Sentandrejski Put 11, Novi Sad','Bioskop u Bigu','CINEMA','3',NULL);
INSERT into ct(name,address,description,type,ambient,user_id) values('Pozoriste mladih','Novi Sad','Pozoriste u Novom Sadu','THEATER','5', NULL );
INSERT INTO ct(name,address,description,type,ambient,user_id) VALUES ('Sprsko Narodno Pozoriste','Pozorisni Trg 1, Novi Sad','Pozoriste u centru','THEATER','1', NULL );

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

/*CREATE TABLE reservations_merchandise(id Long NOT NULL AUTO_INCREMENT, user_id long,merchandise_id LONG,PRIMARY KEY(id));
INSERT INTO reservations_merchandise(user_id,merchandise_id) VALUES (2,1);*/

INSERT INTO ads(name_ad,description,image_ad,user_id,price_ad,date_end_of_bids, ad_bid_status,version) VALUES ('badge','starwars badge','/images/jediresize.jpg',5,4,'2019-11-11','ACCEPTED',0);
INSERT INTO ads(name_ad,description,image_ad,user_id,price_ad,date_end_of_bids,ad_bid_status,version) VALUES ('cup','frozen cup','/images/frozen.jpg',2,5,'2018-11-11','WAITING',0);



INSERT INTO notifications(id_receiver, topic, message) VALUES (1,'Bid notification','Your bid has been rejected.');
INSERT INTO notifications(id_receiver, topic, message) VALUES (1,'Ad notification','Your ad has been approved.');


INSERT INTO reservations_merchandise(user_id,merchandise_id,version) VALUES (1,1,0);


INSERT INTO merchandise(user_id, name_merchandise,description,price_merchandise,image_merchandise) VALUES (1,'badge','starwars badge',5,'/images/jediresize.jpg');
INSERT INTO merchandise(user_id, name_merchandise,description,price_merchandise,image_merchandise) VALUES (1,'cup','frozen cup',8,'/images/frozen.jpg');


INSERT INTO bids(id_ad,id_guest_bid,price_bid,ad_bid_status,version) VALUES (1,3,3,'WAITING',0);

INSERT INTO notifications(id_receiver, topic, message) VALUES (1,'Bid notification','Your bid has been rejected.');
INSERT INTO notifications(id_receiver, topic, message) VALUES (1,'Ad notification','Your ad has been approved.');


INSERT INTO notifications(id_receiver, topic, message) VALUES (1,'Bid notification','Your bid has been rejected.');
INSERT INTO notifications(id_receiver, topic, message) VALUES (1,'Ad notification','Your ad has been approved.');


UPDATE ct set user_id = 4 where id = 1;


/*--dont delete below*/

INSERT into projections(name,actors,genre,director,duration,image_path, description, hall_id, ct_id,date,price, time) values('Inception','Leonardo','Sci-Fy','Nolan','180','path','none','1','1','2018-04-18','250','21:00:00');
INSERT into projections(name,actors,genre,director,duration,image_path, description, hall_id, ct_id,date,price, time) values('Interstellar','Alright','Sci-Fy','Nolan','180','path','none','1','1','2018-03-17','250','19:00:00');
INSERT into projections(name,actors,genre,director,duration,image_path, description, hall_id, ct_id,date,price, time) values('Interstellar','Alright','Sci-Fy','Nolan','180','path','none','2','1','2018-04-11','250','16:00:00');

INSERT into seats(hall_id,row,col,seat_type,projection_id) values('1','1','1','AVAILABLE','6');
INSERT into seats(hall_id,row,col,seat_type,projection_id) values('1','1','2','AVAILABLE','6');
INSERT into seats(hall_id,row,col,seat_type,projection_id) values('1','1','1','AVAILABLE','7');
INSERT into seats(hall_id,row,col,seat_type,projection_id) values('2','1','2','AVAILABLE','8');
INSERT into seats(hall_id,row,col,seat_type,projection_id) values('2','1','2','AVAILABLE','8');


INSERT into halls_seats(halls_id,seats_id) values('1','25');
INSERT into halls_seats(halls_id,seats_id) values('1','26');
INSERT into halls_seats(halls_id,seats_id) values('1','27');

INSERT into halls_seats(halls_id,seats_id) values('2','28');
INSERT into halls_seats(halls_id,seats_id) values('2','29');

insert into ticket(discount, status, projection_id, seat_id) values('0','1','6','25');
insert into ticket(discount, status, projection_id, seat_id) values('0','1','6','26');
insert into ticket(discount, status, projection_id, seat_id) values('0','1','7','27');
insert into ticket(discount, status, projection_id, seat_id) values('0','1','8','28');
insert into ticket(discount, status, projection_id, seat_id) values('0','1','8','29');

insert into users_reserved_tickets(user_id, reserved_tickets_id) values('2','1');
insert into users_reserved_tickets(user_id, reserved_tickets_id) values('2','2');
insert into users_reserved_tickets(user_id, reserved_tickets_id) values('2','3');
insert into users_reserved_tickets(user_id, reserved_tickets_id) values('2','4');
insert into users_reserved_tickets(user_id, reserved_tickets_id) values('2','5');

insert into projections_taken(projections_id,taken_id) values('6','1');
insert into projections_taken(projections_id,taken_id) values('6','2');
insert into projections_taken(projections_id,taken_id) values('7','3');
insert into projections_taken(projections_id,taken_id) values('8','4');
insert into projections_taken(projections_id,taken_id) values('8','5');

INSERT into ct_projections(cinema_theater_id,projections_id) values('1','6');
INSERT into ct_projections(cinema_theater_id,projections_id) values('1','7');
INSERT into ct_projections(cinema_theater_id,projections_id) values('1','8');

/*--dont delete above*/
