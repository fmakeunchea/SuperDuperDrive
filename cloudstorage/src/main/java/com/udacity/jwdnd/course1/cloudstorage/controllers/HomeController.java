package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.*;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller

public class HomeController {
    public final static String TAG_ = "HomeController";
    private final NoteService noteService;
    private final UserService userService;
    private final CredentialService credentialService;
    private final FileService fileService;
    private EncryptionService encryptionService;

    @Autowired
    public HomeController(NoteService noteService, UserService userService, CredentialService credentialService, FileService fileService, EncryptionService encryptionService) {
        this.noteService = noteService;
        this.userService = userService;
        this.credentialService = credentialService;
        this.fileService = fileService;
        this.encryptionService = encryptionService;
    }

    @GetMapping(value = {"/", "/home"})
    public String homeView(Authentication authentication, Model model, HttpSession session) {


        Integer userId = null;
        if (session.getAttribute("userId") == null) {
            String username = authentication.getName();
            userId = Integer.valueOf(userService.getUser(username).getUserId());
            session.setAttribute("userId", userId);
        } else {
            userId = Integer.valueOf((int) session.getAttribute("userId"));
        }


        List<Note> notes = noteService.getAllUserNotes(userId.intValue());
        List<Credential> credentials = credentialService.getAllUserCredentials(userId.intValue());
        List<File> files = fileService.getFilesByUserId(userId.intValue());
        model.addAttribute("notes", notes);
        model.addAttribute("credentials", credentials);
        model.addAttribute("files", files);

        model.addAttribute("noteForm", new NoteForm());
        model.addAttribute("credentialForm", new CredentialForm());
        model.addAttribute("fileForm", new FileForm());
        model.addAttribute("enS", encryptionService);

        return "home";
    }
}