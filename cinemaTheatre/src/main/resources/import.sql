INSERT INTO users(first_name,last_name,password_user,email,city,phone,role,enabled) VALUES ('isidora','isidora','123','isi@gmail.com','novisad','1234','GUEST',TRUE );
INSERT INTO users(first_name,last_name,password_user,email,city,phone,role,enabled) VALUES ('doda','doda','123','doda@gmail.com','novisad','1234','ADMIN',TRUE );
insert into isadb.users(first_name, last_name,password_user, email,city,phone,enabled, role  ) values('admin', 'admin','admin','admin@gmail.com','novi sad','021', TRUE, 'ADMIN' );
insert into isadb.users(first_name, last_name,password_user, email,city,phone,enabled,role  ) values('jefimija', 'jefimija','jefimija','isamejl811@gmail.com','jefimija','jefimija', TRUE, 'GUEST' );

INSERT INTO friends(user_one_id,user_two_id,status) VALUES (1,2,1);
INSERT INTO friends(user_one_id,user_two_id,status) VALUES (2,3,1);
INSERT INTO friends(user_one_id,user_two_id,status) VALUES (1,3,1);
INSERT INTO friends(user_one_id,user_two_id,status) VALUES (3,4,1);

INSERT INTO isadb.merchandise(user_id, name_merchandise,description,price_merchandise,image_merchandise) VALUES (1,'badge','starwars badge',5,'/images/jediresize.jpg');
INSERT INTO isadb.merchandise(user_id, name_merchandise,description,price_merchandise,image_merchandise) VALUES (1,'cup','frozen cup',8,'/images/frozen.jpg');
INSERT INTO isadb.ads(name_ad,description,image_ad,user_id,price_ad,date_end_of_bids, ad_bid_status) VALUES ('badge','starwars badge','/images/jediresize.jpg',1,4,'2019-11-11','WAITING');
INSERT INTO isadb.ads(name_ad,description,image_ad,user_id,price_ad,date_end_of_bids,ad_bid_status) VALUES ('cup','frozen cup','/images/frozen.jpg',1,5,'2018-11-11','WAITING');
INSERT INTO isadb.bids(id_ad,id_guest_bid,price_bid,ad_bid_status) VALUES (1,1,3,'WAITING');
INSERT INTO isadb.bids(id_ad,id_guest_bid,price_bid,ad_bid_status) VALUES (1,1,5,'WAITING');
INSERT INTO isadb.bids(id_ad,id_guest_bid,price_bid,ad_bid_status) VALUES (2,1,25,'WAITING');
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
#
# INSERT into isadb.seats(hall_id,rows,cols) values('4','4','4');
