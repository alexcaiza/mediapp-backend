package com.mitocode.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mitocode.dto.ExamDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Exam;
import com.mitocode.service.IExamService;

@RestController
@RequestMapping("/exams")
public class ExamController {

	@Autowired
    private IExamService service;

    @Autowired
    private ModelMapper mapper;
    
    
	
    @GetMapping
    public ResponseEntity<List<ExamDTO>> findAll() {
        List<ExamDTO> list = service.findAll().stream().map(p -> mapper.map(p, ExamDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
	
    @GetMapping("/{id}")
    public ResponseEntity<ExamDTO> findById(@PathVariable("id") Integer id) {
        ExamDTO dtoResponse;
        Exam obj = service.findById(id);
        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        } else {
            dtoResponse = mapper.map(obj, ExamDTO.class);
        }
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }
	
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ExamDTO dto) {
        Exam p = service.save(mapper.map(dto, Exam.class));
        //localhost:8080/patients/3
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(p.getIdExam()).toUri();
        return ResponseEntity.created(location).build();
    }
	
    @PutMapping
    public ResponseEntity<Exam> update(@Valid @RequestBody ExamDTO dto) {
        Exam obj = service.findById(dto.getIdExam());
        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + dto.getIdExam());
        }

        return new ResponseEntity<>(service.update(mapper.map(dto, Exam.class)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
        Exam obj = service.findById(id);
        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        } else {
            service.delete(id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
    @GetMapping("/hateoas/{id}")
    public EntityModel<ExamDTO> findByIdHateoas(@PathVariable("id") Integer id) {
        ExamDTO dtoResponse;
        Exam obj = service.findById(id);
        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        } else {
            dtoResponse = mapper.map(obj, ExamDTO.class);
        }
        
        EntityModel<ExamDTO> resource = EntityModel.of(dtoResponse);
        
        WebMvcLinkBuilder link1 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findById(id));
        
        resource.add(link1.withRel("patient-info1"));
        
        return resource;
    }
}
