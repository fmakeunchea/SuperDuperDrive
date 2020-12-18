package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
@Controller
public class HomeController {
    private UserService userService;
    private NoteService noteService;
    private FileService fileService;
    private CredentialService credentialService;
    private EncryptionService encryptionService;

    public HomeController(UserService userService, NoteService noteService, FileService fileService,
                          CredentialService credentialService, EncryptionService encryptionService) {
        super();
        this.userService = userService;
        this.noteService = noteService;
        this.fileService = fileService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @GetMapping("/home")
    public String getHome(Model model, @ModelAttribute("formCredential") Credential credential,
                          @ModelAttribute("formNote") Note note, Authentication auth) {
        User user = userService.getUser(auth.getName());
        model.addAttribute("fileList", fileService.listAllFiles(user.getUserid()));
        model.addAttribute("noteList", noteService.getAllNotes(user.getUserid()));
        model.addAttribute("credentialList", credentialService.getAllCredentials(user.getUserid()));

        return "home";
    }

}