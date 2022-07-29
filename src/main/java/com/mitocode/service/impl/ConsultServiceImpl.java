package com.mitocode.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Consult;
import com.mitocode.model.Exam;
import com.mitocode.repo.IConsultExamRepo;
import com.mitocode.repo.IConsultRepo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.IConsultService;


@Service
public class ConsultServiceImpl extends CRUDImpl<Consult, Integer> implements IConsultService {

    @Autowired
    private IConsultRepo repo;
    
    @Autowired
    private IConsultExamRepo ceRepo;

    @Override
    protected IGenericRepo<Consult, Integer> getRepo() {
        return repo;
    }
    
    /**
     * 
     */
    @Transactional
    @Override    
    public Consult saveTransactional(Consult consult, List<Exam> exams) {
    	// Consult + ConsultDetail
    	consult.getDetails().forEach(det->det.setConsult(consult));
    	repo.save(consult);
    	
    	exams.forEach(ex-> ceRepo.saveExam(consult.getIdConsult(), ex.getIdExam()));
    	
    	return consult;
    }
}
