package ru.home.hibernate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "files")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileEntity {

    @Id
    @Column(name = "filename", nullable = false, unique = true)
    private String filename;

    @Column(name = "filecontent", nullable = false)
    private byte[] fileContent;
}
