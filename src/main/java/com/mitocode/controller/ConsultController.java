package com.mitocode.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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

import com.mitocode.dto.ConsultDTO;
import com.mitocode.dto.ConsultListExamDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Consult;
import com.mitocode.model.Exam;
import com.mitocode.service.IConsultService;

@RestController
@RequestMapping("/consults")
public class ConsultController {

	@Autowired
    private IConsultService service;

    @Autowired
    private ModelMapper mapper;
    
    
	
    @GetMapping
    public ResponseEntity<List<ConsultDTO>> findAll() {
        List<ConsultDTO> list = service.findAll().stream().map(p -> mapper.map(p, ConsultDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
	
    @GetMapping("/{id}")
    public ResponseEntity<ConsultDTO> findById(@PathVariable("id") Integer id) {
        ConsultDTO dtoResponse;
        Consult obj = service.findById(id);
        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        } else {
            dtoResponse = mapper.map(obj, ConsultDTO.class);
        }
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }
	
    /*
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ConsultDTO dto) {
        Consult p = service.save(mapper.map(dto, Consult.class));
        //localhost:8080/patients/3
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(p.getIdConsult()).toUri();
        return ResponseEntity.created(location).build();
    }
    */
    
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ConsultListExamDTO dto) {
        Consult c = mapper.map(dto.getConsult(), Consult.class);
        List<Exam> exams = mapper.map(dto.getLstExam(), new TypeToken<List<Exam>>() {}.getType());

        Consult obj = service.saveTransactional(c, exams);

        //localhost:8080/consults/3
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdConsult()).toUri();
        return ResponseEntity.created(location).build();
    }
    
	
    @PutMapping
    public ResponseEntity<Consult> update(@Valid @RequestBody ConsultDTO dto) {
        Consult obj = service.findById(dto.getIdConsult());
        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + dto.getIdConsult());
        }

        return new ResponseEntity<>(service.update(mapper.map(dto, Consult.class)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
        Consult obj = service.findById(id);
        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        } else {
            service.delete(id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
    @GetMapping("/hateoas/{id}")
    public EntityModel<ConsultDTO> findByIdHateoas(@PathVariable("id") Integer id) {
        ConsultDTO dtoResponse;
        Consult obj = service.findById(id);
        if (obj == null) {
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        } else {
            dtoResponse = mapper.map(obj, ConsultDTO.class);
        }
        
        EntityModel<ConsultDTO> resource = EntityModel.of(dtoResponse);
        
        WebMvcLinkBuilder link1 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findById(id));
        
        resource.add(link1.withRel("patient-info1"));
        
        return resource;
    }
}
