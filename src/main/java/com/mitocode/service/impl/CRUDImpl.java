package com.mitocode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.IPatientRepo;
import com.mitocode.service.ICRUD;

@Service
public abstract class CRUDImpl<T, ID> implements ICRUD<T, ID> {
	
	@Autowired
	IPatientRepo patientRepo;
	
	protected abstract IGenericRepo<T, ID> getRepo();
	

	@Override
    public T save(T t) {
        return getRepo().save(t);
    }

    @Override
    public T update(T t) {
        return getRepo().save(t);
    }

    @Override
    public T findById(ID id) {
        return getRepo().findById(id).orElse(null);
    }

    @Override
    public List<T> findAll() {
        return getRepo().findAll();
    }

    @Override
    public void delete(ID id) {
        getRepo().deleteById(id);
    }
}
