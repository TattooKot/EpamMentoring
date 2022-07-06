--Students table
insert into students(name, surname, date_of_birth, phone_number)
values
('Name 1', 'Surname 1', '01.01.2001', '1234567890'),
('Name 2', 'Surname 2', '01.01.2001', '1234567890'),
('Name 3', 'Surname 3', '01.01.2001', '1234567890'),
('Name 4', 'Surname 4', '01.01.2001', '1234567890'),
('Name 5', 'Surname 5', '01.01.2001', '1234567890'),
('Name 6', 'Surname 6', '01.01.2001', '1234567890'),
('Name 7', 'Surname 7', '01.01.2001', '1234567890'),
('Name 8', 'Surname 8', '01.01.2001', '1234567890'),
('Name 9', 'Surname 9', '01.01.2001', '1234567890'),
('Name 1', 'Surname 90', '01.01.2001', '1234567890'),
('Name 2', 'Surname 90', '01.01.2001', '1234567890'),
('Name 3', 'Surname 90', '01.01.2001', '1234567890'),
('Name 4', 'Surname 90', '01.01.2001', '1234567890'),
('Name 5', 'Surname 90', '01.01.2001', '1234567890'),
('Name 6', 'Surname 90', '01.01.2001', '1234567890'),
('Name 7', 'Surname 90', '01.01.2001', '1234567890'),
('Name 8', 'Surname 90', '01.01.2001', '1234567890'),
('Name 9', 'Surname 90', '01.01.2001', '1234567890');

--Subjects table
insert into subjects(name, tutor)
values
('Subject 1', 'tutor 1'),
('Subject 2', 'tutor 1'),
('Subject 3', 'tutor 1'),
('Subject 4', 'tutor 1'),
('Subject 5', 'tutor 1'),
('Subject 6', 'tutor 2'),
('Subject 7', 'tutor 2'),
('Subject 8', 'tutor 2'),
('Subject 9', 'tutor 2'),
('Subject 0', 'tutor 2');

--Grades table
insert into grades(student_id, subject_id, mark)
values
(1, 1, 2),
(1, 2, 2),
(1, 3, 2),
(1, 4, 2),
(1, 5, 2),
(1, 6, 2),
(1, 7, 2),
(1, 8, 2),
(1, 9, 2),
(2, 9, 2),
(3, 9, 2),
(4, 9, 2),
(5, 9, 2),
(6, 9, 2),
(7, 9, 2),
(8, 9, 2),
(9, 9, 2);