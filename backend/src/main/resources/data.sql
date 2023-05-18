-- Students
INSERT INTO student (jmbag, firstName, lastName, dateOfBirth, ectsPoints)
VALUES
    ('9283758271', 'Ivan', 'Markovic', '2000-01-01', 60),
    ('0284729172', 'Horvat', 'Horvatinic', '2001-02-02', 78),
    ('7261948261', 'Ana', 'Babic', '2002-03-05', 72),
    ('8826183962', 'Petar', 'Novak', '2000-05-10', 85),
    ('5719273628', 'Ivana', 'Kovac', '1999-09-20', 90),
    ('2957281941', 'Luka', 'Hodak', '2003-04-15', 65),
    ('4791826392', 'Jana', 'Bogdan', '2001-07-25', 80);

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


-- Insert user data
INSERT INTO "user" (id, username, password)
VALUES (1, 'user', '$2a$10$TYExkmI7uVXXVadrdTTa0OQTOorVV32jTjK.Py2BPQjEojbAx96yy');

INSERT INTO "user" (id, username, password)
VALUES (2, 'admin', '$2a$10$TYExkmI7uVXXVadrdTTa0OQTOorVV32jTjK.Py2BPQjEojbAx96yy');

-- Insert authority data
INSERT INTO authority (id, name)
VALUES (1, 'ROLE_USER');

INSERT INTO authority (id, name)
VALUES (2, 'ROLE_ADMIN');

-- Link users to their roles
INSERT INTO user_authority (user_id, authority_id)
VALUES (1, 1);

INSERT INTO user_authority (user_id, authority_id)
VALUES (2, 2);


-- Login history entries
INSERT INTO login_history (user_id, role_id, date_time_login, date_time_logoff)
VALUES (1, 1, '2023-05-15 09:30:00', '2023-05-15 11:45:00');

INSERT INTO login_history (user_id, role_id, date_time_login, date_time_logoff)
VALUES (1, 1, '2023-05-15 12:00:00', '2023-05-15 14:30:00');

INSERT INTO login_history (user_id, role_id, date_time_login, date_time_logoff)
VALUES (2, 2, '2023-05-15 15:00:00', '2023-05-15 17:15:00');

INSERT INTO login_history (user_id, role_id, date_time_login, date_time_logoff)
VALUES (1, 1, '2023-05-15 18:00:00', '2023-05-15 20:30:00');

INSERT INTO login_history (user_id, role_id, date_time_login, date_time_logoff)
VALUES (2, 2, '2023-05-15 21:00:00', '2023-05-15 23:15:00');

INSERT INTO login_history (user_id, role_id, date_time_login, date_time_logoff)
VALUES (1, 1, '2023-05-15 08:30:00', '2023-05-15 10:45:00');

INSERT INTO login_history (user_id, role_id, date_time_login, date_time_logoff)
VALUES (2, 2, '2023-05-15 11:00:00', '2023-05-15 13:15:00');

INSERT INTO login_history (user_id, role_id, date_time_login, date_time_logoff)
VALUES (1, 1, '2023-05-15 14:00:00', '2023-05-15 16:30:00');

INSERT INTO login_history (user_id, role_id, date_time_login, date_time_logoff)
VALUES (2, 2, '2023-05-15 17:00:00', '2023-05-15 19:15:00');

INSERT INTO login_history (user_id, role_id, date_time_login, date_time_logoff)
VALUES (1, 1, '2023-05-15 20:00:00', '2023-05-15 22:30:00');
