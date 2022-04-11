create database Shows;

use Shows;

CREATE TABLE `Movie` (
  `id` int(11) NOT NULL auto_increment,
  `title` varchar(45) NOT NULL,
  `genre` varchar(45) NOT NULL,
  `rate` double NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `rateNum` int NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO Movie VALUES (null,'Early Man','Action/Adventure, Animated, Comedy',6.4,'Set at the dawn of time, when prehistoric creatures and woolly mammoths roamed the earth, Early Man tells the story of Dug, along with sidekick Hognob as they unite his tribe against a mighty enemy Lord Nooth and his Bronze Age City to save their home.',10);
INSERT INTO Movie VALUES (null,'Samson','Action,Drama',4.6,'After losing the love of his life to a cruel Philistine prince, a young Hebrew with Supernatural strength defends his people, sacrificing everything to avenge his love, his people, and his God.',10);
INSERT INTO Movie VALUES (null,'Peter Rabbit','Action/Adventure, Comedy, Family',6.2,'Feature adaptation of Beatrix Potters classic tale of a rebellious rabbit trying to sneak into a farmers vegetable garden.',10);
INSERT INTO Movie VALUES (null,'The 15:17 to Paris','Drama, Historical Film, Suspense Thriller',5.0,'Three Americans discover a terrorist plot aboard a train while in France.',10);
INSERT INTO Movie VALUES (null,'Winchester','Horror, Sci-Fi/Fantasy',4.3,'Ensconced in her sprawling California mansion, eccentric firearm heiress Sarah Winchester believes she is haunted by the souls of people killed by the Winchester repeating rifle.',10);
INSERT INTO Movie VALUES (null,'Maze Runner: The Death Cure','Action/Adventure, Sci-Fi/Fantasy',6.9,'Young hero Thomas embarks on a mission to find a cure for a deadly disease known as the Flare.',10);
INSERT INTO Movie VALUES (null,'12 Strong','Drama, War', 7.0,'12 Strong tells the story of the first Special Forces team deployed to Afghanistan after 9/11; under the leadership of a new captain, the team must work with an Afghan warlord to take down for the Taliban.',10);
INSERT INTO Movie VALUES (null,'Den of Thieves','Action,Adventure,Thriller',7.1,'A gritty crime saga which follows the lives of an elite unit of the LA County Sheriffs Dept. and the states most successful bank robbery crew as the outlaws plan a seemingly impossible heist on the Federal Reserve Bank.',10);

