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
    phoneNUM INT DEFAULT NULL,
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
    image BLOB,
    PRIMARY KEY (petID),
    FOREIGN KEY (ownerID) REFERENCES User(userID)
);

CREATE TABLE GroomingAppointment(
	appointmentID INT auto_increment,
	groomerID INT,
    petID INT,
    ownerID INT,
    date DATE,
    location VARCHAR(50),
    notes LONGTEXT,
    image BLOB,
    favGroom BOOL,
    PRIMARY KEY (appointmentID),
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
	id INT,
    image BLOB,
    PRIMARY KEY (id)
);


