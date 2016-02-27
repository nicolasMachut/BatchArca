package dao;

import java.util.List;

/**
 * Created by nicolas on 27/02/16.
 */
public interface DAO<T> {

    void save (List<T> data);
    void save (T data);

    Object get(T data);
}
