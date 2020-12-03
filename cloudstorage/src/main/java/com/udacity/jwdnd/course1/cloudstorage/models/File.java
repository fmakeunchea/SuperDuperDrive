package com.udacity.jwdnd.course1.cloudstorage.models;

import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Data
@ToString(includeFieldNames = true)
public class File {
    private int fileId;
    private String filename;
    private String contentType;
    private String fileSize;
    private int userId;
    private byte[] fileData;

    public File(String filename, String contentType, String filesize, int userId, byte[] fileData) {
        this.filename = filename;
        this.contentType = contentType;
        this.fileSize = filesize;
        this.userId = userId;
        this.fileData = fileData;
    }
    public File() {
    }
    public File(MultipartFile fileUpload, int userId) throws IOException {
        try{
            this.contentType= fileUpload.getContentType();
            this.fileData = fileUpload.getBytes();
            this.filename = fileUpload.getOriginalFilename();
            this.userId = userId;
            this.fileSize = Long.toString(fileUpload.getSize());
        }catch(IOException e){
            throw e;
        }
    }
    public File(int fileId, String filename, String contentType, String filesize, int userId, byte[] fileData) {
        this.fileId = fileId;
        this.filename = filename;
        this.contentType = contentType;
        this.fileSize = filesize;
        this.userId = userId;
        this.fileData = fileData;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
}
