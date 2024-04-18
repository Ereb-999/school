package ru.hogwarts.school.service;


import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;

import java.io.IOException;

public interface AvatarService {
    void uploadAvatar(Long studentId, MultipartFile failAvatar) throws IOException;
    Avatar findAvatar(Long studentId);
    Page<Avatar> getAllAvatar(Integer page, Integer size);
}
