package ru.hogwarts.school.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class AvatarServiceImpl implements AvatarService{
    @Value("${path.to.avatars.folder}")
    private String avatarLic;
    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;

    public AvatarServiceImpl(AvatarRepository avatarRepository, StudentRepository studentRepository){
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;
    }


    private byte[] generateDataFor(Path filePath) throws IOException{
        try (
            InputStream is = Files.newInputStream(filePath);
            BufferedInputStream bis = new BufferedInputStream(is, 1024);
            ByteArrayOutputStream baos = new ByteArrayOutputStream()){
            BufferedImage image = ImageIO.read(bis);
            int height = image.getHeight()/(image.getWidth()/100);
            BufferedImage preview = new BufferedImage(100, height, image.getType());
            Graphics2D graphics2D = preview.createGraphics();
            graphics2D.drawImage(image,0,0,100,height, null);
            graphics2D.dispose();

            ImageIO.write(preview, getExtensions(filePath.getFileName().toString()), baos);
            return baos.toByteArray();
        }

    }
    @Override
    public void uploadAvatar(Long studentId, MultipartFile failAvatar) throws IOException {
        Student student = studentRepository.getById(studentId);
        Path pathFile = Path.of(avatarLic, student + "." + getExtensions(Objects.requireNonNull(failAvatar.getOriginalFilename())));
        Files.createDirectories(pathFile.getParent());
        Files.deleteIfExists(pathFile);
        try (
            InputStream iS = failAvatar.getInputStream();
            OutputStream oS = Files.newOutputStream(pathFile, CREATE_NEW);
            BufferedInputStream bIS = new BufferedInputStream(iS, 1024);
            BufferedOutputStream boS = new BufferedOutputStream(oS, 1024);
        ){
            bIS.transferTo(boS);
        }
        Avatar avatar = findAvatar(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(pathFile.toString());
        avatar.setFileSize(failAvatar.getSize());
        avatar.setMediaType(failAvatar.getContentType());
        avatar.setData(generateDataFor(pathFile));
        avatarRepository.save(avatar);
    }

    @Override
    public Avatar findAvatar(Long studentId) {
        return avatarRepository.findByStudentId(studentId).orElse(new Avatar());
    }
    private String getExtensions(String fileAvatarTheName){
        return fileAvatarTheName.substring(fileAvatarTheName.lastIndexOf(".") + 1);
    }

    public Page<Avatar> getAllAvatar(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page, size);
        return avatarRepository.findAll(pageable);
    }

}
