CREATE TABLE Department (
    DepartmentID VARCHAR(255) PRIMARY KEY NOT NULL,
    Name VARCHAR(255) NOT NULL,
	Budget VARCHAR(255) NOT NULL,
	StartDate DATETIME,
	Administrator VARCHAR(255) NOT NULL
);

CREATE TABLE OnlineCourse (
    CourseID INT PRIMARY KEY NOT NULL,
    URL VARCHAR(255) NOT NULL
);

CREATE TABLE OnsiteCourse (
    CourseID INT PRIMARY KEY NOT NULL,
    Location VARCHAR(255) NOT NULL,
	Days DATE,
	Time TIME
);

CREATE TABLE OfficeAssignment (
    InstructorID INT PRIMARY KEY NOT NULL,
    Location VARCHAR(255) NOT NULL,
	Timestamp TIMESTAMP NOT NULL
);

CREATE TABLE Person (
    PersonID INT AUTO_INCREMENT PRIMARY KEY,
    LastName VARCHAR(255) NOT NULL,
	FirstName VARCHAR(255) NOT NULL,
	HireDate DATE NOT NULL,
	EnrollmentDate DATE NOT NULL
);

CREATE TABLE StudentGrade (
    EnrollmentID INT AUTO_INCREMENT PRIMARY KEY,
    CourseID INT NOT NULL,
	StudentID INT NOT NULL,
	Grade DOUBLE NOT NULL
);

CREATE TABLE Course (
    CourseID INT AUTO_INCREMENT PRIMARY KEY,
    Title VARCHAR(255) NOT NULL,
	Creadits VARCHAR(255) NOT NULL,
	DepartmentID VARCHAR(255) NOT NULL
);

 
CREATE TABLE CourseInstructor (
    CourseID INT NOT NULL,
    PersonID INT NOT NULL,
	PRIMARY KEY (CourseID, PersonID)
);


ALTER TABLE OnlineCourse ADD CONSTRAINT FK_Course_OnlineCourse FOREIGN KEY (CourseID) REFERENCES Course(CourseID);
ALTER TABLE OnsiteCourse ADD CONSTRAINT FK_Course_OnsiteCourse FOREIGN KEY (CourseID) REFERENCES Course(CourseID);
ALTER TABLE OfficeAssignment ADD CONSTRAINT FK_Person_OfficeAssignment FOREIGN KEY (InstructorID) REFERENCES Person(PersonID);


ALTER TABLE CourseInstructor ADD CONSTRAINT FK_CourseID_CourseInstructor FOREIGN KEY (CourseID) REFERENCES Course(CourseID);
ALTER TABLE CourseInstructor ADD CONSTRAINT FK_PersonID_CourseInstructor FOREIGN KEY (PersonID) REFERENCES Person(PersonID);


ALTER TABLE StudentGrade ADD CONSTRAINT FK_Person_StudentGrade FOREIGN KEY (StudentID) REFERENCES Person(PersonID);

ALTER TABLE StudentGrade ADD CONSTRAINT FK_Course_StudentGrade FOREIGN KEY (CourseID) REFERENCES Course(CourseID);

ALTER TABLE Course ADD CONSTRAINT FK_Department_Course FOREIGN KEY (DepartmentID) REFERENCES Department(DepartmentID);


INSERT INTO Department (DepartmentID, Name, Budget, StartDate, Administrator)
VALUES
('CNTT', 'Cong nghe thong tin', 350000, '2017-09-01 00:00:00', 2),
('KT', 'Ke toan', 120000, '2010-09-01 00:00:00', 6),
('QTKD', 'Quan tri kinh doanh', 200000, '2020-09-01 00:00:00', 4),
('SPA', 'Su pham anh', 250000, '2015-09-01 00:00:00', 3);

INSERT INTO person (Firstname, Lastname, HireDate, EnrollmentDate) 
VALUES 
('Nguyen', 'Van An', NULL, '2017-09-01 13:00:00'), 
('Hoang', 'Quoc Binh', NULL, '2017-09-01 07:00:00'), 
('Luu', 'Van Hoa', NULL, '2017-09-01 07:20:00'), 
('Nguyen', 'Thi Huong', NULL, '2017-09-01 08:00:00'), 
('Nguyen', 'Hoang Long', NULL, '2017-09-01 14:00:00');
