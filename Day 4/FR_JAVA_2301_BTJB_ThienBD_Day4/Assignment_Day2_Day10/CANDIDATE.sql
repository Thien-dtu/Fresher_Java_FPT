create database CANDIDATE;
use CANDIDATE;

create table CANDIDATE(
	candidateId varchar(45) not null primary key,
    fullName varchar(255),
    birthday date,
    phone varchar(45),
    email varchar(45),
    candidateType int,
--certificatedId varchar(45),
    expInYear int,
    proSkill varchar(255),
    graduationDate date,
    graduationRank varchar(255),
    education varchar(255),
    majors varchar(255),
    semester varchar(255),
    universityName varchar(255)
);

Create table CERTIFICATED(
certificatedId INT not null primary KEY IDENTITY(1,1),
certificatedName varchar(50),
certificatedRank varchar(30),
certificatedDate date,
candidateId varchar(45) foreign key references CANDIDATE(candidateId)
);

SELECT * FROM dbo.CANDIDATE

SELECT * FROM CERTIFICATED