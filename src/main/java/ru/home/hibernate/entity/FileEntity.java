package ru.home.hibernate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "files")
@Data
public class FileEntity {

    @Id
    @Column(name = "filename", nullable = false, unique = true)
    private String filename;

    @Column(name = "filecontent", nullable = false)
    private byte[] fileContent;

    public FileEntity() {
    }

    public FileEntity(String filename, byte[] fileContent) {
        this.filename = filename;
        this.fileContent = fileContent;
    }

    public String getFilename() {
        return filename;
    }

    public byte[] getFileContent() {
        return fileContent;
    }
}
