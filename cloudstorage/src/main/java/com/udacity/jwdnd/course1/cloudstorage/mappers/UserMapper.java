package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper extends Condition {

        String getUserByUsername = "SELECT * FROM USERS WHERE  username= #{username}";
        String insertByUserObj = "INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES(#{username}, #{salt}, #{password}, #{firstName}, #{lastName})";
        String deleteById = "DELETE FROM USERS WHERE userid = #{userId}";
        String deleteAll = "DELETE FROM USERS";

        @Select(getUserByUsername)
        User getUser(String username);

        @Delete(deleteById)
        int delete(Integer userId);

        @Delete((deleteAll))
        int deleteAll();

        @Override
        @Insert(insertByUserObj)
        @Options(useGeneratedKeys = true, keyProperty = "userId")
        int insert(Object add);

}
