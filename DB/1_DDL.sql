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