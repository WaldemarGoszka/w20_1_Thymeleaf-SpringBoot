insert into zajavka_user (user_id, user_name, email, password, name, active)
values (1, 'admin', 'admin@zajavka.pl','$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', 'Admin', true);
insert into zajavka_user (user_id, user_name, email, password, name, active)
values (2, 'user1', 'user1@zajavka.pl','$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', 'User1' , true);

insert into zajavka_role (role_id, role) values (1, 'ADMIN'), (2, 'USER');

insert into zajavka_user_role (user_id, role_id) values (1, 1), (1,2);
insert into zajavka_user_role (user_id, role_id) values (2,2);