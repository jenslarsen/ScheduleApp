/*
ALTER TABLE user MODIFY COLUMN userId INT AUTO_INCREMENT;
ALTER table user AUTO_INCREMENT = 100;

INSERT INTO user (userName, password, active, createBy, createDate, lastUpdate, lastUpdatedBy) 
VALUES ("test", "test", 1, "Jens", "2018-06-03 08:02:00", "2018-06-03", "Jens");

INSERT INTO user (userName, password, active, createBy, createDate, lastUpdate, lastUpdatedBy) 
VALUES ("Jens", "panda", 1, "Jens", "2018-06-04 08:02:00", "2018-06-03", "Jens"); 

INSERT INTO user (userName, password, active, createBy, createDate, lastUpdate, lastUpdatedBy) 
VALUES ("Jamie", "hippo", 1, "Jamie", "2018-06-05 08:02:00", "2018-06-03", "Jamie"); 
 
 */
 
 SELECT * FROM user