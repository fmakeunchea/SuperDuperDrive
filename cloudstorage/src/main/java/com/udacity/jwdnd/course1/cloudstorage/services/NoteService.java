package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteMapper noteMapper;
    private UserService userService;
    public NoteService(NoteMapper noteMapper, UserService userService) {
        super();
        this.noteMapper = noteMapper;
        this.userService = userService;
    }

    public Note getNote(Integer noteid) {
        return noteMapper.getNoteById(noteid);
    }

    public Note getNote(String notetitle) {
        return noteMapper.getNoteByTitle(notetitle);
    }

    public boolean isNoteTitleAvailable(String notetitle) {
        return noteMapper.getNoteByTitle(notetitle) == null;
    }

    public Integer addOrUpdateNote(Note note) {
//

        if (note.getNoteid() != null)
            return noteMapper.update(note);
        else
            return noteMapper.insert(note);
    }

    public void deleteNote(Integer noteid) {
        noteMapper.delete(noteid);
    }

    public List<Note> getAllNotes(Integer userid) {
        return noteMapper.getAllNotes(userid);
    }
}