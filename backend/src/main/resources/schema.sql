DROP TABLE IF EXISTS student;
CREATE TABLE student (
    jmbag CHAR(10) PRIMARY KEY,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    dateOfBirth DATE NOT NULL,
    ectsPoints INTEGER NOT NULL,
    enrolledStudiesAtYear INTEGER NOT NULL,
    currentSemester INTEGER NOT NULL,
    email VARCHAR(255),
    phone VARCHAR(20),
    major VARCHAR(255) NOT NULL
);