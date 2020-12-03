package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteMapper noteMapper;
    public final static String TAG_ = "NoteService";

    @Autowired
    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }
    public Note getSingleNoteByNoteId(int noteId){
        return this.noteMapper.getItemByItemId(noteId);
    }
    public List<Note> getAllUserNotes(int userId){
        return this.noteMapper.getAll(userId);
    }

    public int editNoteByNoteObject(Note update){
        return this.noteMapper.update(update);
    }

    public int deleteNote(int noteId){
        return this.noteMapper.delete(noteId);
    }

    public int deleteAll() {return this.noteMapper.deleteAll();}
    public int addNote(Note add){
        return this.noteMapper.insert(add);
    }



}
