package com.zuoyy.modules.common;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface BaseService<T> {

    T findById(String id);
    T save(T t);
    List<T> save(List<T> list);
    void delete(T t);
    void deleteById(String id);
    void deleteAll();
    List<T> getByQuery(Specification<T> query);
    List<T> getByQuery(Specification<T> query, Sort sort);
}
