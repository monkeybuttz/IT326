DROP DATABASE IF EXISTS PetCare;
CREATE DATABASE PetCare;
USE PetCare;

SET NAMES utf8;
set character_set_client = utf8mb4;

CREATE TABLE User(
	userID INT AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    username VARCHAR(45) NOT NULL,
    password VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL,
    phoneNUM LONG DEFAULT NULL,
    isGroomer BOOL NOT NULL,
    PRIMARY KEY (userID),
    UNIQUE KEY (username),
    UNIQUE KEY (password),
    UNIQUE KEY (email)
);

CREATE TABLE Pet(
	petID INT AUTO_INCREMENT,
    ownerID INT,
    name VARCHAR(45),
    breed VARCHAR(45),
    notes LONGTEXT,
    image LONGBLOB,
    PRIMARY KEY (petID),
    FOREIGN KEY (ownerID) REFERENCES User(userID)
);

CREATE TABLE GroomingAppointment(
	aptID INT auto_increment,
	groomerID INT,
    petID INT,
    date VARCHAR(10),
    location VARCHAR(50),
    notes LONGTEXT,
    image BLOB,
    favorited BOOL,
    PRIMARY KEY (aptID),
    FOREIGN KEY (groomerID) REFERENCES User(userID),
    FOREIGN KEY (petID) REFERENCES Pet(petID)
);


CREATE TABLE Message(
	messageID INT AUTO_INCREMENT,
    post longtext,
    senderID INT,
    receiverID INT,
    PRIMARY KEY (messageID),
    FOREIGN KEY (senderID) REFERENCES User(userID),
    FOREIGN KEY (receiverID) REFERENCES User(userID)
);

CREATE TABLE Image(
	id INT auto_increment,
    image longblob,
    aid INT,
    PRIMARY KEY (id),
    FOREIGN KEY (aid) REFERENCES GroomingAppointment(aptID)
);