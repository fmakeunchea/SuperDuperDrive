package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper extends Condition {

    String getGetCredentialByUserId ="SELECT * FROM CREDENTIALS WHERE  userid= #{userId}";
    String getCredentialById = "SELECT * FROM CREDENTIALS WHERE  credentialid= #{credentialId}";
    String insertByUserObj = "INSERT INTO CREDENTIALS (url, username, key, password, userid) " +
            "VALUES(#{url}, #{username}, #{key}, #{password}, #{userId})";
    String deleteById = "DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId}";
    String deleteAll = "DELETE FROM CREDENTIALS";
    String updateNoteByCredentialObject =    "UPDATE CREDENTIALS SET " +
            "url = #{url}, " +
            "username = #{username}, " +
            "password = #{password} " +
            "WHERE credentialid = #{credentialId}";
    String getKeyByUserIdAndCredentialId = "SELECT key FROM CREDENTIALS WHERE  credentialid= #{credentialId}";

    @Override
    @Delete((deleteAll))
    int delete(Integer itemId);

    @Override
    @Select(getGetCredentialByUserId)
    List<Credential> getAll(Integer userId);

    @Override
    @Delete(deleteAll)
    int deleteAll();

    @Override
    @Select(getCredentialById)
    Credential getItemByItemId(Integer itemId);

    @Select(getKeyByUserIdAndCredentialId)
    String getKey(int credentialId);

    @Override
    @Insert(insertByUserObj)
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert(Object add);

    @Override
    @Update(updateNoteByCredentialObject)
    int update(Object update);

}