package com.udacity.jwdnd.course1.cloudstorage.mappers;

import java.util.List;
public interface Condition {

    int delete(Integer itemId);
    int deleteAll();
    List<?> getAll(Integer userId);
    int insert(Object add);
    <T> T getItemByItemId(Integer itemId);
    int update(Object update);
}
