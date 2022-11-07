CREATE SCHEMA IF NOT EXISTS users DEFAULT CHAR SET utf8;

USE users;

CREATE TABLE IF NOT EXISTS tbl_user (
    u_user VARCHAR(50) NOT NULL COMMENT 'Username',
    u_pass VARCHAR(50) NOT NULL COMMENT 'Password',
    u_score INT(50) NOT NULL COMMENT 'Score'
)

ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'User Table';
    
    
select * from tbl_user;
