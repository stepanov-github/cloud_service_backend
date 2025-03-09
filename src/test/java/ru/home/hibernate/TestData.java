package ru.home.hibernate;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.multipart.MultipartFile;
import ru.home.hibernate.dto.request.AuthenticationRequest;
import ru.home.hibernate.dto.response.AuthenticationResponse;
import ru.home.hibernate.dto.response.FileResponse;
import ru.home.hibernate.entity.FileEntity;
import ru.home.hibernate.entity.UserEntity;

import java.util.List;

public class TestData {

    public static final String TOKEN_1 = "Token1";
    public static final String TOKEN_2 = "Token2";
    public static final String BEARER_TOKEN = "Bearer Token";


    public static final String USERNAME_1 = "Username1";
    public static final String PASSWORD_1 = "Password1";
    public static final String ROLE_1 = "ROLE1";
    public static final UserEntity USER_1 = new UserEntity(USERNAME_1, PASSWORD_1, ROLE_1);

    public static final String USERNAME_2 = "Username2";
    public static final String PASSWORD_2 = "Password2";
    public static final String ROLE_2 = "ROLE2";
    public static final UserEntity USER_2 = new UserEntity(USERNAME_2, PASSWORD_2, ROLE_2);

    public static final String FILENAME_1 = "Filename1";
    public static final String FILENAME_2 = "Filename2";
    public static final byte[] FILE_CONTENT_1 = FILENAME_1.getBytes();
    public static final byte[] FILE_CONTENT_2 = FILENAME_2.getBytes();
    public static final MultipartFile MULTIPART_FILE = new MockMultipartFile(FILENAME_1, FILE_CONTENT_1);
    public static final String FILENAME_EMPTY = "";
    public static final FileEntity FILE_1 = new FileEntity(FILENAME_1, FILE_CONTENT_1);
    public static final FileEntity FILE_2 = new FileEntity(FILENAME_2, FILE_CONTENT_2);
    public static final List<FileEntity> FILE_LIST = List.of(FILE_1,FILE_2);
    public static final int LIMIT_FILES = 100;
    public static final int LIMIT_BAD = 0;
    public static final FileResponse FILE_RESPONSE_1 = new FileResponse(FILE_1.getFilename(),FILE_1.getFileContent().length);
    public static final FileResponse FILE_RESPONSE_2 = new FileResponse(FILE_2.getFilename(),FILE_2.getFileContent().length);
    public static final List<FileResponse> FILE_RESPONSE_LIST = List.of(FILE_RESPONSE_1,FILE_RESPONSE_2);


    public static final AuthenticationRequest AUTHENTICATION_REQUEST = new AuthenticationRequest(USERNAME_1, PASSWORD_1);
    public static final AuthenticationResponse AUTHENTICATION_RESPONSE = new AuthenticationResponse(TOKEN_1);

    public static final UsernamePasswordAuthenticationToken USERNAME_PASSWORD_AUTHENTICATION_TOKEN = new UsernamePasswordAuthenticationToken(USERNAME_1, PASSWORD_1);
}
