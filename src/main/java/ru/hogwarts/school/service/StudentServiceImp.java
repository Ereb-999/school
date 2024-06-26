package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.ExceptionStudent;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;


@Service
public class StudentServiceImp implements StudentService{

    Logger logger = LoggerFactory.getLogger(StudentServiceImp.class);
    private final StudentRepository studentRepository;

    public StudentServiceImp(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student add(Student student) {
        student.setId(null);
        logger.debug("Input error to the method (add) [student]: ", student);
        return studentRepository.save(student);
    }

    @Override
    public Student find(long id) {
        logger.debug("Input error to the method (create) [id]: ", id);
        return studentRepository.findById(id).orElseThrow(ExceptionStudent:: new);
    }

    @Override
    public Student edit(Student student) {
        logger.debug("Input error to the method (edit) [student]: ", student);
        return studentRepository.save(student);
    }

    @Override
    public Collection<Student> findAge(Integer minAge, Integer maxAge) {
        logger.debug("Input error to the method (findAge) [minAge, maxAge]: ", minAge, maxAge);
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    @Override
    public Collection<Student> getAll() {
        logger.debug("Input error to the method (getAll) ");
        return studentRepository.findAll();
    }

    @Override
    public void delete(long id) {
        studentRepository.findById(id).orElseThrow(ExceptionStudent:: new);
        logger.debug("Input error to the method (delete) [id]: ", id);
        studentRepository.deleteById(id);
    }


}
