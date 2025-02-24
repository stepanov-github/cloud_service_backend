package ru.home.hibernate.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.home.hibernate.dto.request.NewFilenameRequest;
import ru.home.hibernate.dto.response.FileResponse;
import ru.home.hibernate.service.FileService;

import java.io.IOException;
import java.util.List;

@Validated
@RestController
@RequestMapping("/cloud")
@AllArgsConstructor
public class FileController {
    private FileService fileService;


    @PostMapping("/file")
    public ResponseEntity<?> uploadFile(@RequestHeader("Auth-token") String token,
                                        @RequestParam("filename") String filename,
                                        @RequestBody MultipartFile file) throws IOException {

        fileService.uploadFile(token, filename, file);

        return ResponseEntity.ok(HttpStatus.OK);

    }

    @DeleteMapping("/file")
    public ResponseEntity<?> deleteFile(@RequestHeader("auth-token")  String token,
                                        @RequestParam("filename")  String filename) {

        fileService.deleteFile(token, filename);

        return ResponseEntity.ok(HttpStatus.OK);

    }

    @GetMapping("/file")
    public ResponseEntity<?> downloadFile(@RequestHeader("auth-token") String token,
                                          @RequestParam("filename") String fileName) {

        byte[] file = fileService.downloadFile(token, fileName);

        return ResponseEntity.ok().body(new ByteArrayResource(file));

    }

    @PutMapping("/file")
    public ResponseEntity<?> editFileName(@RequestHeader("auth-token") String token,
                                          @RequestParam("filename") String filename,
                                          @RequestBody NewFilenameRequest newFilenameRequest) {

        String newFilename = newFilenameRequest.getFilename();

        fileService.editFileName(token, filename, newFilename);

        return ResponseEntity.ok(HttpStatus.OK);

    }

    @GetMapping("/list")
    public ResponseEntity<List<FileResponse>> getAllFiles(@RequestHeader("auth-token") String token,
                                                          @RequestParam(name = "limit") int limit) {

        List<FileResponse> files = fileService.getFileList(token, limit);

        return ResponseEntity.ok(files);

    }
}
