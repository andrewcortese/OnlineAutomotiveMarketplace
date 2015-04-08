CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `user_profile` (
  `userId` int(11) NOT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  CONSTRAINT `userId` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO user 
VALUES (1, "Jd123", "UAlbany456");

INSERT INTO user_profile 
VALUES(1, "John", "Doe");

INSERT INTO user
VALUES (2, "Mj987", "UAlbany654");

INSERT INTO user_profile
VALUES (2,"Mary", "Jane");

INSERT INTO user
VALUES (3, "ac", "pw");

INSERT INTO user_profile
VALUES (3, "Andrew", "Cortese");