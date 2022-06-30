--function for time
CREATE OR REPLACE FUNCTION trigger_set_timestamp()
RETURNS TRIGGER AS $$
BEGIN
  NEW.updated_datetime = NOW();
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;


--Tables creation
-- 6. Add validation on DB level that will check username on special characters (reject student name with next characters '@', '#', '$'). (0.3 point)
CREATE TABLE IF NOT EXISTS students (
    id serial NOT NULL,
    name text NOT NULL check (name !~ '.*[@*].*'),
    surname text NOT NULL check (surname !~ '.*[@*].*'),
    date_of_birth date NOT NULL,
    phone_number varchar(10) NOT NULL,
    created_datetime TIMESTAMPTZ DEFAULT now() NOT NULL,
    updated_datetime TIMESTAMPTZ DEFAULT now() NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS subjects (
    id serial NOT NULL,
    name text NOT NULL,
    tutor text NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS grades (
    student_id integer NOT NULL,
    subject_id integer NOT NULL,
    mark integer NOT NULL,
    FOREIGN KEY (student_id) REFERENCES students(id),
    FOREIGN KEY (subject_id) REFERENCES subjects(id)
);

--5. Add trigger that will update column updated_datetime to current date in case of updating any of student. (0.3 point)
CREATE TRIGGER set_timestamp
BEFORE UPDATE ON students
FOR EACH ROW
EXECUTE PROCEDURE trigger_set_timestamp();

--a. Find user by name (exact match)
select * from students where name = 'name';

--b. Find user by surname (partial match)
select * from students where name like '%n%';

--c. Find user by phone number (partial match)
select * from students where phone_number like '%0%';

--d. Find user with marks by user surname (partial match)
select students.name, students.surname, subjects.name, grades.mark
    FROM grades
    LEFT JOIN students
    ON grades.student_id = students.id
    LEFT JOIN subjects
    ON grades.subject_id = subjects.id
    where student_id = (
		select id
		from students
		where surname = 'surname');

--Function: get all marks by surname
create function get_marks_by_surname(stud_surname text)
returns table (
subjects_name text,
mark integer
)
language plpgsql
as
$$
begin
return query
 select subjects.name, grades.mark
    FROM grades
    LEFT JOIN students
    ON grades.student_id = students.id
    LEFT JOIN subjects
    ON grades.subject_id = subjects.id
    where student_id = (select id from students where surname = stud_surname);
end;
$$;

--use function
select * from get_marks_by_surname('suranme');



--Function: get average mark by surname
--8. Create function that will return average mark for input user. (0.3 point)
create or replace function get_average_mark_by_surname(stud_surname text)
returns numeric
language plpgsql
as
$$
begin
return
 (select avg(grades.mark)
    FROM grades
    LEFT JOIN students
    ON grades.student_id = students.id
    LEFT JOIN subjects
    ON grades.subject_id = subjects.id
    where student_id = (select id from students where surname = stud_surname)
 );
end;
$$;

--Use function
select * from get_average_mark_by_surname('surname');

--Function: get average mark by subject name
-- 9. Create function that will return avarage mark for input subject name. (0.3 point).
create or replace function get_average_mark_by_subject_name(subject_name text)
returns numeric
language plpgsql
as
$$
begin
return
 (select avg(grades.mark)
    FROM grades
    LEFT JOIN subjects
    ON grades.subject_id = subjects.id
    where subject_id = (select id from subjects where name = subject_name)
 );
end;
$$;

--Use function
select * from get_average_mark_by_subject_name('sub1');


--10.Create function that will return student at "red zone" (red zone means at least 2 marks <=3). (0.3 point)
create function red_zone_students()
returns table (
subjects_name text
)
language plpgsql
as
$$
begin
return query
 select students.name
    FROM grades
    LEFT JOIN students
    ON grades.student_id = students.id
    where grades.mark <= 3
	group by students.name
	HAVING COUNT(*) >= 2;
end;
$$;

--Use function
select * from red_zone_students();
