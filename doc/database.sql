/*创建数据库*/
create database oauth2;

/*创建用户*/
create user 'oauth2'@'%' identified by 'oauth2';

/*赋权*/
grant all privileges on oauth2.* to oauth2;

/*更新权限*/
flush privileges;

/*建表*/