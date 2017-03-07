/*创建数据库*/
create database oauth2;

/*创建用户*/
create user 'oauth2'@'%' identified by 'oauth2';

/*赋权*/
grant all privileges on oauth2.* to oauth2;

/*更新权限*/
flush privileges;

/*建表*/
create table oauth_user(
	id bigint not null auto_increment,
	userName varchar(100) not null,
	password varchar(100) not null,
	salt varchar(50) not null,
	primary key(id)
);

create table oauth_client(
	id bigint not null auto_increment,
	clientName varchar(100) not null comment '客户端名称',
	clientId varchar(50) not null comment '客户端id',
	clientkey varchar(50) not null comment '客户端安全key',
	primary key(id)
);