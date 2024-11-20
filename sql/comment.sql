USE prj20241114;

CREATE TABLE comment
(
    board_id  INT,
    member_id VARCHAR(20)  NOT NULL,
    comment   VARCHAR(500) Not NULL,
    inserted  DATETIME     NOT NULL DEFAULT NOW()
);

SELECT *
FROM comment;