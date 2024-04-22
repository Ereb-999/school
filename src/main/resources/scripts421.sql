ALTER TABLE student
ADD CONSTRAINT age_student_constraint check (age > 16);

ALTER TABLE student
ADD CONSTRAINT name_student_unique UNIQUE (name);

ALTER TABLE student
ADD name SET NOT NULL;

ALTER TABLE faculty
ADD CONSTRAINT color_name_faculty_unique UNIQUE (color, name);

ALTER TABLE student
ALTER age SET DEFAULT 20;