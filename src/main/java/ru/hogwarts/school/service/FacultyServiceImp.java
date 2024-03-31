package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.ExceptionFaculty;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.List;

@Service
public class FacultyServiceImp implements FacultyService{
    private final FacultyRepository facultyRepository;

    public FacultyServiceImp(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {return facultyRepository.save(faculty);}

    @Override
    public Faculty get(Long id) {
       return facultyRepository.findById(id).orElseThrow(ExceptionFaculty::new);
    }

    @Override
    public Faculty update(Faculty faculty, Long id) {
        facultyRepository.findById(id).orElseThrow(ExceptionFaculty::new);
        return facultyRepository.save(faculty);
    }

    @Override
    public void delete(Long id) {
        facultyRepository.deleteById(id);
    }

    @Override
    public List<Faculty> findColor(String color) {
        return facultyRepository.findAllByColor(color);
    }

    @Override
    public Collection<Faculty> getAll() {
        return facultyRepository.findAll();
    }

    @Override
    public List<Faculty> findByColorAndName(String name, String color) {
        return facultyRepository.findByColorAndName(name, color);
    }
}
