package com.udacity.jwdnd.course1.cloudstorage.models;

import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Data
@ToString(includeFieldNames = true)
public class FileForm {
    private MultipartFile fileEntity;
    private String fileId;
    public FileForm(MultipartFile fileEntity) {
        this.fileEntity = fileEntity;
    }

    public FileForm(MultipartFile fileEntity, String fileId) {
        this.fileEntity = fileEntity;
        this.fileId = fileId;
    }

    public FileForm() {
    }

    public MultipartFile getFileEntity() {
        return fileEntity;
    }

    public void setFileEntity(MultipartFile fileEntity) {
        this.fileEntity = fileEntity;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}