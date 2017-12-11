package com.belyaev.action;

import java.util.List;

/**
 * @author Pavel Belyaev
 * @since 28-Nov-17
 */
public interface Action<T> {

    List<T> getEmpAll();

    void insert(T emp);

    void update(T emp);

    void delete(T emp);

    T get(Integer id);
}
