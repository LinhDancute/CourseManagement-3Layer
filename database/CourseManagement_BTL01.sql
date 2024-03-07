-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 07, 2024 at 04:42 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `coursemanagement`
--

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `CourseID` int(11) NOT NULL,
  `Title` varchar(255) NOT NULL,
  `Creadits` varchar(255) NOT NULL,
  `DepartmentID` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `courseinstructor`
--

CREATE TABLE `courseinstructor` (
  `CourseID` int(11) NOT NULL,
  `PersonID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `DepartmentID` varchar(255) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Budget` varchar(255) NOT NULL,
  `StartDate` datetime DEFAULT NULL,
  `Administrator` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`DepartmentID`, `Name`, `Budget`, `StartDate`, `Administrator`) VALUES
('CNTT', 'Cong nghe thong tin', '350000', '2017-09-01 00:00:00', '2'),
('KT', 'Ke toan', '120000', '2010-09-01 00:00:00', '6'),
('QTKD', 'Quan tri kinh doanh', '200000', '2020-09-01 00:00:00', '4'),
('SPA', 'Su pham anh', '250000', '2015-09-01 00:00:00', '3');

-- --------------------------------------------------------

--
-- Table structure for table `officeassignment`
--

CREATE TABLE `officeassignment` (
  `InstructorID` int(11) NOT NULL,
  `Location` varchar(255) NOT NULL,
  `Timestamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `onlinecourse`
--

CREATE TABLE `onlinecourse` (
  `CourseID` int(11) NOT NULL,
  `URL` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `onsitecourse`
--

CREATE TABLE `onsitecourse` (
  `CourseID` int(11) NOT NULL,
  `Location` varchar(255) NOT NULL,
  `Days` date DEFAULT NULL,
  `Time` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

CREATE TABLE `person` (
  `PersonID` int(11) NOT NULL,
  `LastName` varchar(255) NOT NULL,
  `FirstName` varchar(255) NOT NULL,
  `HireDate` date DEFAULT NULL,
  `EnrollmentDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `person`
--

INSERT INTO `person` (`PersonID`, `LastName`, `FirstName`, `HireDate`, `EnrollmentDate`) VALUES
(1, 'Van An', 'Nguyen', NULL, '2017-09-01'),
(2, 'Quoc Binh', 'Hoang', NULL, '2017-09-01'),
(3, 'Van Hoan', 'Luu', NULL, '2017-09-01'),
(4, 'Thi Huong', 'Nguyen', NULL, '2017-09-01'),
(5, 'Hoang Long', 'Nguyen', NULL, '2017-09-01'),
(6, 'Thi Diem My', 'Vo', NULL, '2024-03-01'),
(8, 'Hoang Minh', 'Nguyen ', NULL, '2024-02-20'),
(9, 'Dao Linh Dan', 'Nguyen', NULL, '2024-03-01'),
(11, 'To Uyen', 'Quach', NULL, '2024-01-16'),
(12, 'Hoai Quan', 'Nguyen', '2023-02-02', NULL),
(13, 'Nam Phuong', 'Hoang', '2023-01-20', NULL),
(14, 'Anh Thinh', 'Luu', '2023-10-25', NULL),
(15, 'Thi Huong', 'Nguyen', '2024-02-01', NULL),
(16, 'Vu Hoa', 'Luong', '2024-05-23', NULL),
(18, 'Nam Phuong', 'Hoang', NULL, '2024-03-04'),
(19, 'Thieu Luong', 'Nam', '2024-03-03', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `studentgrade`
--

CREATE TABLE `studentgrade` (
  `EnrollmentID` int(11) NOT NULL,
  `CourseID` int(11) NOT NULL,
  `StudentID` int(11) NOT NULL,
  `Grade` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`CourseID`),
  ADD KEY `FK_Department_Course` (`DepartmentID`);

--
-- Indexes for table `courseinstructor`
--
ALTER TABLE `courseinstructor`
  ADD PRIMARY KEY (`CourseID`,`PersonID`),
  ADD KEY `FK_PersonID_CourseInstructor` (`PersonID`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`DepartmentID`);

--
-- Indexes for table `officeassignment`
--
ALTER TABLE `officeassignment`
  ADD PRIMARY KEY (`InstructorID`);

--
-- Indexes for table `onlinecourse`
--
ALTER TABLE `onlinecourse`
  ADD PRIMARY KEY (`CourseID`);

--
-- Indexes for table `onsitecourse`
--
ALTER TABLE `onsitecourse`
  ADD PRIMARY KEY (`CourseID`);

--
-- Indexes for table `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`PersonID`);

--
-- Indexes for table `studentgrade`
--
ALTER TABLE `studentgrade`
  ADD PRIMARY KEY (`EnrollmentID`),
  ADD KEY `FK_Person_StudentGrade` (`StudentID`),
  ADD KEY `FK_Course_StudentGrade` (`CourseID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `course`
--
ALTER TABLE `course`
  MODIFY `CourseID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `person`
--
ALTER TABLE `person`
  MODIFY `PersonID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `studentgrade`
--
ALTER TABLE `studentgrade`
  MODIFY `EnrollmentID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `course`
--
ALTER TABLE `course`
  ADD CONSTRAINT `FK_Department_Course` FOREIGN KEY (`DepartmentID`) REFERENCES `department` (`DepartmentID`);

--
-- Constraints for table `courseinstructor`
--
ALTER TABLE `courseinstructor`
  ADD CONSTRAINT `FK_CourseID_CourseInstructor` FOREIGN KEY (`CourseID`) REFERENCES `course` (`CourseID`),
  ADD CONSTRAINT `FK_PersonID_CourseInstructor` FOREIGN KEY (`PersonID`) REFERENCES `person` (`PersonID`);

--
-- Constraints for table `officeassignment`
--
ALTER TABLE `officeassignment`
  ADD CONSTRAINT `FK_Person_OfficeAssignment` FOREIGN KEY (`InstructorID`) REFERENCES `person` (`PersonID`);

--
-- Constraints for table `onlinecourse`
--
ALTER TABLE `onlinecourse`
  ADD CONSTRAINT `FK_Course_OnlineCourse` FOREIGN KEY (`CourseID`) REFERENCES `course` (`CourseID`);

--
-- Constraints for table `onsitecourse`
--
ALTER TABLE `onsitecourse`
  ADD CONSTRAINT `FK_Course_OnsiteCourse` FOREIGN KEY (`CourseID`) REFERENCES `course` (`CourseID`);

--
-- Constraints for table `studentgrade`
--
ALTER TABLE `studentgrade`
  ADD CONSTRAINT `FK_Course_StudentGrade` FOREIGN KEY (`CourseID`) REFERENCES `course` (`CourseID`),
  ADD CONSTRAINT `FK_Person_StudentGrade` FOREIGN KEY (`StudentID`) REFERENCES `person` (`PersonID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
