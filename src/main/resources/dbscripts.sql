CREATE TABLE FOOSBALL.FOOSBALLUSER
(ID INTEGER NOT NULL AUTOINCREMENT,
FIRSTNAME VARCHAR(40) NOT NULL,
LASTNAME VARCHAR(40) NOT NULL,
USERNAME VARCHAR(40) NOT NULL,
PASSWORD VARCHAR(40) NOT NULL,
LEVEL INTEGER,
ROLE VARCHAR(40),
PRIMARY KEY (ID));

