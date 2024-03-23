package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.ExceptionStudent;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;


@Service
public class StudentServiceImp implements StudentService{

    private final StudentRepository studentRepository;

    public StudentServiceImp(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student add(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student find(long id) {
        return studentRepository.findById(id).orElseThrow(ExceptionStudent:: new);
    }

    @Override
    public Student edit(long id, Student student) {
        studentRepository.findById(id).orElseThrow(ExceptionStudent::new);
        return studentRepository.save(student);
    }

    @Override
    public Collection<Student> findAge(Integer minAge, Integer maxAge) {
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    @Override
    public Collection<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public void delete(long id) {
        studentRepository.findById(id).orElseThrow(ExceptionStudent:: new);
        studentRepository.deleteById(id);
    }


}
