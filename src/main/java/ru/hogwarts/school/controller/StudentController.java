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
    public ResponseEntity<Collection<Student>> studentsInform(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> studentsInform(@PathVariable Long id){
        Student student = service.find(id);
        if (student == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.find(id));
    }
    @PostMapping
    public ResponseEntity<Student> сreate(@RequestBody Student student){
       service.add(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Student> edit(@RequestBody Student student){
        Student editStudent = service.edit(student);
        if (editStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return new ResponseEntity<>(editStudent, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAge")
    public ResponseEntity<Collection<Student>> getAge(@RequestParam Integer minAge, @RequestParam Integer maxAge){
        return ResponseEntity.ok(service.findAge(minAge, maxAge));
    }

    @GetMapping("/sortingA")
    public ResponseEntity<Collection<String>> getNameStudentSortingA(){
        Collection<String> stringCollection = service.getNameFilterA();
        return ResponseEntity.ok(stringCollection);
    }

    @GetMapping("/StudentSortingAverageAge")
    public Double studentSortingAverageAge(){return service.getStudentSortingAverageAge();}

    @GetMapping("/print-parallel")
    public void getStudentParallel() throws InterruptedException{
        service.getStudentParallel();
    }
    @GetMapping("/print-synchronized")
    public void getStudentSync() throws InterruptedException{
        service.getStudentSync();

    }

}
