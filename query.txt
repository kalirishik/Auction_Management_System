create database auction_management_system;
use auction_management_system;
show databases;
CREATE TABLE adminpanel (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(50) NOT NULL,
    pwd VARCHAR(20) NOT NULL
);
show tables;
select * from adminpanel;

CREATE TABLE seller (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sellerName VARCHAR(100) NOT NULL,
    phoneno VARCHAR(15) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    pwd VARCHAR(100) NOT NULL,
    location VARCHAR(100)
);

select * from seller;

CREATE TABLE buyer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    buyerName VARCHAR(100) NOT NULL,
    phoneno VARCHAR(15) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    pwd VARCHAR(100) NOT NULL,
    location VARCHAR(100)
);

select * from buyer;
drop table buyer;
delete from buyer where id=1;
alter table buyer Rename column id to buyerId;

alter table seller Rename column id to sellerId;

CREATE TABLE products (
    productId INT AUTO_INCREMENT PRIMARY KEY,
    productName VARCHAR(100) NOT NULL,
    sellerId INT,
    buyerId INT,
    categoryId INT,
    quantity INT,
    price INT 
);

drop table products;
select * from products;

create table category(categoryId int, categoryName varchar(50));
select * from category;

INSERT INTO category (categoryId, categoryName) VALUES
(1001, 'Bikes'),
(1002, 'Cars'),
(1003, 'Watches'),
(1004, 'Shoes'),
(1005, 'Jewellery'),
(1006, 'Paintings'),
(1007, 'Electronics'),
(1008, 'Swords');

alter table products add column soldDate varchar(50);

alter table products add column productStatus varchar(50);
ALTER TABLE products MODIFY COLUMN productStatus VARCHAR(50) DEFAULT 'available';





