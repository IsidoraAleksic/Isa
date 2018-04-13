


insert into isadb.ct(name,address,description,type,ambient) values('nameC','addressC','descriptionC','CINEMA','0');
insert into isadb.ct(name,address,description,type,ambient) values('nameT','addressT','descriptionT','THEATER','0');

INSERT INTO users(first_name,last_name,password_user,email,city,phone,role,enabled) VALUES ('isidora','isidora','123','isi@gmail.com','novisad','1234','GUEST',TRUE );
INSERT INTO users(first_name,last_name,password_user,email,city,phone,role,enabled) VALUES ('doda','doda','123','doda@gmail.com','novisad','1234','ADMIN',TRUE );
insert into isadb.users(first_name, last_name,password_user, email,city,phone,enabled, role  ) values('admin', 'admin','admin','admin@gmail.com','novi sad','021', TRUE, 'ADMIN' );
insert into isadb.users(first_name, last_name,password_user, email,city,phone,enabled,role  ) values('jefimija', 'jefimija','jefimija','isamejl811@gmail.com','jefimija','jefimija', TRUE, 'GUEST' );

INSERT INTO friends(user_one_id,user_two_id,status) VALUES (1,2,1);
INSERT INTO friends(user_one_id,user_two_id,status) VALUES (2,3,1);
INSERT INTO friends(user_one_id,user_two_id,status) VALUES (1,3,1);
INSERT INTO friends(user_one_id,user_two_id,status) VALUES (3,4,1);