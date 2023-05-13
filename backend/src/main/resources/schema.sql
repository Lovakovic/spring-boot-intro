DROP TABLE IF EXISTS student_course;
DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS user_authority;
DROP TABLE IF EXISTS authority;
DROP TABLE IF EXISTS "user";

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

CREATE TABLE course (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    ects INTEGER NOT NULL
);

CREATE TABLE student_course (
    student_jmbag CHAR(10) NOT NULL,
    course_id INTEGER NOT NULL,
    PRIMARY KEY (student_jmbag, course_id),
    FOREIGN KEY (student_jmbag) REFERENCES student (jmbag),
    FOREIGN KEY (course_id) REFERENCES course (id)
);

CREATE TABLE authority (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE "user" (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255)
);

CREATE TABLE user_authority (
    user_id INTEGER NOT NULL,
    authority_id INTEGER NOT NULL,
    PRIMARY KEY (user_id, authority_id),
    FOREIGN KEY (user_id) REFERENCES "user" (id),
    FOREIGN KEY (authority_id) REFERENCES authority (id)
);