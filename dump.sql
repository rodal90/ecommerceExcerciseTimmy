-- H2 2.2.224;
;             
CREATE USER IF NOT EXISTS "SAS" SALT '30c4a65c67209351' HASH 'cdd08c2a800c4d6ced257873f1593a14411d3fcb3fc3f8c1726ef9dbe7aabacf' ADMIN;        
CREATE MEMORY TABLE "PUBLIC"."ROLES"(
    "ROLENAME" CHARACTER VARYING(255) NOT NULL
);     
ALTER TABLE "PUBLIC"."ROLES" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_4" PRIMARY KEY("ROLENAME");  
-- 3 +/- SELECT COUNT(*) FROM PUBLIC.ROLES;   
INSERT INTO "PUBLIC"."ROLES" VALUES
('ADMIN'),
('USER'),
('MANAGER');      
CREATE MEMORY TABLE "PUBLIC"."USERS"(
    "ENABLED" BOOLEAN,
    "EXPIRYDATEACCOUNT" DATE,
    "EXPIRYDATECREDENTIALS" DATE,
    "LOCKEDACCOUNT" BOOLEAN,
    "EMAIL" CHARACTER VARYING(255),
    "FULLNAME" CHARACTER VARYING(255),
    "PASSWORD" CHARACTER VARYING(255),
    "USERNAME" CHARACTER VARYING(255) NOT NULL
);        
ALTER TABLE "PUBLIC"."USERS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_4D" PRIMARY KEY("USERNAME"); 
-- 3 +/- SELECT COUNT(*) FROM PUBLIC.USERS;   
INSERT INTO "PUBLIC"."USERS" VALUES
(TRUE, DATE '2024-11-14', DATE '2024-11-14', FALSE, 'ana@gmail.com', 'Ana Perez', 'anaPass', 'ana'),
(TRUE, DATE '2024-11-14', DATE '2024-11-14', FALSE, 'luis@gmail.com', 'luis Gomez', 'luisPass', 'luis'),
(TRUE, DATE '2024-11-14', DATE '2024-11-14', FALSE, 'evano@gmail.com', 'Evano No', 'evanoPass', 'evano');
CREATE MEMORY TABLE "PUBLIC"."USERS_HAS_ROLES"(
    "USER_USERNAME" CHARACTER VARYING(255) NOT NULL,
    "ROLESET_ROLENAME" CHARACTER VARYING(255) NOT NULL
);             
ALTER TABLE "PUBLIC"."USERS_HAS_ROLES" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_9" PRIMARY KEY("USER_USERNAME", "ROLESET_ROLENAME");               
-- 3 +/- SELECT COUNT(*) FROM PUBLIC.USERS_HAS_ROLES;         
INSERT INTO "PUBLIC"."USERS_HAS_ROLES" VALUES
('ana', 'ADMIN'),
('ana', 'USER'),
('evano', 'MANAGER');     
ALTER TABLE "PUBLIC"."USERS_HAS_ROLES" ADD CONSTRAINT "PUBLIC"."FK40JY793CG5KBTS0087CM4ENGD" FOREIGN KEY("ROLESET_ROLENAME") REFERENCES "PUBLIC"."ROLES"("ROLENAME") NOCHECK; 
ALTER TABLE "PUBLIC"."USERS_HAS_ROLES" ADD CONSTRAINT "PUBLIC"."FKFY7X7IL32RR6KYEOS1VA1FVH5" FOREIGN KEY("USER_USERNAME") REFERENCES "PUBLIC"."USERS"("USERNAME") NOCHECK;    
