package ru.hogwarts.school.service;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.ExceptionFaculty;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Service
public class FacultyServiceImp implements FacultyService{

    Logger logger = LoggerFactory.getLogger(FacultyServiceImp.class);
    private final FacultyRepository facultyRepository;

    public FacultyServiceImp(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.debug("Input error to the method (create) [faculty]: ", faculty);
        return facultyRepository.save(faculty);}

    @Override
    public Faculty get(Long id) {
        logger.debug("Input error to the method (get) [id]: ", id);
       return facultyRepository.findById(id).orElseThrow(ExceptionFaculty::new);
    }

    @Override
    public Faculty update(Faculty faculty, Long id) {
        logger.debug("Input error to the method (update) [faculty, id]: ", faculty, id);
        facultyRepository.findById(id).orElseThrow(ExceptionFaculty::new);
        return facultyRepository.save(faculty);
    }


    public String getMaxNameFacultyLength(){
        logger.debug("Input error to the method (getMaxNameFacultyLength)");
        return facultyRepository
                .findAll()
                .stream()
                .map(Faculty::getName)
                .max(Comparator.comparing(String::length))
                .orElse("");
    }

    @Override
    public void delete(Long id) {
        logger.debug("Input error to the method (delete) [id]: ", id);
        facultyRepository.deleteById(id);
    }

    @Override
    public List<Faculty> findColor(String color) {
        logger.debug("Input error to the method (findColor) [color]: ", color);
        return facultyRepository.findAllByColor(color);
    }

    @Override
    public Collection<Faculty> getAll() {
        logger.debug("Input error to the method (getAll)");
        return facultyRepository.findAll();
    }

    @Override
    public List<Faculty> findByColorAndName(String name, String color) {
        logger.debug("Input error to the method (findByColorAndName) [name, color]", name, color);
        return facultyRepository.findByColorAndName(name, color);
    }
}
