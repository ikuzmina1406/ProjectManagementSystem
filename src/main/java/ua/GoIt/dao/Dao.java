package ua.GoIt.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends Identity> {


    Optional<T> create(T entity);

    List<T> read();

    void update(T entity);

    void delete(T entity);

    Optional<T> get(Long id);


}
