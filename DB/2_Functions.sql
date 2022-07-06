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