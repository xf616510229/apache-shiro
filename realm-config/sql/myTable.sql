create table t_permission
(
	id int not null
		primary key,
	permission_name varchar(64) not null,
	status int default '1' null
)
engine=InnoDB
;

create table t_role
(
	id int not null
		primary key,
	role_name varchar(20) not null,
	status int default '1' null comment '状态 0 无效 1 有效'
)
engine=InnoDB
;

create table t_roles_permission
(
	role_id int not null
		primary key,
	permission_id int null
)
engine=InnoDB
;

create table t_user
(
	id int not null
		primary key,
	username varchar(64) not null,
	password varchar(64) not null,
	birthday datetime null
)
engine=InnoDB
;

create table t_user_role
(
	user_id int null,
	role_id int not null,
	remark varchar(128) null
)
engine=InnoDB
;

