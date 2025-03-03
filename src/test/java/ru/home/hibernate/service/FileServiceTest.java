package ru.home.hibernate.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.home.hibernate.exception.BadRequestException;
import ru.home.hibernate.exception.UnauthorizedErrorException;
import ru.home.hibernate.repositiry.FileRepository;
import ru.home.hibernate.repositiry.UserRepository;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static ru.home.hibernate.TestData.*;

@ExtendWith(MockitoExtension.class)
class FileServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private FileRepository fileRepository;

    @Mock
    private InMemoryBlackListToken blackListToken;

    @InjectMocks
    private FileService fileService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void uploadFile() throws IOException {
        assertTrue(fileService.uploadFile(TOKEN_1, FILENAME_1, MULTIPART_FILE));
    }

    @Test
    void uploadFileUnauthorized() {
        Mockito.when(blackListToken.isBlacklisted(TOKEN_2)).thenReturn(true);
        assertThrows(UnauthorizedErrorException.class, () -> fileService.uploadFile(TOKEN_2, FILENAME_1, MULTIPART_FILE));
    }

    @Test
    void deleteFile() {
        assertTrue(fileService.deleteFile(BEARER_TOKEN, FILENAME_1));
    }

    @Test
    void deleteFileUnauthorized() {
        Mockito.when(blackListToken.isBlacklisted(TOKEN_2)).thenReturn(true);
        assertThrows(UnauthorizedErrorException.class, () -> fileService.deleteFile(TOKEN_2, FILENAME_1));
    }

    @Test
    void deleteFileBadRequestException() {
//        Mockito.when(storageFileRepository.findByUserAndFilename(USER_1, FILENAME_1)).thenReturn(STORAGE_FILE_1);
        assertThrows(BadRequestException.class, () -> fileService.deleteFile(TOKEN_1, FILENAME_EMPTY));
    }

    @Test
    void downloadFile() {
        Mockito.when(fileRepository.findById(FILENAME_1)).thenReturn(Optional.of(FILE_1));
        assertEquals(FILE_CONTENT_1, fileService.downloadFile(TOKEN_1, FILENAME_1));
    }

    @Test
    void downloadFileUnauthorized() {
        Mockito.when(blackListToken.isBlacklisted(TOKEN_2)).thenReturn(true);
        assertThrows(UnauthorizedErrorException.class, () -> fileService.downloadFile(TOKEN_2, FILENAME_1));
    }

    @Test
    void downloadFileBadRequestException() {
        assertThrows(BadRequestException.class, () -> fileService.downloadFile(TOKEN_1, FILENAME_EMPTY));
    }

    @Test
    void editFileName() {
        Mockito.when(fileRepository.editFileName(FILENAME_1, FILENAME_2)).thenReturn(1);
        assertTrue(fileService.editFileName(TOKEN_1, FILENAME_1, FILENAME_2));
    }

    @Test
    void editFileNameUnauthorized() {
        Mockito.when(blackListToken.isBlacklisted(TOKEN_2)).thenReturn(true);
        assertThrows(UnauthorizedErrorException.class, () -> fileService.editFileName(TOKEN_2, FILENAME_1, FILENAME_2));
    }

    @Test
    void editFileNameBadRequestException() {
        assertThrows(BadRequestException.class, () -> fileService.editFileName(TOKEN_1, FILENAME_1,FILENAME_EMPTY));
        assertThrows(BadRequestException.class, () -> fileService.editFileName(TOKEN_1, FILENAME_EMPTY,FILENAME_2));
    }

    @Test
    void getFileList() {
        Mockito.when(fileRepository.getFiles(LIMIT_FILES)).thenReturn(FILE_LIST);
        assertEquals(FILE_RESPONSE_LIST, fileService.getFileList(TOKEN_1, LIMIT_FILES));
    }

    @Test
    void getFileListUnauthorized() {
        Mockito.when(blackListToken.isBlacklisted(TOKEN_2)).thenReturn(true);
        assertThrows(UnauthorizedErrorException.class, () -> fileService.getFileList(TOKEN_2, LIMIT_FILES));
    }

    @Test
    void getFileListBadRequestException() {
        assertThrows(BadRequestException.class, () -> fileService.getFileList(TOKEN_1, LIMIT_BAD));
    }

}