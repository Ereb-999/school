package ru.hogwarts.school.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyServiceImp;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyServiceImp service;

    public FacultyController(FacultyServiceImp service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Faculty> create(@RequestBody Faculty faculty){
      return ResponseEntity.ok(service.createFaculty(faculty));
    }
    @GetMapping("{id}")
    public ResponseEntity<Faculty> get(@PathVariable Long id){
        return ResponseEntity.ok(service.get(id));
    }
    @GetMapping
    public ResponseEntity<Collection<Faculty>> getAll(){
        return (ResponseEntity<Collection<Faculty>>)(service.getAll());
    }
    @GetMapping("update/{id}")
    public ResponseEntity<Faculty> update(@RequestBody Faculty faculty, @PathVariable Long id){
        return  ResponseEntity.ok(service.update(faculty, id));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/filter/{color}")
    public ResponseEntity<List<Faculty>> get(@PathVariable String color){
        return  ResponseEntity.ok(service.findColor(color));
    }
    @GetMapping("filter")
    public  ResponseEntity<List<Faculty>> getFindAllByColor(@RequestParam String name, @RequestParam String color){
        return  ResponseEntity.ok(service.findByColorAndName(name, color));
    }

    @GetMapping("/maxNameFacult")
    public String getFacultyMaxNameLength(){
        return service.getMaxNameFacultyLength();
    }
}
