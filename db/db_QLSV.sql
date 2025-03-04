
-- Lớp học
CREATE TABLE `Classes` (
    `id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    className VARCHAR(255) UNIQUE NOT NULL, -- Tên lớp (VD: IT01, IT02...).
    department VARCHAR(255) NOT NULL, -- Khoa/Ngành học.
    `createdAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
	`updatedAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Môn học
CREATE TABLE `Subjects` (
    `id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT, -- Mã môn học.
    subjectName VARCHAR(255) UNIQUE NOT NULL, --  Tên môn học.
    credit INT NOT NULL CHECK (credit > 0), -- Số tín chỉ (phải lớn hơn 0).
    `createdAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
	`updatedAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `Students` (
    `id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `fullName` VARCHAR(255) NOT NULL, --  Họ tên sinh viên.
    `birthDay` DATE NOT NULL, -- Ngày sinh.
    `gender` ENUM('Male', 'Female', 'Other') NOT NULL, -- Giới tính (Nam, Nữ, Khác).
    `phone` VARCHAR(255), -- Số điện thoại (không trùng lặp).
    `address` TEXT, --  Địa chỉ sinh viên.
    `createdAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
	`updatedAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Đăng ký môn học
CREATE TABLE `Enrollments` (
    `id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `studentId` INT, -- Sinh viên đăng ký môn học nào.
    `subjectId` INT, -- Môn học đã đăng ký
    `semester` VARCHAR(10) NOT NULL, -- Học kỳ (VD: Fall, Spring...).
    `year` INT NOT NULL, -- Năm học.
    `grade` FLOAT CHECK (grade >= 0 AND grade <= 10), -- Điểm môn học (thang 10).
    `createdAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
	`updatedAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`studentId`) REFERENCES `Students`(id) ON DELETE CASCADE,
    FOREIGN KEY (`subjectId`) REFERENCES `Subjects`(id) ON DELETE CASCADE
);

-- Tài khoản đăng nhập - dành cho quản trị viên, giảng viên, sinh viên
CREATE TABLE `Users` (
    `id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `email` VARCHAR(50) UNIQUE NOT NULL,
    `password` VARCHAR(255) NOT NULL, -- Mật khẩu (đã mã hóa)
    `role` ENUM('Admin', 'Teacher', 'Student') NOT NULL DEFAULT 'Student', -- Phân quyền (Admin, Teacher, Student).
    `studentId` INT UNIQUE, -- Nếu là sinh viên thì liên kết đến students.
    `createdAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
	`updatedAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	FOREIGN KEY (`studentId`) REFERENCES `Students`(id) ON DELETE SET NULL
);
