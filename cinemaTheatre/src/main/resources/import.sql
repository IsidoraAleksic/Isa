drop table merchandise;
drop table ads;
drop table bids;
drop table users;
drop table notification;
CREATE TABLE merchandise(ID int NOT NULL AUTO_INCREMENT,id_ad Long, name_merchandise VARCHAR(30),description VARCHAR(30), price_merchandise Long,image_merchandise VARCHAR(30), PRIMARY KEY (ID));
CREATE TABLE ads(ID int NOT NULL AUTO_INCREMENT, merchandise_id Long, user_id Long,price_ad Long, date_end_of_bids Date,  PRIMARY KEY (ID));
CREATE TABLE bids(ID int NOT NULL AUTO_INCREMENT,id_ad LONG,id_guest_bid Long, price_bid LONG, bid_status ENUM('REJECTED','ACCEPTED','WAITING'), PRIMARY KEY (ID));
CREATE TABLE users(ID int NOT NULL AUTO_INCREMENT,first_name VARCHAR(30),last_name VARCHAR(30),password_user VARCHAR(30),email VARCHAR(30),username VARCHAR(30),city VARCHAR(30),phone VARCHAR(30), role ENUM('ADMIN','ADMINFZ','UNREGISTERED','GUEST'), PRIMARY KEY (ID));
CREATE TABLE notification(ID int NOT NULL AUTO_INCREMENT, id_receiver Long, topic VARCHAR(30), message VARCHAR(30), PRIMARY KEY (ID));

INSERT INTO merchandise(user_id, id_ad, name_merchandise,description,price_merchandise,image_merchandise) VALUES (1,1,'badge','starwars badge',5,NULL);
INSERT INTO merchandise(user_id, id_ad, name_merchandise,description,price_merchandise,image_merchandise) VALUES (1,2,'shirt','ready player 1 shirt',8,NULL);
INSERT INTO users(first_name,last_name,password_user,email,username,city,phone,role) VALUES ('isidora','isidora','123','isi@gmail.com','isidoraa','novisad','1234','GUEST');
INSERT INTO users(first_name,last_name,password_user,email,username,city,phone,role) VALUES ('doda','doda','123','doda@gmail.com','doda','novisad','1234','ADMIN');
INSERT INTO ads(merchandise_id,user_id,price_ad,date_end_of_bids) VALUES (1,1,5,'2018-07-06');
INSERT INTO ads(merchandise_id,user_id,price_ad,date_end_of_bids) VALUES (2,1,15,'2019-11-11');
INSERT INTO bids(id_ad,id_guest_bid,price_bid,bid_status) VALUES (1,1,3,'WAITING');
INSERT INTO bids(id_ad,id_guest_bid,price_bid,bid_status) VALUES (1,1,5,'WAITING');
INSERT INTO bids(id_ad,id_guest_bid,price_bid,bid_status) VALUES (2,1,25,'WAITING');
INSERT INTO notification(id_receiver, topic, message) VALUES (1,'Bid notification','Your bid has been rejected.');
INSERT INTO notification(id_receiver, topic, message) VALUES (1,'Ad notification','Your ad has been approved.');