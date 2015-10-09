drop table FOOSBALL.FOOSBALLUSER;

CREATE TABLE FOOSBALL.FOOSBALLUSER(
id bigint auto_increment primary key, 
username varchar(50),
firstname varchar(50),
lastname varchar(50),
password varchar(1064),
level double,
ROLE VARCHAR(40),
team_id BIGINT);

drop table FOOSBALL.TEAM;

CREATE TABLE FOOSBALL.TEAM(
id bigint auto_increment primary key, 
teamname varchar(50) not null,
numberofgames int,
cumulativepoint int,
rank int);

ALTER TABLE FOOSBALL.FOOSBALLUSER ADD FOREIGN KEY (team_id) REFERENCES FOOSBALL.TEAM(id);

drop table FOOSBALL.USER_RATINGS;

CREATE TABLE FOOSBALL.USER_RATINGS(
RATING_USER_ID BIGINT primary key,
RATED_USER_ID BIGINT primary key,
RATING INT);

ALTER TABLE FOOSBALL.USER_RATINGS
ADD FOREIGN KEY (RATING_USER_ID)
REFERENCES FOOSBALL.FOOSBALLUSER(id);

ALTER TABLE FOOSBALL.USER_RATINGS
ADD FOREIGN KEY (RATED_USER_ID)
REFERENCES FOOSBALL.FOOSBALLUSER(id);

drop table FOOSBALL.MATCH;

CREATE TABLE FOOSBALL.MATCH(
id bigint auto_increment primary key,
firstTeam_id bigint,
secondTeam_id bigint,
firstScore int,
secondScore int);
