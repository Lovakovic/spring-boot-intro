DROP TABLE IF EXISTS student_course;
DROP TABLE IF EXISTS login_history;
DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS "user";

CREATE SEQUENCE IF NOT EXISTS user_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE student (
    jmbag VARCHAR(255) PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    date_of_birth DATE,
    ects_points INTEGER
);

CREATE TABLE course (
    id INTEGER PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    ects INTEGER NOT NULL
);

CREATE TABLE student_course (
    student_jmbag VARCHAR(255) NOT NULL,
    course_id INTEGER NOT NULL,
    PRIMARY KEY (student_jmbag, course_id),
    FOREIGN KEY (student_jmbag) REFERENCES student (jmbag),
    FOREIGN KEY (course_id) REFERENCES course (id)
);

CREATE TABLE "user" (
    id BIGINT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(255),
    username VARCHAR(255)
);

CREATE TABLE login_history (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    date_time_login TIMESTAMP(6) NOT NULL,
    date_time_logoff TIMESTAMP(6),
    role VARCHAR(255),
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES "user" (id)
);