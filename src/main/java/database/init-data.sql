INSERT INTO `Classes` (`id`, `className`, `department`, `createdAt`, `updatedAt`) VALUES
(1, 'IT01', 'Công nghệ thông tin', '2025-03-03 14:51:59', '2025-03-04 14:42:28'),
(2, 'IT02', 'Khoa học máy tính', '2025-03-03 14:51:59', '2025-03-03 14:51:59'),
(3, 'IT03', 'Hệ thống thông tin', '2025-03-03 14:51:59', '2025-03-03 14:51:59'),
(4, 'CS01', 'An toàn thông tin', '2025-03-03 14:51:59', '2025-03-03 14:51:59'),
(5, 'CS02', 'Kỹ thuật phần mềm', '2025-03-03 14:51:59', '2025-03-03 14:51:59'),
(6, 'CS03', 'Khoa học dữ liệu', '2025-03-03 14:51:59', '2025-03-03 14:51:59'),
(7, 'SE01', 'Mạng máy tính', '2025-03-03 14:51:59', '2025-03-03 14:51:59'),
(8, 'SE02', 'Trí tuệ nhân tạo', '2025-03-03 14:51:59', '2025-03-03 14:51:59'),
(9, 'SE03', 'Khoa học máy tính ứng dụng', '2025-03-03 14:51:59', '2025-03-03 14:51:59'),
(10, 'DS01', 'Công nghệ phần mềm', '2025-03-03 14:51:59', '2025-03-03 14:51:59'),
(11, 'DS02', 'Hệ thống nhúng', '2025-03-03 14:51:59', '2025-03-03 14:51:59'),
(12, 'DS03', 'Điện tử viễn thông', '2025-03-03 14:51:59', '2025-03-03 14:51:59');

INSERT INTO `Subjects` (`id`, `subjectName`, `credit`, `createdAt`, `updatedAt`) VALUES
(1, 'Lập trình Python', 4, '2025-03-02 14:31:37', '2025-03-02 14:31:37'),
(2, 'Công Nghệ JAVA', 5, '2025-03-05 17:44:44', '2025-03-05 17:44:44'),
(3, 'Trí tuệ nhân tạo', 4, '2025-03-07 10:30:00', '2025-03-07 10:30:00'),
(4, 'Cơ sở dữ liệu', 3, '2025-03-08 12:45:00', '2025-03-08 12:45:00'),
(5, 'Mạng máy tính', 4, '2025-03-09 15:20:00', '2025-03-09 15:20:00'),
(6, 'Hệ điều hành', 3, '2025-03-10 09:00:00', '2025-03-10 09:00:00'),
(7, 'Cấu trúc dữ liệu & Giải thuật', 5, '2025-03-11 14:15:00', '2025-03-11 14:15:00'),
(8, 'Lập trình Web', 4, '2025-03-12 16:50:00', '2025-03-12 16:50:00'),
(9, 'Phân tích thiết kế hệ thống', 3, '2025-03-13 11:25:00', '2025-03-13 11:25:00'),
(10, 'An toàn thông tin', 4, '2025-03-14 18:10:00', '2025-03-14 18:10:00');

INSERT INTO `Students` (`id`, `fullName`, `birthDay`, `gender`, `phone`, `address`, `classId`, `createdAt`, `updatedAt`) VALUES
(1, 'Nguyễn Văn Một', '2025-03-05', 'Male', '09836789578', 'Hồ Chí Minh', 1, '2025-03-04 18:06:43', '2025-03-04 19:01:29'),
(2, 'Nguyễn Văn Hai', '2025-03-05', 'Male', '09836789571', 'Hồ Chí Minh', NULL, '2025-03-06 15:43:27', '2025-03-06 15:43:27'),
(3, 'Nguyễn Văn Ba', '2025-03-06', 'Male', '09836789572', 'Hồ Chí Minh', 2, '2025-03-06 16:04:17', '2025-03-06 16:04:17'),
(4, 'Nguyễn Văn Bốn', '2025-03-06', 'Male', '09836789573', 'Hồ Chí Minh', NULL, '2025-03-06 16:10:15', '2025-03-06 16:10:15'),
(5, 'Nguyễn Văn Năm', '2025-03-05', 'Male', '09836789574', 'Hồ Chí Minh', 5, '2025-03-06 16:27:05', '2025-03-06 16:27:05');

INSERT INTO `Enrollments` (`id`, `studentId`, `subjectId`, `semester`, `year`, `grade`, `createdAt`, `updatedAt`) VALUES
(1, 1, 4, 'Spring', 2025, -1, '2025-03-07 16:28:52', '2025-03-07 16:28:52'),
(2, 2, 4, 'Spring', 2025, -1, '2025-03-11 02:55:30', '2025-03-11 02:55:30'),
(3, 3, 1, 'Spring', 2025, -1, '2025-03-11 02:59:24', '2025-03-11 02:59:24'),
(4, 4, 4, 'Spring', 2025, -1, '2025-03-11 03:13:20', '2025-03-11 03:13:20'),
(5, 5, 1, 'Spring', 2025, -1, '2025-03-11 03:16:01', '2025-03-11 03:16:01');

