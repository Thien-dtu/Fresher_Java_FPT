create database CANDIDATE;
use CANDIDATE;

create table CANDIDATE(
	candidateId varchar(45) not null primary key,
    fullName varchar(255),
    birthday date,
    phone varchar(45),
    email varchar(45),
    candidateType int,
	certificatedId varchar(45),
    expInYear int,
    proSkill varchar(255),
    graduationDate date,
    graduationRank varchar(255),
    education varchar(255),
    majors varchar(255),
    semester varchar(255),
    universityName varchar(255)
);
create table CHUNGCHI(
	certificatedId varchar(45) not null primary key,
    certificateName varchar(255),
    certificateRank varchar(255),
    certificatedDate date
);
create table cer_candi(
	candidateId varchar(45),
    certificatedId varchar(45),
    primary key(candidateId, certificatedId),
    foreign key(candidateId) references candidate(candidateId),
    foreign key(certificatedId) references CHUNGCHI(certificatedId)
);