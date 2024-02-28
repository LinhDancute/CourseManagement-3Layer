CREATE DATABASE CourseManagement
USE CourseManagement

--NHỮNG BẢNG THÔNG TIN CƠ BẢN
CREATE TABLE Department (
    DepartmentID VARCHAR(255) PRIMARY KEY NOT NULL,
    Name VARCHAR(255) NOT NULL,
	Budget VARCHAR(255) NOT NULL,
	StartDate DATETIME,
	Administrator VARCHAR(255) NOT NULL
);

CREATE TABLE OnlineCourse (
    CourseID VARCHAR(255) PRIMARY KEY NOT NULL,
    URL VARCHAR(255) NOT NULL
);

CREATE TABLE OnsiteCourse (
    CourseID VARCHAR(255) PRIMARY KEY NOT NULL,
    Location VARCHAR(255) NOT NULL,
	Days DATE,
	Time TIME
);

CREATE TABLE OfficeAssignment (
    InstructorID VARCHAR(255) PRIMARY KEY NOT NULL,
    Location VARCHAR(255) NOT NULL,
	Timestamp TIMESTAMP NOT NULL
);

CREATE TABLE Person (
    PersonID VARCHAR(255) PRIMARY KEY NOT NULL,
    LastName VARCHAR(255) NOT NULL,
	FirstName VARCHAR(255) NOT NULL,
	HireDate DATE NOT NULL,
	EnrollmentDate DATE NOT NULL
);

CREATE TABLE StudentGrade (
    EnrollmentID VARCHAR(255) PRIMARY KEY NOT NULL,
    CourseID VARCHAR(255) NOT NULL,
	StudentID VARCHAR(255) NOT NULL,
	Grade VARCHAR(255) NOT NULL
);

CREATE TABLE Course (
    CourseID VARCHAR(255) PRIMARY KEY NOT NULL,
    Title VARCHAR(255) NOT NULL,
	Creadits VARCHAR(255) NOT NULL,
	DepartmentID VARCHAR(255) NOT NULL
);

--CourseInstructor sinh ra từ mqh many-many 
CREATE TABLE CourseInstructor (
    CourseID VARCHAR(255) NOT NULL,
    PersonID VARCHAR(255) NOT NULL,
	PRIMARY KEY (CourseID, PersonID)
);

--Liên kết bảng
ALTER TABLE OnlineCourse ADD CONSTRAINT FK_Course_OnlineCourse FOREIGN KEY (CourseID) REFERENCES Course(CourseID);
ALTER TABLE OnsiteCourse ADD CONSTRAINT FK_Course_OnsiteCourse FOREIGN KEY (CourseID) REFERENCES Course(CourseID);
ALTER TABLE OfficeAssignment ADD CONSTRAINT FK_Person_OfficeAssignment FOREIGN KEY (InstructorID) REFERENCES Person(PersonID);

--relationship many-many Course-Person thông qua CourseInstructor
ALTER TABLE CourseInstructor ADD CONSTRAINT FK_CourseID_CourseInstructor FOREIGN KEY (CourseID) REFERENCES Course(CourseID);
ALTER TABLE CourseInstructor ADD CONSTRAINT FK_PersonID_CourseInstructor FOREIGN KEY (PersonID) REFERENCES Person(PersonID);

--relationship many-one Person-StudentGrade
ALTER TABLE StudentGrade ADD CONSTRAINT FK_Person_StudentGrade FOREIGN KEY (StudentID) REFERENCES Person(PersonID);

--relationship many-one Course-StudentGrade
ALTER TABLE StudentGrade ADD CONSTRAINT FK_Course_StudentGrade FOREIGN KEY (CourseID) REFERENCES Course(CourseID);

--relationship many-one Department-Course
ALTER TABLE Course ADD CONSTRAINT FK_Department_Course FOREIGN KEY (DepartmentID) REFERENCES Department(DepartmentID);


--INSERT DATA
INSERT INTO [Department] ([DepartmentID], [Name], [Budget], [StartDate], [Administrator])
VALUES
('CNTT', 'Cong nghe thong tin', 350000, '2017-09-01 00:00:00', 2),
('KT', 'Ke toan', 120000, '2010-09-01 00:00:00', 6),
('QTKD', 'Quan tri kinh doanh', 200000, '2020-09-01 00:00:00', 4),
('SPA', 'Su pham anh', 250000, '2015-09-01 00:00:00', 3);
