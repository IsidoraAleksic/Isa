INSERT INTO users(first_name,last_name,password_user,email,city,phone,role,enabled) VALUES ('isidora','isidora','123','isi@gmail.com','novisad','1234','GUEST',TRUE );
INSERT INTO users(first_name,last_name,password_user,email,city,phone,role,enabled) VALUES ('doda','doda','123','doda@gmail.com','novisad','1234','ADMIN',TRUE );
INSERT INTO users(first_name, last_name,password_user, email,city,phone,enabled, role  ) VALUES ('admin', 'admin','admin','admin@gmail.com','novi sad','021', TRUE, 'ADMIN' );
INSERT INTO users(first_name, last_name,password_user, email,city,phone,enabled,role  ) VALUES ('jefimija', 'jefimija','jefimija','isamejl811@gmail.com','jefimija','jefimija', TRUE, 'GUEST' );

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
