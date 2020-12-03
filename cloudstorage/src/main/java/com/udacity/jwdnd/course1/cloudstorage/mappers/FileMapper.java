package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper extends Condition {
    String getFilesByUserId ="SELECT * FROM FILES WHERE  userid= #{userId}";
    String insertFile = "INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) " +
            "VALUES(#{filename}, #{contentType}, #{fileSize}, #{userId}, #{fileData})";
    String deleteFileByNoteId = "DELETE FROM FILES WHERE fileId = #{fileId}";
    String getFileByFileId = "SELECT * FROM FILES WHERE fileId=#{fileId}";
    String updateFileByNoteObject =  "UPDATE FILES SET " +
            "filename = #{filename}, " +
            "filedata = #{fileData}, " +
            "contenttype = #{contentType}, " +
            "filesize = #{fileSize} , " +
            "userid = #{userId} " +
            "WHERE fileId = #{fileId}";
    String duplicateFile = "SELECT * FROM FILES WHERE  userid= #{userId} AND filename = #{filename}";

    String deleteAll = "DELETE FROM FILES";


    @Select(duplicateFile)
    File isDuplicateFile(int userId, String filename);

    @Delete(deleteAll)
    int deleteAll();

    @Delete(deleteFileByNoteId)
    int delete(Integer itemId);

    @Select(getFilesByUserId)
    List<File> getAll(Integer userId);

    @Select(getFileByFileId)
    File getItemByItemId(Integer fileId);

    @Override
    @Insert(insertFile)
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(Object add);

    @Override
    @Update(updateFileByNoteObject)
    int update(Object update);


}
