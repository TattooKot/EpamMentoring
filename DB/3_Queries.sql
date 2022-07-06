--a. Find user by name (exact match)
select * from students where name = 'name';

--b. Find user by surname (partial match)
select * from students where name like '%n%';

--c. Find user by phone number (partial match)
select * from students where phone_number like '%0%';

--d. Find user with marks by user surname (partial match)
select students.name, students.surname, subjects.name, grades.mark
    FROM grades
    JOIN students
    ON grades.student_id = students.id
    LEFT JOIN subjects
    ON grades.subject_id = subjects.id
    where students.surname like  '%Sur%';