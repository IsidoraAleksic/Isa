INSERT INTO isadb.merchandise(user_id, name_merchandise,description,price_merchandise,image_merchandise) VALUES (1,'badge','starwars badge',5,'/images/jediresize.jpg');
INSERT INTO isadb.merchandise(user_id, name_merchandise,description,price_merchandise,image_merchandise) VALUES (1,'cup','frozen cup',8,'/images/frozen.jpg');
INSERT INTO isadb.users(first_name,last_name,password_user,email,username,city,phone,role,enabled) VALUES ('isidora','isidora','123','isi@gmail.com','isidoraa','novisad','1234','GUEST',TRUE);
INSERT INTO isadb.users(first_name,last_name,password_user,email,username,city,phone,role,enabled) VALUES ('doda','doda','123','doda@gmail.com','doda','novisad','1234','ADMINFZ',TRUE);
INSERT into isadb.users(first_name, last_name,password_user,username, email,city,phone,role,enabled  ) values('admin', 'admin','admin','admin','admin','admin','admin','ADMIN', FALSE );
INSERT INTO isadb.ads(name_ad,description,image_ad,user_id,price_ad,date_end_of_bids, ad_bid_status) VALUES ('badge','starwars badge','/images/jediresize.jpg',1,4,'2019-11-11','WAITING');
INSERT INTO isadb.ads(name_ad,description,image_ad,user_id,price_ad,date_end_of_bids,ad_bid_status) VALUES ('cup','frozen cup','/images/frozen.jpg',1,5,'2018-11-11','WAITING');
INSERT into isadb.ct(name,address,description,type,ambient) values('nameC','addressC','descriptionC','CINEMA','0');
INSERT into isadb.ct(name,address,description,type,ambient) values('nameT','addressT','descriptionT','THEATER','0');
INSERT INTO isadb.bids(id_ad,id_guest_bid,price_bid,ad_bid_status) VALUES (1,1,3,'WAITING');
INSERT INTO isadb.bids(id_ad,id_guest_bid,price_bid,ad_bid_status) VALUES (1,1,5,'WAITING');
INSERT INTO isadb.bids(id_ad,id_guest_bid,price_bid,ad_bid_status) VALUES (2,1,25,'WAITING');
INSERT INTO isadb.notifications(id_receiver, topic, message) VALUES (1,'Bid notification','Your bid has been rejected.');
INSERT INTO isadb.notifications(id_receiver, topic, message) VALUES (1,'Ad notification','Your ad has been approved.');
INSERT INTO isadb.reservations_merchandise(user_id,merchandise_id) VALUES (1,1);

