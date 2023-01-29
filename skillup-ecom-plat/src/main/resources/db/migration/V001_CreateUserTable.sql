USE skillup;
create table user(
    user_id varchar(36) not null ,
    user_name varchar(100) not null unique ,
    password varchar(100) not null ,
    primary key (user_id)
)ENGINE = InnoDB CHARSET = utf8mb4;

INSERT INTO user(user_id, user_name, password) VALUES ("1", "qingxiao", "123");