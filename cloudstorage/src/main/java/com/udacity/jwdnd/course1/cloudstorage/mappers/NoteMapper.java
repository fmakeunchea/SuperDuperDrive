package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface NoteMapper extends Condition{

    String getNotesByUserId ="SELECT * FROM NOTES WHERE  userid= #{userId}";
    String insertNote = "INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})";
    String deleteNoteByNoteId = "DELETE FROM NOTES WHERE noteid = #{noteId}";
    String deleteAll = "DELETE NOTES";
    String getNoteByNoteId = "SELECT * FROM NOTES WHERE noteid=#{noteId}";
    String updateNoteByNoteObject =    "UPDATE NOTES SET " +
            "notetitle = #{noteTitle}, " +
            "notedescription = #{noteDescription} " +
            "WHERE noteid = #{noteId}";

    @Delete(deleteNoteByNoteId)
    int delete(Integer itemId);

    @Delete(deleteAll)
    int deleteAll();

    @Select(getNotesByUserId)
    List<Note> getAll(Integer userId);

    @Insert(insertNote)
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insert(Object add);

    @Select(getNoteByNoteId)
    Note getItemByItemId(Integer noteId);

    @Update(updateNoteByNoteObject)
    int update(Object update);



}

