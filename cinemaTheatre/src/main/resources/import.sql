INSERT INTO isadb.merchandise(user_id, id_ad, name_merchandise,description,price_merchandise,image_merchandise) VALUES (1,1,'badge','starwars badge',5,NULL);
INSERT INTO isadb.merchandise(user_id, id_ad, name_merchandise,description,price_merchandise,image_merchandise) VALUES (1,2,'shirt','ready player 1 shirt',8,NULL);
INSERT INTO isadb.users(first_name,last_name,password_user,email,username,city,phone,role) VALUES ('isidora','isidora','123','isi@gmail.com','isidoraa','novisad','1234','GUEST');
INSERT INTO isadb.users(first_name,last_name,password_user,email,username,city,phone,role) VALUES ('doda','doda','123','doda@gmail.com','doda','novisad','1234','ADMIN');
INSERT INTO isadb.ads(merchandise_id,user_id,price_ad,date_end_of_bids) VALUES (1,1,15,'2019-11-11');
INSERT into isadb.users(first_name, last_name,password_user,username, email,city,phone,enabled  ) values('admin', 'admin','admin','admin','admin','admin','admin', FALSE );
INSERT into isadb.ct(name,address,description,type,ambient) values('nameC','addressC','descriptionC','CINEMA','0');
INSERT into isadb.ct(name,address,description,type,ambient) values('nameT','addressT','descriptionT','THEATER','0');
INSERT INTO isadb.ads(merchandise_id,user_id,price_ad,date_end_of_bids) VALUES (2,1,15,'2019-11-11');
INSERT INTO isadb.bids(id_ad,id_guest_bid,price_bid,ad_bid_status) VALUES (1,1,3,'WAITING');
INSERT INTO isadb.bids(id_ad,id_guest_bid,price_bid,ad_bid_status) VALUES (1,1,5,'WAITING');
INSERT INTO isadb.bids(id_ad,id_guest_bid,price_bid,ad_bid_status) VALUES (2,1,25,'WAITING');
INSERT INTO isadb.notification(id_receiver, topic, message) VALUES (1,'Bid notification','Your bid has been rejected.');
INSERT INTO isadb.notification(id_receiver, topic, message) VALUES (1,'Ad notification','Your ad has been approved.');
INSERT INTO isadb.reservations_merchandise(user_id,merchandise_id) VALUES (1,1);

