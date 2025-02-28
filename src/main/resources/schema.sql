-- 创建数据库
CREATE DATABASE IF NOT EXISTS my_user;

-- 使用数据库
USE my_user;

-- 创建表
CREATE TABLE IF NOT EXISTS data_user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role INT,
    status INT,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    index index_username (username),
    index index_email (email)
);