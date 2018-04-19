create table user_tier_scale(id Long NOT NULL AUTO_INCREMENT, points INT,user_tier ENUM('BRONZE','SILVER','GOLDEN'),PRIMARY KEY(id));
insert into isadb.users(first_name, last_name,password_user,email,city,phone,enabled, role,user_tier,first_login) values('admin', 'admin','admin','admin@gmail.com','novi sad','021', TRUE, 'ADMIN','BRONZE',true);
insert into isadb.users(first_name, last_name,password_user,email,city,phone,enabled,role,user_tier,first_login) values('jefimija', 'jefimija','jefimija','isamejl811@gmail.com','jefimija','jefimija', TRUE,'GUEST','BRONZE',true);
insert into isadb.users(first_name, last_name,password_user,email,city,phone,enabled,role,user_tier,first_login) values('doda', 'doda','doda','doda@gmail.com','doda','doda', TRUE,'GUEST','BRONZE',true);
insert into isadb.users(first_name, last_name,password_user,email,city,phone,enabled, role,user_tier,first_login) values('isidora', 'isidora','isidora','isidora@gmail.com','novi sad','021',TRUE,'ADMINFZ','BRONZE',true);
insert into isadb.users(first_name, last_name,password_user,email,city,phone,enabled, role,user_tier,first_login) values('njari', 'njari','njari','njari@gmail.com','novi sad','021',TRUE,'GUEST','BRONZE',true);
insert into user_tier_scale(points,user_tier) values (11,'BRONZE');
insert into user_tier_scale(points,user_tier) values (20,'SILVER');
insert into user_tier_scale(points,user_tier) values (30,'GOLDEN');

INSERT INTO ads(name_ad,description,image_ad,user_id,price_ad,date_end_of_bids, ad_bid_status) VALUES ('badge','starwars badge','/images/jediresize.jpg',5,4,'2019-11-11','WAITING');
INSERT INTO ads(name_ad,description,image_ad,user_id,price_ad,date_end_of_bids,ad_bid_status) VALUES ('cup','frozen cup','/images/frozen.jpg',2,5,'2018-11-11','WAITING');

CREATE TABLE reservations_merchandise(id Long NOT NULL AUTO_INCREMENT, user_id long,merchandise_id LONG,version LONG,PRIMARY KEY(id));
#INSERT INTO reservations_merchandise(user_id,merchandise_id,version) VALUES (2,1,0);


INSERT INTO isadb.merchandise(user_id, name_merchandise,description,price_merchandise,image_merchandise) VALUES (1,'badge','starwars badge',5,'/images/jediresize.jpg');
INSERT INTO isadb.merchandise(user_id, name_merchandise,description,price_merchandise,image_merchandise) VALUES (1,'cup','frozen cup',8,'/images/frozen.jpg');

INSERT INTO isadb.bids(id_ad,id_guest_bid,price_bid,ad_bid_status) VALUES (1,3,3,'WAITING');
INSERT INTO isadb.bids(id_ad,id_guest_bid,price_bid,ad_bid_status) VALUES (1,2,5,'WAITING');
INSERT INTO isadb.bids(id_ad,id_guest_bid,price_bid,ad_bid_status) VALUES (2,2,25,'WAITING');
INSERT INTO isadb.notifications(id_receiver, topic, message) VALUES (1,'Bid notification','Your bid has been rejected.');
INSERT INTO isadb.notifications(id_receiver, topic, message) VALUES (1,'Ad notification','Your ad has been approved.');



