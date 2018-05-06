-- we don't know how to generate schema shiro (class Schema) :(
create table roles_permissions
(
	id bigint auto_increment
		primary key,
	role_name varchar(100) null,
	permission varchar(100) null,
	constraint idx_roles_permissions
		unique (role_name, permission)
)
engine=InnoDB
;

create table user_roles
(
	id bigint auto_increment
		primary key,
	username varchar(100) null,
	role_name varchar(100) null,
	constraint idx_user_roles
		unique (username, role_name)
)
engine=InnoDB
;

create table users
(
	id bigint auto_increment
		primary key,
	username varchar(100) null,
	password varchar(100) null,
	password_salt varchar(100) null,
	constraint idx_users_username
		unique (username)
)
engine=InnoDB
;

