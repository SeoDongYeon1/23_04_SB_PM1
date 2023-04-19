DROP DATABASE IF EXISTS `SB_PM_04`;
CREATE DATABASE `SB_PM_04`;
USE `SB_PM_04`;

# `article` 테이블 생성

CREATE TABLE article(
id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
regDate DATETIME NOT NULL,
updateDate DATETIME NOT NULL,
title VARCHAR(300) NOT NULL,
`body` TEXT NOT NULL
);

# `member` 테이블 생성

CREATE TABLE `member`(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    loginId CHAR(100) NOT NULL,
    loginPw CHAR(100) NOT NULL,
    authLevel SMALLINT(2) UNSIGNED DEFAULT 3 COMMENT '권한 레벨(3=일반, 7=관리자)',
    `name` CHAR(100) NOT NULL,
    nickname CHAR(100) NOT NULL,
    cellphoneNum CHAR(100) NOT NULL,
    email CHAR(100) NOT NULL,
    delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '탈퇴 여부 (0=탈퇴 전, 1= 탈퇴 후)',
    delDate DATETIME COMMENT '탈퇴 날짜'
);


ALTER TABLE article CONVERT TO CHARSET UTF8;
ALTER TABLE `member` CONVERT TO CHARSET UTF8;
# `article` 테스트 데이터 생성 구문

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
#memberId = 1,
title = '제목1',
`body` ='내용1';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
#memberId = 2,
title = '제목2',
`body` ='내용2';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
#memberId = 3,
title = '제목3',
`body` ='내용3';

# `member` 테스트 데이터 생성 구문

INSERT INTO `member` 
SET regDate = NOW(), 
updateDate = NOW(), 
loginId = 'asd', 
loginPw = 'asd',
`name` = 'asd',
nickname = 'asd',
cellphoneNum = '010-1234-1234',
email = 'asd@gmail.com';

INSERT INTO `member` 
SET regDate = NOW(), 
updateDate = NOW(), 
loginId = 'qwe', 
loginPw = 'qwe',
`name` = 'qwe',
nickname = 'qwe',
cellphoneNum = '010-4567-4567',
email = 'qwe@gmail.com';

INSERT INTO `member` 
SET regDate = NOW(), 
updateDate = NOW(), 
loginId = 'zxc', 
loginPw = 'zxc',
`name` = 'zxc',
nickname = 'zxc',
cellphoneNum = '010-7890-7890',
email = 'zxc@gmail.com';

# 조인 셀렉트
SELECT a.id, a.regDate, a.title, a.body, a.memberId, m.name
FROM article a
INNER JOIN `member` m
ON a.memberId = m.id
ORDER BY a.id DESC LIMIT 0, 10; 

SELECT * FROM article ORDER BY id DESC;

SELECT * FROM `member` ORDER BY id DESC;

SELECT LAST_INSERT_ID()

SELECT * 
FROM `member` 
WHERE id = 1;
