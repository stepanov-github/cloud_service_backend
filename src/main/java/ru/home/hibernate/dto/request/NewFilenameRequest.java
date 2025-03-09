package ru.home.hibernate.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NewFilenameRequest {

    @NotBlank
    private String filename;

    public NewFilenameRequest() {
    }

    public NewFilenameRequest(String filename) {
        this.filename = filename;
    }

    public @NotBlank String getFilename() {
        return filename;
    }
}


