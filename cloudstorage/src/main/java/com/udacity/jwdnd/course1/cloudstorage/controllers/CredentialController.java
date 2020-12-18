package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;

@Controller
public class CredentialController {
    private CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        super();
        this.credentialService = credentialService;
    }

    @PostMapping("/credentialUpload")
    public String credentialUpload(@ModelAttribute("formCredential") Credential credential, Model model,
                                   Authentication auth) {
        Integer outcome = credentialService.addOrUpdateCredential(credential, auth);
        if (outcome > 0)
            model.addAttribute("success", true);
        else
            model.addAttribute("success", false);
        return "result";
    }

    @GetMapping("/credentialDelete")
    public String credentialDelete(@RequestParam("credentialid") Integer credentialid, Model model) {
        credentialService.deleteCredential(credentialid);
        model.addAttribute("success", true);
        return "result";
    }
}