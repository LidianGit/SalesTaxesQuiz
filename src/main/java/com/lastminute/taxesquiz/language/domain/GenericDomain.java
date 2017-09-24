package com.lastminute.taxesquiz.language.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public class GenericDomain implements Serializable{

    private static final long serialVersionUID = -7235243561845618670L;

    protected String code;
    protected String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GenericDomain code(String code){
        this.code = code;
        return this;
    }

    public GenericDomain description(String description){
        this.description = description;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        GenericDomain that = (GenericDomain) o;

        return new EqualsBuilder()
                .append(code, that.code)
                .append(description, that.description)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(code)
                .append(description)
                .toHashCode();
    }
}
