create database if not exists eazyschool;

use eazyschool;

CREATE TABLE IF NOT EXISTS `contact_msg`
(
    `contact_id` int AUTO_INCREMENT PRIMARY KEY,
    `name`       varchar(100) NOT NULL,
    `mobile_num` varchar(10)  NOT NULL,
    `email`      varchar(100) NOT NULL,
    `subject`    varchar(100) NOT NULL,
    `message`    varchar(500) NOT NULL,
    `status`     varchar(10)  NOT NULL,
    `created_at` TIMESTAMP    NOT NULL,
    `created_by` varchar(50)  NOT NULL,
    `updated_at` TIMESTAMP   DEFAULT NULL,
    `updated_by` varchar(50) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `holidays`
(
    `day`        varchar(20)  NOT NULL,
    `reason`     varchar(100) NOT NULL,
    `type`       varchar(20)  NOT NULL,
    `created_at` TIMESTAMP    NOT NULL,
    `created_by` varchar(50)  NOT NULL,
    `updated_at` TIMESTAMP   DEFAULT NULL,
    `updated_by` varchar(50) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `roles`
(
    `role_id`    int auto_increment primary key,
    `role_name`  varchar(100) NOT NULL,
    `created_at` TIMESTAMP    NOT NULL,
    `created_by` varchar(50)  NOT NULL,
    `updated_at` TIMESTAMP   DEFAULT NULL,
    `updated_by` varchar(50) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `address`
(
    `address_id` int auto_increment primary key,
    `address1`   varchar(200) NOT NULL,
    `address2`   varchar(200) DEFAULT NULL,
    `city`       varchar(50)  NOT NULL,
    `state`      varchar(50)  NOT NULL,
    `zip_code`   int          NOT NULL,
    `created_at` TIMESTAMP    NOT NULL,
    `created_by` varchar(50)  NOT NULL,
    `updated_at` TIMESTAMP    DEFAULT NULL,
    `updated_by` varchar(50)  DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `person`
(
    `person_id`     int auto_increment primary key,
    `name`          varchar(100) NOT NULL,
    `mobile_number` varchar(10)  NOT NULL,
    `email`         varchar(100) NOT NULL,
    `password`      varchar(200) NOT NULL,
    `role_id`       int          NOT NULL,
    `address_id`    int          NULL,
    `created_at`    TIMESTAMP    NOT NULL,
    `created_by`    varchar(50)  NOT NULL,
    `updated_at`    TIMESTAMP   DEFAULT NULL,
    `updated_by`    varchar(50) DEFAULT NULL,
    foreign key (role_id) references roles (role_id),
    foreign key (address_id) references address (address_id)
);

CREATE TABLE IF NOT EXISTS `class`
(
    `class_id`   int AUTO_INCREMENT PRIMARY KEY,
    `name`       varchar(100) NOT NULL,
    `created_at` TIMESTAMP    NOT NULL,
    `created_by` varchar(50)  NOT NULL,
    `updated_at` TIMESTAMP   DEFAULT NULL,
    `updated_by` varchar(50) DEFAULT NULL
);

ALTER TABLE `person`
    ADD COLUMN `class_id` int NULL AFTER `address_id`,
    ADD CONSTRAINT `FK_CLASS_CLASS_ID` FOREIGN KEY (`class_id`)
        REFERENCES `class` (`class_id`);


