CREATE TABLE `categories` (
  `catogoryId` int PRIMARY KEY,
  `category_name` String
);

CREATE TABLE `products` (
  `productId` int PRIMARY KEY,
  `name` varchar(200),
  `quantity` int,
  `unitPrice` float,
  `image` varchar(255),
  `description` text,
  `discount` float,
  `enteredDate` datetime,
  `status` int,
  `catogoryId` int
);

CREATE TABLE `orderDetails` (
  `orderDetailId` int PRIMARY KEY,
  `orderId` int,
  `productId` int,
  `quantity` int,
  `unitPrice` float
);

CREATE TABLE `orders` (
  `orderId` int PRIMARY KEY,
  `oderDate` datetime,
  `customerId` int,
  `amount` float,
  `status` int
);

CREATE TABLE `customers` (
  `customerId` int PRIMARY KEY,
  `name` varchar(200),
  `email` varchar(255),
  `password` varchar(50),
  `phone` varchar(50),
  `registerdDate` datetime,
  `status` int
);

CREATE TABLE `accounts` (
  `username` varchar(30) PRIMARY KEY,
  `password` varchar(50)
);

ALTER TABLE `orderDetails` ADD FOREIGN KEY (`orderId`) REFERENCES `orders` (`orderId`);

ALTER TABLE `orders` ADD FOREIGN KEY (`customerId`) REFERENCES `customers` (`customerId`);

ALTER TABLE `products` ADD FOREIGN KEY (`catogoryId`) REFERENCES `categories` (`catogoryId`);

ALTER TABLE `orderDetails` ADD FOREIGN KEY (`productId`) REFERENCES `products` (`productId`);
