package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Consult;
import com.mitocode.model.Exam;

public interface IConsultService extends ICRUD<Consult, Integer>{
	
	Consult saveTransactional(Consult consult, List<Exam> lstExam);

}
