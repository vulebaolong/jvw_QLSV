CREATE TABLE IF NOT EXISTS `Classes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `className` varchar(255) NOT NULL,
  `department` varchar(255) NOT NULL,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `className` (`className`)
);

CREATE TABLE IF NOT EXISTS `Subjects` (
  `id` int NOT NULL AUTO_INCREMENT,
  `subjectName` varchar(255) NOT NULL,
  `credit` int NOT NULL,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `subjectName` (`subjectName`),
  CHECK ((`credit` > 0))
);

CREATE TABLE IF NOT EXISTS `Students` (
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
  FOREIGN KEY (`classId`) REFERENCES `Classes` (`id`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `Enrollments` (
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
  FOREIGN KEY (`studentId`) REFERENCES `Students` (`id`) ON DELETE CASCADE,
  FOREIGN KEY (`subjectId`) REFERENCES `Subjects` (`id`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `Teachers` (
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
);

CREATE TABLE IF NOT EXISTS `TeacherSubject` (
  `id` int NOT NULL AUTO_INCREMENT,
  `teacherId` int NOT NULL,
  `subjectId` int NOT NULL,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `teacherId` (`teacherId`),
  KEY `subjectId` (`subjectId`),
  FOREIGN KEY (`teacherId`) REFERENCES `Teachers` (`id`) ON DELETE CASCADE,
  FOREIGN KEY (`subjectId`) REFERENCES `Subjects` (`id`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `Users` (
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
  FOREIGN KEY (`studentId`) REFERENCES `Students` (`id`) ON DELETE SET NULL,
  FOREIGN KEY (`teacherId`) REFERENCES `Teachers` (`id`) ON DELETE SET NULL
);
