create table ROLES (rolename varchar(255) not null, primary key (rolename));
create table USERS (enabled boolean, expiryDateAccount date, expiryDateCredentials date, lockedAccount boolean, email varchar(255), fullname varchar(255), password varchar(255), username varchar(255) not null, primary key (username));
