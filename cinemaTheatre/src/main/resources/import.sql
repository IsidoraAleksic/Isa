
INSERT INTO merchandise(id_ad, name_merchandise,description,price_merchandise,image_merchandise) VALUES (1,'badge','starwars badge',5,NULL);
INSERT INTO merchandise(id_ad, name_merchandise,description,price_merchandise,image_merchandise) VALUES (2,'shirt','ready player 1 shirt',8,NULL);
INSERT INTO users(first_name,last_name,password_user,email,username,city,phone,role) VALUES ('isidora','isidora','123','isi@gmail.com','isidoraa','novisad','1234','GUEST');
INSERT INTO users(first_name,last_name,password_user,email,username,city,phone,role) VALUES ('doda','doda','123','doda@gmail.com','doda','novisad','1234','ADMIN');
INSERT INTO ads(merchandise_id,user_id,price_ad,date_end_of_bids) VALUES (1,1,5,'2018-07-06');
INSERT INTO ads(merchandise_id,user_id,price_ad,date_end_of_bids) VALUES (1,1,15,'2019-11-11');
INSERT INTO bids(id_ad,id_guest_bid,price_bid) VALUES (1,1,3);
INSERT INTO bids(id_ad,id_guest_bid,price_bid) VALUES (1,1,5);
INSERT INTO bids(id_ad,id_guest_bid,price_bid) VALUES (2,1,25);
insert into isadb.users(first_name, last_name,password_user,username, email,city,phone,enabled  ) values('admin', 'admin','admin','admin','admin','admin','admin', FALSE );
insert into isadb.ct(name,address,description,type,ambient) values('nameC','addressC','descriptionC','CINEMA','0');
insert into isadb.ct(name,address,description,type,ambient) values('nameT','addressT','descriptionT','THEATER','0');
