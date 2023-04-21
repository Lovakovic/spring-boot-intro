CREATE TABLE IF NOT EXISTS student (
    jmbag CHAR(10) PRIMARY KEY,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    dateOfBirth DATE NOT NULL,
    ectsPoints INTEGER NOT NULL,
    enrolledStudiesAtYear INTEGER NOT NULL,
    currentSemester INTEGER NOT NULL
);