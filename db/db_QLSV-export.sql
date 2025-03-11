-- -------------------------------------------------------------
-- TablePlus 6.3.1(584)
--
-- https://tableplus.com/
--
-- Database: db_QLSV
-- Generation Time: 2025-03-12 01:03:51.2700
-- -------------------------------------------------------------


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


DROP TABLE IF EXISTS `Classes`;
CREATE TABLE `Classes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `className` varchar(255) NOT NULL,
  `department` varchar(255) NOT NULL,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `className` (`className`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `Enrollments`;
CREATE TABLE `Enrollments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `studentId` int DEFAULT NULL,
  `subjectId` int DEFAULT NULL,
  `semester` varchar(10) NOT NULL,
  `year` int NOT NULL,
  `grade` float DEFAULT NULL,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `studentId` (`studentId`),
  KEY `subjectId` (`subjectId`),
  CONSTRAINT `Enrollments_ibfk_1` FOREIGN KEY (`studentId`) REFERENCES `Students` (`id`) ON DELETE CASCADE,
  CONSTRAINT `Enrollments_ibfk_2` FOREIGN KEY (`subjectId`) REFERENCES `Subjects` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `Students`;
CREATE TABLE `Students` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fullName` varchar(255) NOT NULL,
  `birthDay` date NOT NULL,
  `gender` enum('Male','Female','Other') NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `address` text,
  `classId` int DEFAULT NULL,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `classId` (`classId`),
  CONSTRAINT `Students_ibfk_1` FOREIGN KEY (`classId`) REFERENCES `Classes` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `Subjects`;
CREATE TABLE `Subjects` (
  `id` int NOT NULL AUTO_INCREMENT,
  `subjectName` varchar(255) NOT NULL,
  `credit` int NOT NULL,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `subjectName` (`subjectName`),
  CONSTRAINT `Subjects_chk_1` CHECK ((`credit` > 0))
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `Teachers`;
CREATE TABLE `Teachers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fullName` varchar(255) NOT NULL,
  `birthDay` date NOT NULL,
  `gender` enum('Male','Female','Other') NOT NULL,
  `phone` varchar(255) NOT NULL,
  `address` text,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `TeacherSubject`;
CREATE TABLE `TeacherSubject` (
  `id` int NOT NULL AUTO_INCREMENT,
  `teacherId` int NOT NULL,
  `subjectId` int NOT NULL,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `teacherId` (`teacherId`),
  KEY `subjectId` (`subjectId`),
  CONSTRAINT `TeacherSubject_ibfk_1` FOREIGN KEY (`teacherId`) REFERENCES `Teachers` (`id`) ON DELETE CASCADE,
  CONSTRAINT `TeacherSubject_ibfk_2` FOREIGN KEY (`subjectId`) REFERENCES `Subjects` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `Users`;
CREATE TABLE `Users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('Admin','Teacher','Student') NOT NULL DEFAULT 'Student',
  `studentId` int DEFAULT NULL,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `teacherId` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `studentId` (`studentId`),
  UNIQUE KEY `teacherId` (`teacherId`),
  CONSTRAINT `Users_ibfk_1` FOREIGN KEY (`studentId`) REFERENCES `Students` (`id`) ON DELETE SET NULL,
  CONSTRAINT `Users_ibfk_2` FOREIGN KEY (`teacherId`) REFERENCES `Teachers` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Classes` (`id`, `className`, `department`, `createdAt`, `updatedAt`) VALUES
(69, 'IT01', 'Công nghệ thông tin', '2025-03-03 14:51:59', '2025-03-04 14:42:28'),
(70, 'IT02', 'Khoa học máy tính', '2025-03-03 14:51:59', '2025-03-03 14:51:59'),
(71, 'IT03', 'Hệ thống thông tin', '2025-03-03 14:51:59', '2025-03-03 14:51:59'),
(72, 'CS01', 'An toàn thông tin', '2025-03-03 14:51:59', '2025-03-03 14:51:59'),
(73, 'CS02', 'Kỹ thuật phần mềm', '2025-03-03 14:51:59', '2025-03-03 14:51:59'),
(74, 'CS03', 'Khoa học dữ liệu', '2025-03-03 14:51:59', '2025-03-03 14:51:59'),
(75, 'SE01', 'Mạng máy tính', '2025-03-03 14:51:59', '2025-03-03 14:51:59'),
(76, 'SE02', 'Trí tuệ nhân tạo', '2025-03-03 14:51:59', '2025-03-03 14:51:59'),
(77, 'SE03', 'Khoa học máy tính ứng dụng', '2025-03-03 14:51:59', '2025-03-03 14:51:59'),
(78, 'DS01', 'Công nghệ phần mềm', '2025-03-03 14:51:59', '2025-03-03 14:51:59'),
(79, 'DS02', 'Hệ thống nhúng', '2025-03-03 14:51:59', '2025-03-03 14:51:59'),
(80, 'DS03', 'Điện tử viễn thông', '2025-03-03 14:51:59', '2025-03-03 14:51:59');

INSERT INTO `Enrollments` (`id`, `studentId`, `subjectId`, `semester`, `year`, `grade`, `createdAt`, `updatedAt`) VALUES
(1, 37, 4, 'Spring', 2025, -1, '2025-03-07 16:28:52', '2025-03-07 16:28:52'),
(2, 26, 4, 'Spring', 2025, -1, '2025-03-11 02:55:30', '2025-03-11 02:55:30'),
(3, 26, 1, 'Spring', 2025, -1, '2025-03-11 02:59:24', '2025-03-11 02:59:24'),
(4, 18, 4, 'Spring', 2025, -1, '2025-03-11 03:13:20', '2025-03-11 03:13:20'),
(5, 18, 1, 'Spring', 2025, -1, '2025-03-11 03:16:01', '2025-03-11 03:16:01'),
(6, 27, 4, 'Spring', 2025, -1, '2025-03-11 03:16:13', '2025-03-11 03:16:13'),
(7, 38, 4, 'Spring', 2025, -1, '2025-03-11 03:23:04', '2025-03-11 03:23:04'),
(8, 40, 4, 'Spring', 2025, -1, '2025-03-11 03:33:23', '2025-03-11 03:33:23'),
(9, 40, 1, 'Spring', 2025, -1, '2025-03-11 03:33:26', '2025-03-11 03:33:26');

INSERT INTO `Students` (`id`, `fullName`, `birthDay`, `gender`, `phone`, `address`, `classId`, `createdAt`, `updatedAt`) VALUES
(18, 'Vũ Lê Bảo Long', '2025-03-05', 'Male', '123123123', 'Hồ Chí Minh', 75, '2025-03-04 18:06:43', '2025-03-04 19:01:29'),
(21, '12312123123', '2025-03-05', 'Male', '123123123', '123123123', NULL, '2025-03-06 15:43:27', '2025-03-06 15:43:27'),
(23, 'okela', '2025-03-06', 'Male', '0101012031023', 'akakakaka', NULL, '2025-03-06 16:04:17', '2025-03-06 16:04:17'),
(26, 'lần này oke nè', '2025-03-06', 'Male', '9919191919', 'umnè', NULL, '2025-03-06 16:10:15', '2025-03-06 16:10:15'),
(27, 'lan thứ 2', '2025-03-05', 'Male', '123123123', 'okeokeoek', NULL, '2025-03-06 16:27:05', '2025-03-06 16:27:05'),
(28, 'hello', '2025-03-05', 'Male', '123123', 'respect sự tôn trọng', 70, '2025-03-06 16:31:40', '2025-03-06 17:09:03'),
(29, 'okenaf', '2025-03-07', 'Male', '123123123123', 'asdasdasd', NULL, '2025-03-07 15:12:10', '2025-03-07 15:18:13'),
(30, 'umf nef', '2025-03-07', 'Male', '123123123', 'asdasdasd', NULL, '2025-03-07 15:18:57', '2025-03-07 15:18:57'),
(31, 'ùm ùm', '2025-03-07', 'Male', '123123123', 'adâdâđa', 74, '2025-03-07 15:23:07', '2025-03-07 15:26:09'),
(33, 'okokeoek', '2025-03-21', 'Male', '123123123', 'aeaeaeae', 71, '2025-03-07 15:31:40', '2025-03-07 15:31:49'),
(34, 'lalalala', '2025-03-07', 'Male', '123123123gagag', 'gagagag', 76, '2025-03-07 15:32:35', '2025-03-07 15:35:48'),
(35, 'longlonglong', '2025-03-10', 'Male', '213123123', 'adâđa', NULL, '2025-03-07 15:36:20', '2025-03-07 15:36:20'),
(36, 'okeokền longlong', '2025-03-08', 'Male', '123123123', 'adâdâd', 71, '2025-03-07 15:37:13', '2025-03-07 15:37:20'),
(37, 'cũng được', '2025-03-07', 'Male', '123123123', 'ô sờ kê', NULL, '2025-03-07 15:59:44', '2025-03-07 15:59:44'),
(38, 'anh long 11', '2025-03-11', 'Male', '123123123123', 'Hồ Chí Minh', 70, '2025-03-11 03:22:36', '2025-03-11 03:23:19'),
(39, 'anh long 12', '2025-03-11', 'Male', '123123123', 'ho chi minh', 72, '2025-03-11 03:31:42', '2025-03-11 03:31:48'),
(40, 'anh long 14', '2025-03-11', 'Male', '123123123', 'Ho Chi Minh', 71, '2025-03-11 03:33:13', '2025-03-11 03:33:18');

INSERT INTO `Subjects` (`id`, `subjectName`, `credit`, `createdAt`, `updatedAt`) VALUES
(1, 'Lập trình Python', 4, '2025-03-02 14:31:37', '2025-03-02 14:31:37'),
(4, 'Công Nghệ JAVA', 5, '2025-03-05 17:44:44', '2025-03-05 17:44:44');

INSERT INTO `Teachers` (`id`, `fullName`, `birthDay`, `gender`, `phone`, `address`, `createdAt`, `updatedAt`) VALUES
(3, 'gv1', '2025-03-11', 'Male', '123123123', 'ho chi minh', '2025-03-11 15:35:51', '2025-03-11 15:35:51');

INSERT INTO `Users` (`id`, `email`, `password`, `role`, `studentId`, `createdAt`, `updatedAt`, `teacherId`) VALUES
(2, 'long@gmail.com', '$2a$10$3qPSkgFDsSxzFCTogCavveGqH3l15x20Xoge/9dr7IwwuSfpp7Ev6', 'Admin', NULL, '2025-03-03 16:02:44', '2025-03-04 02:48:37', NULL),
(6, 'long1@gmail.com', '$2a$10$bIxbdNyU3k4ZdoaXQiwJHOUAIxVfUdEB2Q7t8OpLEOY1m6zLi/gwK', 'Student', 18, '2025-03-03 16:07:57', '2025-03-05 17:18:16', NULL),
(7, 'long2@gmail.com', '$2a$10$tV/UIHlh4BnVP9n6qKYKYe.TqfRS.3RVQaLnKOqV.R71YpzhK/S.K', 'Student', 26, '2025-03-03 16:10:05', '2025-03-06 16:10:15', NULL),
(8, 'long3@gmail.com', '$2a$10$ro7zVqHrY3a.7w/ujfVTOunPy7CEyqcl9WaShlsgI9JJo4ljXhZpi', 'Student', 27, '2025-03-03 16:12:31', '2025-03-06 16:27:05', NULL),
(9, 'long4@gmail.com', '$2a$10$tXF193S430/F/Uj58eMVz.yvipItlhr5HIdM8sT9A2h66Ya64dFve', 'Student', 28, '2025-03-03 17:32:19', '2025-03-06 16:31:40', NULL),
(10, 'long5@gmail.com', '$2a$10$zDjzRqTIP43b19BVwSLEAuTHK/5B5MMjlL8ZUCcm/S.VA5tcqW0x2', 'Student', 31, '2025-03-05 15:20:18', '2025-03-07 15:23:07', NULL),
(11, 'long6@gmail.com', '$2a$10$kXwPjBvXPO/NdOsj5FVAS.iRtzSdQ0RFK9PEeJ3WgOZjKJ/pRJikO', 'Student', 33, '2025-03-05 17:46:03', '2025-03-07 15:31:40', NULL),
(12, 'long7@gmail.com', '$2a$10$kPwWnIdzthkphIVpYHB0uOzsqvu5wb9qDLsXIVxmAQxOxRAtHAQ8C', 'Student', 34, '2025-03-07 15:32:17', '2025-03-07 15:32:35', NULL),
(13, 'long8@gmail.com', '$2a$10$TTciQwUKNvyPRS4JdU3aTuCWLdl7pYDSmrDwiB1u8ZVwoH168Z.iq', 'Student', 35, '2025-03-07 15:35:59', '2025-03-07 15:36:20', NULL),
(14, 'long9@gmail.com', '$2a$10$fPmwSOUhvFsEDNtV0ZBKEurDXFSVFBy3rK2rnyb7.aGXiplML9nWq', 'Student', 36, '2025-03-07 15:36:50', '2025-03-07 15:37:13', NULL),
(15, 'long10@gmail.com', '$2a$10$Hkr3QbDRUbqglhqfYDTGZOt/EtTVmN5ChOsNXPj5dLTzWqyUve97q', 'Student', 37, '2025-03-07 15:59:24', '2025-03-07 15:59:44', NULL),
(16, 'long11@gmail.com', '$2a$10$.z.1vX95jSmzyPrAQwfG/u9uwjHGtfpplASP7T4CSebUoBvnXeaJ6', 'Student', 38, '2025-03-11 03:17:21', '2025-03-11 03:22:36', NULL),
(17, 'long12@gmail.com', '$2a$10$Q/etkfC2yAo.aGFS9V3Ziuz8W4F5Zp/orTGEVlC2ZklnAHQs2fzRu', 'Student', 39, '2025-03-11 03:29:23', '2025-03-11 03:31:42', NULL),
(18, 'long14@gmail.com', '$2a$10$hiuFwm.RdHVSWYfN5FqClevMPFE.YpA5E16Dtp0MdtsVaUc99nXgG', 'Student', 40, '2025-03-11 03:32:54', '2025-03-11 03:33:13', NULL),
(20, 'gv1@gmail.com', '$2a$10$xt9Sm6mT/5PTJZ/G7rGMB.pPDUeTHSn0f9Ck3u1/lhwInjr79AXBS', 'Teacher', NULL, '2025-03-11 15:35:51', '2025-03-11 15:35:51', 3);



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;