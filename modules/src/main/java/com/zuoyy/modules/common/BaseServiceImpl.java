package com.zuoyy.modules.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    private BaseRepository<T,String> baseRepository;

    @Override
    public T findById(String id) {
        Optional<T> optional =  baseRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    @Transactional
    public T save(T t) {
        return baseRepository.save(t);
    }

    @Override
    @Transactional
    public List<T> save(List<T> list) {
        return baseRepository.saveAll(list);
    }

    @Override
    @Transactional
    public void delete(T t) {
        baseRepository.delete(t);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        baseRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        baseRepository.deleteAll();
    }

    @Override
    public List<T> getByQuery(Specification<T> query) {
        return baseRepository.findAll(query);
    }

    @Override
    public List<T> getByQuery(Specification<T> query, Sort sort) {
        return baseRepository.findAll(query,sort);
    }
}
