package ru.home.hibernate.dto.response;

public class FileResponse {
    private String filename;
    private int size;

    public FileResponse() {
    }

    public FileResponse(String filename, int size) {
        this.filename = filename;
        this.size = size;
    }

    public String getFilename() {
        return filename;
    }

    public int getSize() {
        return size;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
