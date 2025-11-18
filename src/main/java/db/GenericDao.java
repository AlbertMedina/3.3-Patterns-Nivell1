package db;

import java.util.List;

public interface GenericDao<T> {
    T findById(int id);
    List<T> findAll();
    boolean insert(T element);
    boolean update(T element);
    boolean delete (int id);
}
