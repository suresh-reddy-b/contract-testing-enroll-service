package com.testannotation.enrollservice.client;

import com.testannotation.enrollservice.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class StudentClient {
    private RestTemplate restTemplate;
    private final String studentServiceURL;

    @Autowired
    public StudentClient(@Value("${student-service.url}") final String studentServiceURL) {
        System.out.println("Inside StudentClient: "+studentServiceURL);
        this.studentServiceURL = studentServiceURL;
        restTemplate = new RestTemplate();
    }

    public ResponseEntity<Student> searchStudent(String ID){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(studentServiceURL)
                .path("/student")
                .queryParam("ID", ID);
        System.out.println("Path: "+builder.toUriString());

        ResponseEntity<Student> responseEntity = restTemplate.getForEntity(builder.toUriString(), Student.class);
        return responseEntity;
    }

    public ResponseEntity<Student> enrollStudent(String ID){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(studentServiceURL)
                .path("/student")
                .queryParam("ID", ID);
        System.out.println("Path: "+builder.toUriString());

        ResponseEntity<Student> responseEntity = restTemplate.getForEntity(builder.toUriString(), Student.class);
        return responseEntity;
    }



}
