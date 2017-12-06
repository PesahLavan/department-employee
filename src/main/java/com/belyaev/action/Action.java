package com.belyaev.action;

import java.util.List;

/**
 * TODO: comment
 *
 * @author Pavel Belyaev
 * @since 28-Nov-17
 */
public interface Action<T> {

    public List<T> getEmpAll();

    public void insert(T emp);

    public void update(T emp);

    public void delete(T emp);

    public T get(Integer id);
}
