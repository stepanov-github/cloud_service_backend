package ru.home.hibernate.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.home.hibernate.dto.response.FileResponse;
import ru.home.hibernate.entity.FileEntity;
import ru.home.hibernate.exception.BadRequestException;
import ru.home.hibernate.exception.InternalServerErrorException;
import ru.home.hibernate.exception.UnauthorizedErrorException;
import ru.home.hibernate.repositiry.FileRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FileService {

    private FileRepository fileRepository;
    private final InMemoryBlackListToken tokenBlacklist;

    public FileService(InMemoryBlackListToken tokenBlacklist, FileRepository fileRepository) {
        this.tokenBlacklist = tokenBlacklist;
        this.fileRepository = fileRepository;
    }

    public boolean uploadFile(String token, String filename, MultipartFile file) {

        checkToken(token);

        if (filename.isEmpty() || file.isEmpty()) {
            throw new BadRequestException("Error input data");
        }

        try {
            fileRepository.save(new FileEntity(filename, file.getBytes()));
            return true;
        } catch (Exception e) {
            throw new InternalServerErrorException("Error upload file");
        }

    }

    public boolean deleteFile(String token, String filename) {

        checkToken(token);

        if (filename.isEmpty()) {
            throw new BadRequestException("Error input data");
        }

        try {
            fileRepository.deleteById(filename);
            return true;
        } catch (RuntimeException e) {
            throw new InternalServerErrorException("Error delete file");
        }

    }

    public byte[] downloadFile(String token, String filename) {

        checkToken(token);

        if (filename.isEmpty()) {
            throw new BadRequestException("Error input data");
        }

        Optional<FileEntity> file = fileRepository.findById(filename);

        FileEntity fileEntity = file.get();

        if (fileEntity == null) {
            throw new InternalServerErrorException("Error upload file");
        }

        return fileEntity.getFileContent();

    }

    public boolean editFileName(String token, String filename, String newFilename) {

        checkToken(token);

        if (filename.isEmpty() || newFilename.isEmpty()) {
            throw new BadRequestException("Error input data");
        }

        int updatedRows = fileRepository.editFileName(filename, newFilename);

        if (updatedRows == 0) {
            throw new InternalServerErrorException("Error upload file");
        }
        return true;

    }

    public List<FileResponse> getFileList(String token, int limit) {

        checkToken(token);

        if (limit == 0) {
            throw new BadRequestException("Please enter limit files");
        }

        List<FileEntity> files = fileRepository.getFiles(limit);

//        if (files.isEmpty()) {throw new InternalServerErrorException("File list is empty");}

        return files.stream().map(file -> new FileResponse(file.getFilename(), file.getFileContent().length)).collect(Collectors.toList());

    }

    public void checkToken(String token) {
        if (tokenBlacklist.isBlacklisted(token)) {
            throw new UnauthorizedErrorException("Unauthorized error");
        }
    }
}
