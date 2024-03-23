package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentService {
    Student add(Student student);
    Student find(long id);
    Collection<Student> findAge(Integer minAge, Integer maxAge);
    Student edit(long id, Student student);
    Collection<Student> getAll();
    void delete(long id);

}

