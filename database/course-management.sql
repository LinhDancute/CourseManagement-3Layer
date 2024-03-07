-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th3 06, 2024 lúc 06:36 PM
-- Phiên bản máy phục vụ: 10.4.27-MariaDB
-- Phiên bản PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `course-management`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `course`
--

CREATE TABLE `course` (
  `CourseID` int(11) NOT NULL,
  `Title` varchar(255) NOT NULL,
  `Credits` varchar(255) NOT NULL,
  `DepartmentID` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `course`
--

INSERT INTO `course` (`CourseID`, `Title`, `Credits`, `DepartmentID`) VALUES
(1, 'PT-TK Giải thuật', '3', 'CNTT'),
(2, 'Phát triển phần mềm mã nguồn mở', '4', 'CNTT'),
(3, 'Quản trị tài chính', '3', 'KT'),
(4, 'Mô hình kinh doanh', '2', 'KT'),
(5, 'Tâm lý học', '5', 'SPA'),
(6, 'Giáo dục tiếng anh', '1', 'SPA'),
(7, 'Kinh tế vĩ mô', '2', 'QTKD'),
(8, 'Pháp luật kinh doanh', '2', 'QTKD'),
(9, 'bbbbb', '4', 'SPA');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `courseinstructor`
--

CREATE TABLE `courseinstructor` (
  `CourseID` int(11) NOT NULL,
  `PersonID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `department`
--

CREATE TABLE `department` (
  `DepartmentID` varchar(255) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Budget` varchar(255) NOT NULL,
  `StartDate` datetime DEFAULT NULL,
  `Administrator` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `department`
--

INSERT INTO `department` (`DepartmentID`, `Name`, `Budget`, `StartDate`, `Administrator`) VALUES
('CNTT', 'Công nghệ thông tin', '350000', '2017-09-01 00:00:00', '2'),
('KT', 'Kế toán', '120000', '2010-09-01 00:00:00', '6'),
('QTKD', 'Quản trị kinh doanh', '200000', '2020-09-01 00:00:00', '4'),
('SPA', 'Sư phạm anh', '250000', '2015-09-01 00:00:00', '3');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `officeassignment`
--

CREATE TABLE `officeassignment` (
  `InstructorID` int(11) NOT NULL,
  `Location` varchar(255) NOT NULL,
  `Timestamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `onlinecourse`
--

CREATE TABLE `onlinecourse` (
  `CourseID` int(11) NOT NULL,
  `URL` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `onlinecourse`
--

INSERT INTO `onlinecourse` (`CourseID`, `URL`) VALUES
(1, 'https://www.studocu.com/vn/document/truong-dai-hoc-bach-khoa-dai-hoc-da-nang/phan-tich-thiet-ke-giai-thuat/giao-trinh-phan-tich-va-thiet-ke-giai-thuat/20861865'),
(2, 'https://drive.google.com/drive/folders/1ldQRmJ6Bioi2fB59weT64GlAxI5CWdgf'),
(5, 'https://tailieuhust.com/tai-lieu-mon-tam-ly-hoc-ung-dung-hust/'),
(8, 'https://cuuduongthancong.com/s/luat-kinh-doanh');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `onsitecourse`
--

CREATE TABLE `onsitecourse` (
  `CourseID` int(11) NOT NULL,
  `Location` varchar(255) NOT NULL,
  `Days` date DEFAULT NULL,
  `Time` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `onsitecourse`
--

INSERT INTO `onsitecourse` (`CourseID`, `Location`, `Days`, `Time`) VALUES
(3, 'Tầng 7, CharmVit Tower, số 117 Trần Duy Hưng, Trung Hòa, Cầu Giấy, Hà Nội', '2022-01-30', '13:00:00'),
(4, '121 Pasteur, Phường Võ Thị Sáu, Quận 3, TP. Hồ Chí Minh', '2022-01-11', '15:30:00'),
(6, '1 Lương Yên, Bạch Đằng, Hà Nội', '2024-03-07', ''),
(7, '110 Phạm Văn Đồng, Phường 3, Gò Vấp, Thành phố Hồ Chí Minh', '2021-12-16', '21:00:00'),
(9, 'a', '2024-03-07', '12:00:00');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `person`
--

CREATE TABLE `person` (
  `PersonID` int(11) NOT NULL,
  `LastName` varchar(255) NOT NULL,
  `FirstName` varchar(255) NOT NULL,
  `HireDate` date NOT NULL,
  `EnrollmentDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `person`
--

INSERT INTO `person` (`PersonID`, `LastName`, `FirstName`, `HireDate`, `EnrollmentDate`) VALUES
(1, 'Van An', 'Nguyen', '0000-00-00', '2017-09-01'),
(2, 'Quoc Binh', 'Hoang', '0000-00-00', '2017-09-01'),
(3, 'Van Hoa', 'Luu', '0000-00-00', '2017-09-01'),
(4, 'Thi Huong', 'Nguyen', '0000-00-00', '2017-09-01'),
(5, 'Hoang Long', 'Nguyen', '0000-00-00', '2017-09-01');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `studentgrade`
--

CREATE TABLE `studentgrade` (
  `EnrollmentID` int(11) NOT NULL,
  `CourseID` int(11) NOT NULL,
  `StudentID` int(11) NOT NULL,
  `Grade` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`CourseID`),
  ADD KEY `FK_Department_Course` (`DepartmentID`);

--
-- Chỉ mục cho bảng `courseinstructor`
--
ALTER TABLE `courseinstructor`
  ADD PRIMARY KEY (`CourseID`,`PersonID`),
  ADD KEY `FK_PersonID_CourseInstructor` (`PersonID`);

--
-- Chỉ mục cho bảng `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`DepartmentID`);

--
-- Chỉ mục cho bảng `officeassignment`
--
ALTER TABLE `officeassignment`
  ADD PRIMARY KEY (`InstructorID`);

--
-- Chỉ mục cho bảng `onlinecourse`
--
ALTER TABLE `onlinecourse`
  ADD PRIMARY KEY (`CourseID`);

--
-- Chỉ mục cho bảng `onsitecourse`
--
ALTER TABLE `onsitecourse`
  ADD PRIMARY KEY (`CourseID`);

--
-- Chỉ mục cho bảng `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`PersonID`);

--
-- Chỉ mục cho bảng `studentgrade`
--
ALTER TABLE `studentgrade`
  ADD PRIMARY KEY (`EnrollmentID`),
  ADD KEY `FK_Person_StudentGrade` (`StudentID`),
  ADD KEY `FK_Course_StudentGrade` (`CourseID`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `course`
--
ALTER TABLE `course`
  MODIFY `CourseID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT cho bảng `person`
--
ALTER TABLE `person`
  MODIFY `PersonID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `studentgrade`
--
ALTER TABLE `studentgrade`
  MODIFY `EnrollmentID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `course`
--
ALTER TABLE `course`
  ADD CONSTRAINT `FK_Department_Course` FOREIGN KEY (`DepartmentID`) REFERENCES `department` (`DepartmentID`);

--
-- Các ràng buộc cho bảng `courseinstructor`
--
ALTER TABLE `courseinstructor`
  ADD CONSTRAINT `FK_CourseID_CourseInstructor` FOREIGN KEY (`CourseID`) REFERENCES `course` (`CourseID`),
  ADD CONSTRAINT `FK_PersonID_CourseInstructor` FOREIGN KEY (`PersonID`) REFERENCES `person` (`PersonID`);

--
-- Các ràng buộc cho bảng `officeassignment`
--
ALTER TABLE `officeassignment`
  ADD CONSTRAINT `FK_Person_OfficeAssignment` FOREIGN KEY (`InstructorID`) REFERENCES `person` (`PersonID`);

--
-- Các ràng buộc cho bảng `onlinecourse`
--
ALTER TABLE `onlinecourse`
  ADD CONSTRAINT `FK_Course_OnlineCourse` FOREIGN KEY (`CourseID`) REFERENCES `course` (`CourseID`);

--
-- Các ràng buộc cho bảng `onsitecourse`
--
ALTER TABLE `onsitecourse`
  ADD CONSTRAINT `FK_Course_OnsiteCourse` FOREIGN KEY (`CourseID`) REFERENCES `course` (`CourseID`);

--
-- Các ràng buộc cho bảng `studentgrade`
--
ALTER TABLE `studentgrade`
  ADD CONSTRAINT `FK_Course_StudentGrade` FOREIGN KEY (`CourseID`) REFERENCES `course` (`CourseID`),
  ADD CONSTRAINT `FK_Person_StudentGrade` FOREIGN KEY (`StudentID`) REFERENCES `person` (`PersonID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
