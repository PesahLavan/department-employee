package com.belyaev.model;

import java.io.Serializable;

/**
 * @author Pavel Belyaev
 * @since 26-Oct-17
 */

public class Department implements Serializable {
    private static final long serialVersionUID = 748392356L;

    protected long id;
    protected String name;

    public void setId(int id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

}
