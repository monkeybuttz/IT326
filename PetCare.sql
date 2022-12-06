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

INSERT INTO user
VALUES(1, "bob", "bob1", "pass1", "bob@email.com","111",0);

INSERT INTO user
VALUES(2, "john", "john1", "pass2", "john@email.com","222",1);

INSERT INTO message
VALUES (1, "Hi there groomer", 1, 2);

INSERT INTO message
VALUES (2, "Hello there user", 2, 1);

INSERT INTO message
VALUES (3, "I want my pet groomed", 1, 2);

INSERT INTO message
VALUES (4, "Ok come see me", 2, 1);

INSERT INTO pet(petID,ownerID, name, breed, notes)
VALUES(1,1,"jojo","dutch shepherd","Friendly and active. Is trained.");

INSERT INTO groomingappointment (aptID, groomerID, petID, date,
location, notes, favorited)
VALUES(1,2,1,"10/10/10","il", "bringing my pet", 1);

INSERT INTO image (id,aid)
VALUES(2,1);
