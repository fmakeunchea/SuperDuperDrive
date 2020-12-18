package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FileService {
    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        super();
        this.fileMapper = fileMapper;
    }

    public boolean isFileNameAvailable(String filename) {
        return fileMapper.getFileByName(filename) == null;

    }

    public File getFile(Integer fileid) {
        return fileMapper.getFileById(fileid);
    }

    public File getFile(String filename) {
        return fileMapper.getFileByName(filename);
    }

    public Integer addFile(File file) {
        return fileMapper.insert(file);
    }

    public void deleteFile(Integer fileid) {
        fileMapper.delete(fileid);
    }

    public List<File> listAllFiles(Integer userid) {
        return fileMapper.getAllFiles(userid);
    }
}
