create table ROLES (rolename varchar(255) not null, primary key (rolename));
create table USERS (enabled boolean, expiryDateAccount date, expiryDateCredentials date, lockedAccount boolean, email varchar(255), fullname varchar(255), password varchar(255), username varchar(255) not null, primary key (username));
create table USERS_HAS_ROLES (User_username varchar(255) not null, roleSet_rolename varchar(255) not null, primary key (User_username, roleSet_rolename));
alter table if exists USERS_HAS_ROLES add constraint FK40jy793cg5kbts0087cm4engd foreign key (roleSet_rolename) references ROLES;
alter table if exists USERS_HAS_ROLES add constraint FKfy7x7il32rr6kyeos1va1fvh5 foreign key (User_username) references USERS;
