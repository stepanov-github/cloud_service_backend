package ru.home.hibernate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.home.hibernate.entity.FileEntity;

@Data
//@NoArgsConstructor
@AllArgsConstructor
public class FileResponse {
    private String filename;
    private int size;
    public FileResponse(FileEntity fileEntity) {
        this.filename = fileEntity.getFilename();
        this.size = fileEntity.getFileContent().length;
    }
}
