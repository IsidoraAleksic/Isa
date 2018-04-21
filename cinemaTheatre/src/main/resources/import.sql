insert into users(first_name, last_name,password_user,email,city,phone,enabled, role,user_tier,first_login,points) values('Jefimija', 'Zivkovic','123','isamejl811@gmail.com','Novi Sad','021', TRUE, 'GUEST','BRONZE',true,'10');
insert into users(first_name, last_name,password_user,email,city,phone,enabled,role,user_tier,first_login,points) values('Isidora', 'Aleksic','1234','isidora@gmail.com','Novi Sad','021', TRUE,'ADMINFZ','BRONZE',true,'10');
insert into users(first_name, last_name,password_user,email,city,phone,enabled, role,user_tier,first_login,points) values('Daniel', 'Njari','12345','daniel@gmail.com','Novi Sad','021',TRUE,'ADMINCT','BRONZE',true,'5');
insert into users(first_name, last_name,password_user,email,city,phone,enabled, role,user_tier,first_login,points) values('Pera', 'Markovic','123','zivkovic.jefimijaa@gmail.com','Beograd','011',TRUE,'ADMINCT','BRONZE',true,'14');
insert into users(first_name, last_name,password_user,email,city,phone,enabled, role,user_tier,first_login,points) values('Admin', 'Sistema','123','admin@gmail.com','Beograd','011',TRUE,'ADMIN','BRONZE',true,'14');

INSERT INTO ct(name,address,description,type,ambient,user_id) VALUES ('Arena Cineplex','Bulevar Mihajla Pupina 3, Novi Sad','Bioskop u Novom Sadu','CINEMA','4','3');
INSERT INTO ct(name,address,description,type,ambient,user_id) VALUES ('CineStar1','Sentandrejski Put 11, Novi Sad','Bioskop u Bigu','CINEMA','3',NULL);
INSERT into ct(name,address,description,type,ambient,user_id) values('Pozoriste mladih','Novi Sad','Pozoriste u Novom Sadu','THEATER','5', NULL );
INSERT INTO ct(name,address,description,type,ambient,user_id) VALUES ('Sprsko Narodno Pozoriste','Pozorisni Trg 1, Novi Sad','Pozoriste u centru','THEATER','1', NULL );

INSERT into projections(name,actors,genre,director,duration,image_path, description, hall_id, ct_id,date,price, time) values('Inception','Leonardo','Sci-Fy','Nolan','180','path','none','1','1','2018-04-19','250','09:50:00');
INSERT into projections(name,actors,genre,director,duration,image_path, description, hall_id, ct_id,date,price, time) values('Interstellar','Alright','Sci-Fy','Nolan','180','path','none','1','1','2018-05-25','250','19:00:00');
INSERT into projections(name,actors,genre,director,duration,image_path, description, hall_id, ct_id,date,price, time) values('Inception','Leonardo','Sci-Fy','Nolan','180','path','none','2','1','2018-04-19','250','18:00:00');
INSERT into projections(name,actors,genre,director,duration,image_path, description, hall_id, ct_id,date,price, time) values('Inception','Leonardo','Sci-Fy','Nolan','180','path','none','2','1','2018-04-19','250','17:00:00');
INSERT into projections(name,actors,genre,director,duration,image_path, description, hall_id, ct_id,date,price, time) values('Inception','Leonardo','Sci-Fy','Nolan','180','path','none','2','1','2018-04-26','250','19:00:00');


