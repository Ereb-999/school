package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
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

    @GetMapping
    public Faculty create(@RequestBody Faculty faculty){
        return service.createFaculty(faculty);
    }
    @GetMapping("/{id}")
    public Faculty get(@PathVariable Long id){
        return service.get(id);
    }
    @GetMapping
    public Collection<Faculty> getAll(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    public Faculty update(@RequestBody Faculty faculty, @PathVariable Long id){
        return service.update(faculty, id);
    }
    @DeleteMapping("{id}")
    public void delete(@RequestParam long id){
        service.delete(id);
    }
    @GetMapping("/filter/{color}")
    public List<Faculty> get(@RequestParam String color){
        return service.findColor(color);
    }
    @GetMapping("/filter")
    public List<Faculty> getFindAllByColor(@RequestParam String name, @RequestParam String color){
        return service.findByColorAndName(name, color);
    }
}
