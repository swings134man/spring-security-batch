insert into user (username, password, nickname, activated) values ('admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 1);
insert into user (username, password, nickname, activated) values ('manager', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'manager', 1);
insert into user (username, password, nickname, activated) values ('user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 1);

insert into role (role_name) values ('ROLE_USER');
insert into role (role_name) values ('ROLE_MANAGER');
insert into role (role_name) values ('ROLE_ADMIN');

insert into user_role (user_id, role_name) values (1, 'ROLE_USER');
insert into user_role (user_id, role_name) values (1, 'ROLE_MANAGER');
insert into user_role (user_id, role_name) values (1, 'ROLE_ADMIN');

insert into user_role (user_id, role_name) values (2, 'ROLE_USER');
insert into user_role (user_id, role_name) values (2, 'ROLE_MANAGER');

insert into user_role (user_id, role_name) values (3, 'ROLE_USER');
