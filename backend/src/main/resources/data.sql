-- Students
INSERT INTO student (jmbag, firstName, lastName, dateOfBirth, ectsPoints, enrolledStudiesAtYear, currentSemester, email, phone, major)
VALUES
    ('9283758271', 'Ivan', 'Markovic', '2000-01-01', 60, 2019, 4, 'ivan.markovic@student.com', '+38591234567', 'Computer Science'),
    ('0284729172', 'Horvat', 'Horvatinic', '2001-02-02', 78, 2021, 3, 'horvat.horvatinic@student.com', '+38598765432', 'Business Administration'),
    ('7261948261', 'Ana', 'Babic', '2002-03-05', 72, 2020, 2, 'ana.babic@student.com', '+38595544333', 'Electrical Engineering'),
    ('8826183962', 'Petar', 'Novak', '2000-05-10', 85, 2019, 4, 'petar.novak@student.com', '+38591666666', 'Mechanical Engineering'),
    ('5719273628', 'Ivana', 'Kovac', '1999-09-20', 90, 2018, 2, 'ivana.kovac@student.com', '+38597777777', 'Mathematics'),
    ('2957281941', 'Luka', 'Hodak', '2003-04-15', 65, 2022, 1, 'luka.hodak@student.com', '+38595555555', 'Environmental Science'),
    ('4791826392', 'Jana', 'Bogdan', '2001-07-25', 80, 2021, 2, 'jana.bogdan@student.com', '+38594444444', 'Architecture');

-- Courses
INSERT INTO course (name, ects)
VALUES
    ('Introduction to Computer Science', 6),
    ('Calculus', 8),
    ('Data Structures', 6),
    ('Algorithms', 6),
    ('Discrete Mathematics', 6),
    ('Computer Networks', 6),
    ('Operating Systems', 6),
    ('Software Engineering', 6),
    ('Database Systems', 6),
    ('Artificial Intelligence', 6),
    ('Machine Learning', 6),
    ('Computer Graphics', 6),
    ('Computer Vision', 6),
    ('Web Development', 6),
    ('Mobile Development', 6),
    ('Embedded Systems', 6),
    ('Computer Security', 6),
    ('Cryptography', 6),
    ('Parallel Computing', 6),
    ('Programming Languages', 6);


-- Student enrollments
INSERT INTO student_course (student_jmbag, course_id)
VALUES
    ('9283758271', 1),
    ('9283758271', 3),
    ('9283758271', 4),
    ('9283758271', 5),
    ('9283758271', 7),
    ('0284729172', 1),
    ('0284729172', 3),
    ('0284729172', 4),
    ('0284729172', 9),
    ('0284729172', 10),
    ('7261948261', 2),
    ('7261948261', 3),
    ('7261948261', 4),
    ('7261948261', 5),
    ('7261948261', 8),
    ('7261948261', 12),
    ('8826183962', 1),
    ('8826183962', 3),
    ('8826183962', 6),
    ('8826183962', 7),
    ('8826183962', 11),
    ('5719273628', 2),
    ('5719273628', 5),
    ('5719273628', 8),
    ('5719273628', 14),
    ('5719273628', 16),
    ('2957281941', 1),
    ('2957281941', 3),
    ('2957281941', 4),
    ('2957281941', 9),
    ('2957281941', 15),
    ('4791826392', 2),
    ('4791826392', 5),
    ('4791826392', 6),
    ('4791826392', 8),
    ('4791826392', 13);
