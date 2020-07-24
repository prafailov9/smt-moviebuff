package org.ntr.persistence.coredb;

import java.util.List;

public interface GenericDao<T> {

    T loadById(Long id);

    List<T> loadAll();

    T save(T entity);

    boolean update(T entity);

    boolean delete(T entity);

    boolean deleteById(Long id);
}
