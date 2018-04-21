insert into users(first_name, last_name,password_user,email,city,phone,enabled, role,user_tier,first_login,points) values('Jefimija', 'Zivkovic','123','isamejl811@gmail.com','Novi Sad','021', TRUE, 'GUEST','BRONZE',true,'10');
insert into users(first_name, last_name,password_user,email,city,phone,enabled,role,user_tier,first_login,points) values('Isidora', 'Aleksic','1234','isidora@gmail.com','Novi Sad','021', TRUE,'GUEST','BRONZE',true,'10');

INSERT INTO friends(user_one_id,user_two_id,status) VALUES (1,2,1);