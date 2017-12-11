package com.belyaev.form;

import java.io.Serializable;

/**
 * @author Pavel Belyaev
 * @since 27-Nov-17
 */
public class DepartmentForm implements Serializable {
    private static final long serialVersionUID = 748392356L;

    private String id;
    private String name;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
