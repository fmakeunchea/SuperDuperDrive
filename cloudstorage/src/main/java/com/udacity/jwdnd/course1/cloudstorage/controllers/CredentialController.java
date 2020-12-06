package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/credential")
public class CredentialController {
    public final static String TAG_ = "CredentialController";
    private final CredentialService credentialService;
    private final EncryptionService encryptionService;

    @Autowired
    public CredentialController(CredentialService credentialService, EncryptionService encryptionService) {
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @PutMapping("/add")
    public String postCredential(CredentialForm credentialForm, Model model, HttpSession session){

        int userId = (int)session.getAttribute("userId");
        String errorMsgstr = "";
        if(credentialForm.getCredentialId()==null || credentialForm.getCredentialId().equals("")){

            Credential credential = new Credential(credentialForm.getUrl(), credentialForm.getUsername(), credentialForm.getPassword(), userId);
            int addRow = this.credentialService.addCredential(credential);
            if(addRow == 1){

                model.addAttribute("successResult", true);
            }else{

                model.addAttribute("errorResult", true);
                errorMsgstr = "New credential failed to add";
                model.addAttribute("errorResultMessage", errorMsgstr);
            }
        }else{

            Credential credential = new Credential(Integer.parseInt(credentialForm.getCredentialId()), credentialForm.getUrl(), credentialForm.getUsername(), credentialForm.getPassword(), userId);
            int updateRow =  this.credentialService.editCredential(credential);
            if(updateRow == 1){
                model.addAttribute("successResult", true);

            }else{
                model.addAttribute("errorResult", true);

                errorMsgstr = "Note failed to update/edit";
                model.addAttribute("errorResultMessage", errorMsgstr);
            }
        }
        model.addAttribute("credentialForm", new CredentialForm());
        return "result";
    }

    @GetMapping("/delete")
    public String deleteCredential(@RequestParam(name="credentialId") String credentialId, Model model){

        int delRow = this.credentialService.deleteCredential(Integer.parseInt(credentialId));
        if(delRow == 1){
            model.addAttribute("successResult", true);
        }else{
            model.addAttribute("errorResult", true);
        }
        return "result";
    }

}

