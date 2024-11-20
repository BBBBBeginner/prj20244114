USE prj20241114;
CREATE TABLE member
(
    id          VARCHAR(20) PRIMARY KEY,
    password    VARCHAR(30) NOT NULL,
    description VARCHAR(1000),
    inserted    DATETIME DEFAULT NOW()
);


ALTER TABLE member
    ADD COLUMN email VARCHAR(300) UNIQUE AFTER id;
DESC member;

SELECT *
FROM member;


INSERT INTO member (id, password, email)
VALUES ('trump', 'password123', 'trump@example.com');