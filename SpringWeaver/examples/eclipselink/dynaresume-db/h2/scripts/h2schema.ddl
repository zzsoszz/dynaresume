create table T_USER (
        USR_ID_N IDENTITY not null,        
        USR_LOGIN_C varchar(50) not null,
        USR_PASSWORD_C varchar(50) not null,
        primary key (USR_ID_N)
    );