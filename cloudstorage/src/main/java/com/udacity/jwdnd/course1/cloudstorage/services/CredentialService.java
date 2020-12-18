package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {
    private CredentialMapper credentialMapper;
    private EncryptionService encryptionService;
    private UserService userService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService,
                             UserService userService) {
        super();
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
        this.userService = userService;
    }

    public Credential getCredential(Integer credentialid) {
        return credentialMapper.getCredentialById(credentialid);
    }

    public Credential getCredential(String url) {
        return credentialMapper.getCredentialByUrl(url);
    }

    public boolean isCredentialUrlAvailable(String url) {
        return credentialMapper.getCredentialByUrl(url) == null;
    }

    public String getDecryptedPassword(Integer credentialid, Authentication auth) {
        Credential credential = credentialMapper.getCredentialById(credentialid);
        return encryptionService.decryptValue(credential.getPassword(), credential.getKey());
    }

    public Integer addOrUpdateCredential(Credential credential, Authentication auth) {
        User user = userService.getUser(auth.name());
        //User user = userService.getUser(auth.getName());

        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);
        credential.setPassword(encryptedPassword);
        credential.setKey(encodedKey);
        credential.setUserid(user.getUserid());

        if (credential.getCredentialid() != null)
            return this.credentialMapper.update(credential);
        else
            return this.credentialMapper.insert(credential);
    }

    public void deleteCredential(Integer credentialid) {
        credentialMapper.delete(credentialid);
    }

    public List<Credential> getAllCredentials(Integer userid) {
        return credentialMapper.getAllCredentials(userid);
    }
}

