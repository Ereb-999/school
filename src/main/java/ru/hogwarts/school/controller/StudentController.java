package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentServiceImp;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentServiceImp service;

    public StudentController(StudentServiceImp service) {
        this.service = service;
    }

    @GetMapping
    public Collection<Student> studentsInform(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    public Student studentsInform(@PathVariable Long id){
        return service.find(id);
    }
    @PostMapping
    public ResponseEntity<Student> add(@RequestBody Student student){
       service.add(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public Student edit(@RequestBody Student student, @PathVariable Long id){
        return service.edit(id, student);
    }
    @PostMapping("{id}")
    public void delete(@RequestParam long id){
        service.delete(id);
    }
    @GetMapping("/getAge")
    public Collection<Student> getAge(@RequestParam Integer minAge, @RequestParam Integer maxAge){
        return service.findAge(minAge, maxAge);
    }

}
