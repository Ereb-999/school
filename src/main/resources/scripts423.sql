SELECT student.name, student.age, faculty.name
FROM student
INNER JOIN faculty ON student.id_faculty = faculty.id;

SELECT student
FROM student
INNER JOIN avatar a ON student.id = a.student_id;