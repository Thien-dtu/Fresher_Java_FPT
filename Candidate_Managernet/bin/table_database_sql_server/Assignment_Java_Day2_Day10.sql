DROP DATABASE IF EXISTS Assignment_Candidate_Managerment;
CREATE DATABASE Assignment_Candidate_Managerment;
USE Assignment_Candidate_Managerment;

CREATE TABLE Candidate(
Candidate_ID int PRIMARY KEY IDENTITY(1,1),
 Full_Name varchar(50),
 Birth_Day date,
 Phone int,
 Email varchar(50),
 Candidate_Type int check (Candidate_Type between 0 and 2) not null,
--Experience,
 Exp_In_Year int,
 Pro_Skill varchar(50),
 --Fresher
 Graduation_Date date,
 Graduation_Rank varchar(20),
 Eduation varchar(100),
 --Intern
 Majors varchar(50),
 Semester varchar(50),
 University_Name varchar(100),
);

CREATE table Certificated(
Certificated_ID int primary key identity(1,1),
Certificated_Name varchar(50),
Certificated_Rank varchar(30),
Certificated_Date date,
Candidate_ID int foreign key references Candidate(Candidate_ID)
);

select * from Candidate;
select * from Certificated;
