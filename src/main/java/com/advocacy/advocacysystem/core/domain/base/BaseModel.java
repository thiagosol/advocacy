package com.advocacy.advocacysystem.core.domain.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseModel<T> implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    protected Long id;

    public abstract void update(T modelUpdate);
}
