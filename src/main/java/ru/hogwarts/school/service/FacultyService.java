package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.List;

public interface FacultyService {
    Faculty get(Long id);
    Faculty update(Faculty faculty, Long id);
    List<Faculty> findColor(String color);
    Collection<Faculty> getAll();
    void delete(Long id);
    String getMaxNameFacultyLength();
    List<Faculty> findByColorAndName(String name, String color);
}
