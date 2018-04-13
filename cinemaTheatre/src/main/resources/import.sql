INSERT INTO users(first_name,last_name,password_user,email,city,phone,role,enabled) VALUES ('isidora','isidora','123','isi@gmail.com','novisad','1234','GUEST',TRUE );
INSERT INTO users(first_name,last_name,password_user,email,city,phone,role,enabled) VALUES ('doda','doda','123','doda@gmail.com','novisad','1234','ADMIN',TRUE );
insert into isadb.users(first_name, last_name,password_user, email,city,phone,enabled, role  ) values('admin', 'admin','admin','admin@gmail.com','novi sad','021', TRUE, 'ADMIN' );
insert into isadb.users(first_name, last_name,password_user, email,city,phone,enabled,role  ) values('jefimija', 'jefimija','jefimija','isamejl811@gmail.com','jefimija','jefimija', TRUE, 'GUEST' );

INSERT INTO friends(user_one_id,user_two_id,status) VALUES (1,2,1);
INSERT INTO friends(user_one_id,user_two_id,status) VALUES (2,3,1);
INSERT INTO friends(user_one_id,user_two_id,status) VALUES (1,3,1);
INSERT INTO friends(user_one_id,user_two_id,status) VALUES (3,4,1);

INSERT INTO merchandise(id_ad, name_merchandise,description,price_merchandise,image_merchandise) VALUES (1,'badge','starwars badge',5,NULL);
INSERT INTO merchandise(id_ad, name_merchandise,description,price_merchandise,image_merchandise) VALUES (2,'shirt','ready player 1 shirt',8,NULL);
INSERT INTO ads(merchandise_id,user_id,price_ad,date_end_of_bids) VALUES (1,1,5,'2018-07-06');
INSERT INTO ads(merchandise_id,user_id,price_ad,date_end_of_bids) VALUES (1,1,15,'2019-11-11');
INSERT INTO bids(id_ad,id_guest_bid,price_bid) VALUES (1,1,3);
INSERT INTO bids(id_ad,id_guest_bid,price_bid) VALUES (1,1,5);
INSERT INTO bids(id_ad,id_guest_bid,price_bid) VALUES (2,1,25);
INSERT INTO ct(name,address,description,type,ambient) VALUES ('Arena Cineplex','Bulevar Mihajla Pupina 3, Novi Sad','A short description that is absolutely  pointless','CINEMA','3');
INSERT INTO ct(name,address,description,type,ambient) VALUES ('CineStar','Sentandrejski Put 11, Novi Sad','A short description that is absolutely  pointless','CINEMA','4');
