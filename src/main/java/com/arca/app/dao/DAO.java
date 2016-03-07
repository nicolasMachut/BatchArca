package com.arca.app.dao;

/**
 * Created by nicolas on 27/02/16.
 */
public interface DAO<T, K> {

    void save (T data);

    K getOne(T data);
}
