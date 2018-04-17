insert into isadb.users(first_name, last_name,password_user,email,city,phone,enabled, role,user_tier,first_login) values('admin', 'admin','admin','admin@gmail.com','novi sad','021', TRUE, 'ADMIN','BRONZE',true);
insert into isadb.users(first_name, last_name,password_user,email,city,phone,enabled,role,user_tier,first_login) values('jefimija', 'jefimija','jefimija','isamejl811@gmail.com','jefimija','jefimija', TRUE,'GUEST','BRONZE',true);
insert into isadb.users(first_name, last_name,password_user,email,city,phone,enabled, role,user_tier,first_login) values('isidora', 'isidora','isidora','isidora@gmail.com','novi sad','021',TRUE,'ADMINFZ','BRONZE',true);
insert into isadb.users(first_name, last_name,password_user,email,city,phone,enabled, role,user_tier,first_login) values('njari', 'njari','njari','njari@gmail.com','novi sad','021',TRUE,'GUEST','BRONZE',true);
INSERT INTO friends(user_one_id,user_two_id,status) VALUES (1,2,1);
INSERT INTO friends(user_one_id,user_two_id,status) VALUES (2,3,1);
INSERT INTO friends(user_one_id,user_two_id,status) VALUES (1,3,1);
INSERT INTO friends(user_one_id,user_two_id,status) VALUES (3,4,1);

INSERT INTO ct(name,address,description,type,ambient) VALUES ('Arena Cineplex','Bulevar Mihajla Pupina 3, Novi Sad','A short description that is absolutely  pointless','CINEMA','3');
INSERT INTO ct(name,address,description,type,ambient) VALUES ('CineStar','Sentandrejski Put 11, Novi Sad','A short description that is absolutely  pointless','CINEMA','4');

INSERT INTO isadb.merchandise(user_id, name_merchandise,description,price_merchandise,image_merchandise) VALUES (1,'badge','starwars badge',5,'/images/jediresize.jpg');
INSERT INTO isadb.merchandise(user_id, name_merchandise,description,price_merchandise,image_merchandise) VALUES (1,'cup','frozen cup',8,'/images/frozen.jpg');
INSERT INTO isadb.ads(name_ad,description,image_ad,user_id,price_ad,date_end_of_bids, ad_bid_status) VALUES ('badge','starwars badge','/images/jediresize.jpg',1,4,'2019-11-11','WAITING');
INSERT INTO isadb.ads(name_ad,description,image_ad,user_id,price_ad,date_end_of_bids,ad_bid_status) VALUES ('cup','frozen cup','/images/frozen.jpg',4,5,'2018-11-11','WAITING');
INSERT into isadb.ct(name,address,description,type,ambient) values('nameC','addressC','descriptionC','CINEMA','0');
INSERT into isadb.ct(name,address,description,type,ambient) values('nameT','addressT','descriptionT','THEATER','0');
INSERT INTO isadb.bids(id_ad,id_guest_bid,price_bid,ad_bid_status) VALUES (1,3,3,'WAITING');
INSERT INTO isadb.bids(id_ad,id_guest_bid,price_bid,ad_bid_status) VALUES (1,2,5,'WAITING');
INSERT INTO isadb.bids(id_ad,id_guest_bid,price_bid,ad_bid_status) VALUES (2,2,25,'WAITING');
INSERT INTO isadb.notifications(id_receiver, topic, message) VALUES (1,'Bid notification','Your bid has been rejected.');
INSERT INTO isadb.notifications(id_receiver, topic, message) VALUES (1,'Ad notification','Your ad has been approved.');
INSERT INTO isadb.reservations_merchandise(user_id,merchandise_id) VALUES (1,1);

