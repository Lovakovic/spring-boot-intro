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

-- About Me (English)
INSERT INTO about_me (jmbag, language, content)
VALUES
    ('9283758271', 'en', 'Hello, my name is Ivan Markovic. I was born on January 1, 2000. I am currently enrolled in the university and have earned 60 ECTS points.'),
    ('9283758271', 'hr', 'Pozdrav, zovem se Ivan Marković. Rođen sam 1. siječnja 2000. Trenutno sam upisan na fakultet i sakupio sam 60 ECTS bodova.'),
    ('9283758271', 'zh', '你好，我叫伊万·马尔科维奇。我出生于2000年1月1日。我目前在大学就读，已经获得了60个ECTS学分。'),
    ('0284729172', 'en', 'Hi, I''m Horvat Horvatinic. I was born on February 2, 2001. I am passionate about learning and have achieved 78 ECTS points so far.'),
    ('0284729172', 'hr', 'Pozdrav, ja sam Horvat Horvatinic. Rođen sam 2. veljače 2001. Strastveno se bavim učenjem i dosad sam ostvario 78 ECTS bodova.'),
    ('0284729172', 'zh', '嗨，我是霍瓦特·霍瓦蒂尼奇。我出生于2001年2月2日。我热衷于学习，到目前为止已经获得了78个ECTS学分。'),
    ('7261948261', 'en', 'Hi there! I''m Ana Babic. I was born on March 5, 2002. I enjoy exploring new subjects and have earned 72 ECTS points in my studies.'),
    ('7261948261', 'hr', 'Bok! Ja sam Ana Babić. Rođena sam 5. ožujka 2002. Uživam istraživati nove predmete i ostvarila sam 72 ECTS boda tijekom studija.'),
    ('7261948261', 'zh', '嗨！我是安娜·巴比奇。我出生于2002年3月5日。我喜欢探索新的课题，到目前为止在学习中获得了72个ECTS学分。'),
    ('8826183962', 'en', 'Greetings! My name is Petar Novak. I was born on May 10, 2000. I am dedicated to my studies and have achieved 85 ECTS points so far.'),
    ('8826183962', 'hr', 'Pozdrav! Zovem se Petar Novak. Rođen sam 10. svibnja 2000. Posvećen sam svom studiju i dosad sam ostvario 85 ECTS bodova.'),
    ('7261948261', 'zh', '嗨！我是安娜·巴比奇。我出生于2002年3月5日。我喜欢探索新的课题，到目前为止在学习中获得了72个ECTS学分。'),
    ('5719273628', 'en', 'Hello, my name is Ivana Kovac. I was born on September 20, 1999. I am a diligent student and have earned 90 ECTS points in my academic journey.'),
    ('5719273628', 'hr', 'Pozdrav, zovem se Ivana Kovač. Rođena sam 20. rujna 1999. Marljiva sam studentica i ostvarila sam 90 ECTS bodova u svom akademskom putovanju.'),
    ('5719273628', 'zh', '你好，我叫伊万娜·科瓦奇。我出生于1999年9月20日。我是个勤奋的学生，到目前为止在我学业中获得了90个ECTS学分。'),
    ('2957281941', 'en', 'Hi, I''m Luka Hodak. I was born on April 15, 2003. I''m eager to expand my knowledge and have earned 65 ECTS points during my studies.'),
    ('2957281941', 'hr', 'Bok, ja sam Luka Hodak. Rođen sam 15. travnja 2003. Željan sam proširivanja svog znanja i dosad sam ostvario 65 ECTS bodova tijekom studija.'),
    ('2957281941', 'zh', '嗨，我是卢卡·霍达克。我出生于2003年4月15日。我渴望扩大我的知识，在我的学习中已经获得了65个ECTS学分。'),
    ('4791826392', 'en', 'Hey there! I''m Jana Bogdan. I was born on July 25, 2001. I''m passionate about my field of study and have achieved 80 ECTS points so far.'),
    ('4791826392', 'hr', 'Hej! Ja sam Jana Bogdan. Rođena sam 25. srpnja 2001. Strastveno se bavim svojim područjem studija i dosad sam ostvarila 80 ECTS bodova.'),
    ('4791826392', 'zh', '嘿！我是詹娜·博格丹。我出生于2001年7月25日。我对我的学习领域充满热情，到目前为止已经获得了80个ECTS学分。');


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
