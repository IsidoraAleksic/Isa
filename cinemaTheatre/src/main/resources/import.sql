insert into isadb.users(first_name, last_name,password_user, email,city,phone,enabled, role,user_tier) values('admin', 'admin','admin','admin@gmail.com','novi sad','021', TRUE, 'ADMIN','BRONZE');
insert into isadb.users(first_name, last_name,password_user, email,city,phone,enabled,role,user_tier) values('jefimija', 'jefimija','jefimija','isamejl811@gmail.com','jefimija','jefimija', TRUE, 'GUEST','BRONZE');
insert into isadb.users(first_name, last_name,password_user, email,city,phone,enabled, role,user_tier) values('isidora', 'isidora','isidora','isidora@gmail.com','novi sad','021',TRUE,'ADMINFZ','BRONZE');
insert into isadb.users(first_name, last_name,password_user, email,city,phone,enabled, role,user_tier) values('njari', 'njari','njari','njari@gmail.com','novi sad','021',TRUE,'GUEST','BRONZE');
INSERT INTO friends(user_one_id,user_two_id,status) VALUES (1,2,1);
INSERT INTO friends(user_one_id,user_two_id,status) VALUES (2,3,1);
INSERT INTO friends(user_one_id,user_two_id,status) VALUES (1,3,1);
INSERT INTO friends(user_one_id,user_two_id,status) VALUES (3,4,1);


INSERT INTO merchandise(id_ad, name_merchandise,description,price_merchandise,image_merchandise) VALUES (1,'badge','starwars badge',5,'/images/jediresize.jpg');
INSERT INTO merchandise(id_ad, name_merchandise,description,price_merchandise,image_merchandise) VALUES (1,'cup','frozen cup',8,'/images/frozen.jpg');

INSERT INTO ads(name_ad,description,image_ad,user_id,price_ad,date_end_of_bids, ad_bid_status) VALUES ('badge','starwars badge','/images/jediresize.jpg',1,4,'2019-11-11','WAITING');
INSERT INTO ads(name_ad,description,image_ad,user_id,price_ad,date_end_of_bids,ad_bid_status) VALUES ('cup','frozen cup','/images/frozen.jpg',1,5,'2018-11-11','WAITING');

INSERT INTO bids(id_ad,id_guest_bid,price_bid,ad_bid_status) VALUES (1,1,3,'WAITING');
INSERT INTO bids(id_ad,id_guest_bid,price_bid,ad_bid_status) VALUES (1,1,5,'WAITING');
INSERT INTO bids(id_ad,id_guest_bid,price_bid,ad_bid_status) VALUES (2,1,25,'WAITING');

INSERT INTO ct(name,address,description,type,ambient) VALUES ('Arena Cineplex','Bulevar Mihajla Pupina 3, Novi Sad','A short description that is absolutely  pointless','CINEMA','3.443');
INSERT INTO ct(name,address,description,type,ambient) VALUES ('CineStar','Sentandrejski Put 11, Novi Sad','A short description that is absolutely  pointless','CINEMA','4.501');
INSERT INTO ct(name,address,description,type,ambient) VALUES ('Sprsko Narodno Pozoriste','Pozorisni Trg 1, Novi Sad','A short description that is absolutely  pointless','THEATER','4.500');

INSERT INTO halls(rows,cols,name,ct_id) values ('10','10','hall 1','1');
INSERT INTO halls(rows,cols,name,ct_id) values ('10','8','hall 2','1');
INSERT INTO halls(rows,cols,name,ct_id) values ('12','14','hall 1','2');
INSERT INTO halls(rows,cols,name,ct_id) values ('10','10','hall 2','2');
INSERT INTO halls(rows,cols,name,ct_id) values ('22','20','hall 1','3');
INSERT INTO halls(rows,cols,name,ct_id) values ('16','16','hall 2','3');

INSERT INTO projections(actors,date,description,director,duration,genre,image_path,name,price,ct_id,hall_id) VALUES ('name lastname, name2 lastname2, name3 lastname2','2018-04-16','projection description, pointless atm other than wasting/filling space','director name1','97','TRAGEDY','https://i.imgur.com/bOTGH4t.png','MOVIE NAME1','350','1','1');
INSERT INTO projections(actors,date,description,director,duration,genre,image_path,name,price,ct_id,hall_id) VALUES ('name lastname, name2 lastname2, name3 lastname2','2018-04-18','projection description, pointless atm other than wasting/filling space','director name2','103','TRAGEDY','https://i.imgur.com/bOTGH4t.png','MOVIE NAME2','370','1','1');
INSERT INTO projections(actors,date,description,director,duration,genre,image_path,name,price,ct_id,hall_id) VALUES ('name lastname, name2 lastname2','2018-04-18','projection description, pointless atm other than wasting/filling space','director name2','94','TRAGEDY','https://i.imgur.com/bOTGH4t.png','MOVIE NAME3','410','1','2');
INSERT INTO projections(actors,date,description,director,duration,genre,image_path,name,price,ct_id,hall_id) VALUES ('name lastname, name3 lastname2','2018-04-18','projection description, pointless atm other than wasting/filling space','director name2','92','TRAGEDY','https://i.imgur.com/bOTGH4t.png','MOVIE NAME4','450','2','2');
INSERT INTO projections(actors,date,description,director,duration,genre,image_path,name,price,ct_id,hall_id) VALUES ('name2 lastname2, name3 lastname2','2018-04-19','projection description, pointless atm other than wasting/filling space','director name3','98','TRAGEDY','https://i.imgur.com/bOTGH4t.png','SHOW NAME5','480','3','1');

