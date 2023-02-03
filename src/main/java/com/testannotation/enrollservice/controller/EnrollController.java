package com.testannotation.enrollservice.controller;

import com.testannotation.enrollservice.client.StudentClient;
import com.testannotation.enrollservice.model.Enroll;
import com.testannotation.enrollservice.model.Student;
import com.testannotation.enrollservice.service.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class EnrollController {
    @Autowired
    private EnrollService enrollService;

    @Autowired
    private StudentClient studentClient;

    @PostMapping(value = "/enroll-service/enroll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Enroll> enrollNewStudent(@RequestBody Enroll enroll){
        ResponseEntity<Student> response = studentClient.searchStudent(enroll.getID());
        if(response.getStatusCode().equals(404)){//STUDENT_NOT_FOUND
            return ResponseEntity.notFound().build(); }
        enroll = enrollService.createCourse(enroll);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().queryParam("id", enroll.getEnrollID())
                .build().toUri();
        return ResponseEntity.created(location).body(enroll);
    }
}