INSERT INTO `Teachers` (`id`, `fullName`, `birthDay`, `gender`, `phone`, `address`, `createdAt`, `updatedAt`) VALUES
(1, 'Trần Thị Một', '2025-03-11', 'Male', '09836789571', 'Hồ Chí Minh', '2025-03-11 15:35:51', '2025-03-11 15:35:51'),
(2, 'Trần Thị Hai', '2025-03-11', 'Male', '09836789572', 'Hồ Chí Minh', '2025-03-11 15:35:51', '2025-03-11 15:35:51'),
(3, 'Trần Thị Ba', '2025-03-11', 'Male', '09836789573', 'Hồ Chí Minh', '2025-03-11 15:35:51', '2025-03-11 15:35:51'),
(4, 'Trần Thị Bốn', '2025-03-11', 'Male', '09836789574', 'Hồ Chí Minh', '2025-03-11 15:35:51', '2025-03-11 15:35:51'),
(5, 'Trần Thị Năm', '2025-03-11', 'Male', '09836789575', 'Hồ Chí Minh', '2025-03-11 15:35:51', '2025-03-11 15:35:51');

INSERT INTO `Users` (`id`, `email`, `password`, `role`, `studentId`, `createdAt`, `updatedAt`, `teacherId`) VALUES
(1, 'admin@gmail.com', '$2a$10$3qPSkgFDsSxzFCTogCavveGqH3l15x20Xoge/9dr7IwwuSfpp7Ev6', 'Admin', NULL, '2025-03-03 16:02:44', '2025-03-04 02:48:37', NULL),
(2, 'student1@gmail.com', '$2a$10$bIxbdNyU3k4ZdoaXQiwJHOUAIxVfUdEB2Q7t8OpLEOY1m6zLi/gwK', 'Student', 1, '2025-03-03 16:07:57', '2025-03-05 17:18:16', NULL),
(3, 'student2@gmail.com', '$2a$10$tV/UIHlh4BnVP9n6qKYKYe.TqfRS.3RVQaLnKOqV.R71YpzhK/S.K', 'Student', 2, '2025-03-03 16:10:05', '2025-03-06 16:10:15', NULL),
(4, 'student3@gmail.com', '$2a$10$ro7zVqHrY3a.7w/ujfVTOunPy7CEyqcl9WaShlsgI9JJo4ljXhZpi', 'Student', 3, '2025-03-03 16:12:31', '2025-03-06 16:27:05', NULL),
(5, 'student4@gmail.com', '$2a$10$tXF193S430/F/Uj58eMVz.yvipItlhr5HIdM8sT9A2h66Ya64dFve', 'Student', 4, '2025-03-03 17:32:19', '2025-03-06 16:31:40', NULL),
(6, 'student5@gmail.com', '$2a$10$zDjzRqTIP43b19BVwSLEAuTHK/5B5MMjlL8ZUCcm/S.VA5tcqW0x2', 'Student', 5, '2025-03-05 15:20:18', '2025-03-07 15:23:07', NULL),
(7, 'teacher1@gmail.com', '$2a$10$kXwPjBvXPO/NdOsj5FVAS.iRtzSdQ0RFK9PEeJ3WgOZjKJ/pRJikO', 'Teacher', NULL, '2025-03-05 17:46:03', '2025-03-07 15:31:40', 1),
(8, 'teacher2@gmail.com', '$2a$10$kPwWnIdzthkphIVpYHB0uOzsqvu5wb9qDLsXIVxmAQxOxRAtHAQ8C', 'Teacher', NULL, '2025-03-07 15:32:17', '2025-03-07 15:32:35', 2),
(9, 'teacher3@gmail.com', '$2a$10$TTciQwUKNvyPRS4JdU3aTuCWLdl7pYDSmrDwiB1u8ZVwoH168Z.iq', 'Teacher', NULL, '2025-03-07 15:35:59', '2025-03-07 15:36:20', 3),
(10, 'teacher4@gmail.com', '$2a$10$fPmwSOUhvFsEDNtV0ZBKEurDXFSVFBy3rK2rnyb7.aGXiplML9nWq', 'Teacher', NULL, '2025-03-07 15:36:50', '2025-03-07 15:37:13', 4),
(11, 'teacher5@gmail.com', '$2a$10$fPmwSOUhvFsEDNtV0ZBKEurDXFSVFBy3rK2rnyb7.aGXiplML9nWq', 'Teacher', NULL, '2025-03-07 15:36:50', '2025-03-07 15:37:13', 5);

INSERT INTO `TeacherSubject` (`id`, `teacherId`, `subjectId`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3),
(4, 4, 4),
(5, 5, 5);