INSERT INTO notifications(id_receiver, topic, message) VALUES (1,'Bid notification','Your bid has been rejected.');
INSERT INTO notifications(id_receiver, topic, message) VALUES (1,'Ad notification','Your ad has been approved.');

INSERT INTO reservations_merchandise(user_id,merchandise_id) VALUES (1,1);

INSERT INTO isadb.merchandise(user_id, name_merchandise,description,price_merchandise,image_merchandise) VALUES (1,'badge','starwars badge',5,'/images/jediresize.jpg');
INSERT INTO isadb.merchandise(user_id, name_merchandise,description,price_merchandise,image_merchandise) VALUES (1,'cup','frozen cup',8,'/images/frozen.jpg');

INSERT into isadb.ct(name,address,description,type,ambient) values('nameC','addressC','descriptionC','CINEMA','0');
INSERT into isadb.ct(name,address,description,type,ambient) values('nameT','addressT','descriptionT','THEATER','0');
INSERT INTO isadb.bids(id_ad,id_guest_bid,price_bid,ad_bid_status) VALUES (1,3,3,'WAITING');
INSERT INTO isadb.bids(id_ad,id_guest_bid,price_bid,ad_bid_status) VALUES (1,2,5,'WAITING');
INSERT INTO isadb.bids(id_ad,id_guest_bid,price_bid,ad_bid_status) VALUES (2,2,25,'WAITING');
INSERT INTO isadb.notifications(id_receiver, topic, message) VALUES (1,'Bid notification','Your bid has been rejected.');
INSERT INTO isadb.notifications(id_receiver, topic, message) VALUES (1,'Ad notification','Your ad has been approved.');
INSERT INTO isadb.reservations_merchandise(user_id,merchandise_id) VALUES (1,1);



INSERT INTO ct(name,address,description,type,ambient) VALUES ('Arena Cineplex','Bulevar Mihajla Pupina 3, Novi Sad','A short description that is absolutely  pointless','CINEMA','3');
INSERT INTO ct(name,address,description,type,ambient) VALUES ('CineStar','Sentandrejski Put 11, Novi Sad','A short description that is absolutely  pointless','CINEMA','4');
INSERT into isadb.ct(name,address,description,type,ambient) values('beograd Cinema','addressC','descriptionC','CINEMA','0');
INSERT into isadb.ct(name,address,description,type,ambient) values('cacak Theater','addressT','descriptionT','THEATER','0');
INSERT into isadb.ct(name,address,description,type,ambient) values('Pozoriste mladih','Novi Sad','pozoriste','THEATER','0');
INSERT into isadb.projections(name,actors,genre,director,duration,image_path, description, hall_id, ct_id,date,price, time) values('Inception','Leonardo','Sci-Fy','Nolan','180','path','none','1','1','2018-04-25','250','19:00:00');
INSERT into isadb.projections(name,actors,genre,director,duration,image_path, description, hall_id, ct_id,date,price, time) values('Interstellar','Alright','Sci-Fy','Nolan','180','path','none','1','1','2018-05-25','250','19:00:00');
INSERT into isadb.projections(name,actors,genre,director,duration,image_path, description, hall_id, ct_id,date,price, time) values('Inception','Leonardo','Sci-Fy','Nolan','180','path','none','1','1','2018-04-25','250','18:00:00');
INSERT into isadb.projections(name,actors,genre,director,duration,image_path, description, hall_id, ct_id,date,price, time) values('Inception','Leonardo','Sci-Fy','Nolan','180','path','none','1','1','2018-04-25','250','17:00:00');
INSERT into isadb.projections(name,actors,genre,director,duration,image_path, description, hall_id, ct_id,date,price, time) values('Inception','Leonardo','Sci-Fy','Nolan','180','path','none','1','1','2018-04-26','250','19:00:00');


INSERT into isadb.ct_projections(cinema_theater_id,projections_id) values('1','1');
INSERT into isadb.ct_projections(cinema_theater_id,projections_id) values('1','2');
INSERT into isadb.ct_projections(cinema_theater_id,projections_id) values('1','3');
INSERT into isadb.ct_projections(cinema_theater_id,projections_id) values('1','4');
INSERT into isadb.ct_projections(cinema_theater_id,projections_id) values('1','5');
INSERT into isadb.halls(name,rows,cols) values('1','10','10');
INSERT into isadb.halls(name,rows,cols) values('2','15','15');
INSERT into isadb.halls(name,rows,cols) values('3','10','10');
INSERT into isadb.halls(name,rows,cols) values('4','10','10');

INSERT into isadb.seats(hall_id,row,col) values('1','2','3');
INSERT into isadb.seats(hall_id,row,col) values('1','1','7');
INSERT into isadb.seats(hall_id,row,col) values('2','7','7');
INSERT into isadb.seats(hall_id,row,col) values('3','5','5');
INSERT into isadb.seats(hall_id,row,col) values('4','4','4');

 INSERT into isadb.seats(hall_id,rows,cols) values('4','4','4');
