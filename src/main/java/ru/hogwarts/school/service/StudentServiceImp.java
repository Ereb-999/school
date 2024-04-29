package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.ExceptionStudent;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.stream.Collectors;


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
    @Override
    public Collection<String> getNameFilterA(){
        logger.debug("Input error to the method (getNameFilterA)");
        return studentRepository.findAll()
                .stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(s->s.startsWith("А"))
                .sorted()
                .collect(Collectors.toList());
    }
    @Override
    public Double getStudentSortingAverageAge(){
        logger.debug("Input error to the method (getStudentSortingAverageAge)");
        return studentRepository.findAll()
                .stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0);
    }

    private void printNameStudent(Long id){
        String studentName = studentRepository.getById(id).getName();
        System.out.println(studentName);
    }
    private synchronized void printSync(Long id){
        String studentName = studentRepository.getById(id).getName();
        System.out.println(studentName);
    }

    public void getStudentParallel(){
        Thread thread1 = new Thread(() ->{
          printNameStudent(6L);
            printNameStudent(5L);
        });
        thread1.setName("Thread: 1 ");
        Thread thread2 = new Thread(() ->{
            printNameStudent(1L);
            printNameStudent(2L);
        });
        thread1.setName("Thread: 2 ");
        thread1.start();
        thread2.start();
        printNameStudent(3l);
        printNameStudent(4l);
    }
    public void getStudentSync(){
        Thread thread = new Thread(() ->{
           printSync(3L);
           printSync(4L);
        });
        Thread thread2 = new Thread(() ->{
            printSync(5L);
            printSync(6L);
        });
        printSync(1L);
        printSync(2L);
        thread.start();
        thread2.start();
    }

}
