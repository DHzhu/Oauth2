/*创建数据库*/
create database oauth2;

/*创建用户*/
create user 'oauth2'@'%' identified by 'oauth2';

/*赋权*/
grant all privileges on oauth2.* to oauth2;

/*更新权限*/
flush privileges;

/*建表*/
drop table if exists oauth2_client;
drop table if exists oauth2_user;

create table oauth2_user (
  	id bigint not null auto_increment,
	userName varchar(100) not null,
	password varchar(100) not null
) charset=utf8 ENGINE=InnoDB;
create unique index idx_oauth2_user_username on oauth2_user(userName);

create table oauth2_client (
  	id bigint not null auto_increment,
	clientName varchar(100) not null comment '客户端名称',
	clientId varchar(50) not null comment '客户端id',
	clientkey varchar(50) not null comment '客户端安全key',
  constraint pk_oauth2_client primary key(id)
) charset=utf8 ENGINE=InnoDB;
create index idx_oauth2_client_client_id on oauth2_client(clientId);

