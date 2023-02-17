package com.testannotation.enrollservice.client;

import com.testannotation.enrollservice.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class StudentClient {

    private int port = 8080;
    private RestTemplate restTemplate;
    private final String studentServiceURL;

    HttpHeaders headers = new HttpHeaders();

    @Autowired
    public StudentClient(@Value("${student-service.url}") final String studentServiceURL) {
        System.out.println("Inside StudentClient: " + studentServiceURL);
        this.studentServiceURL = studentServiceURL;
        restTemplate = new RestTemplate();
    }

    public ResponseEntity<Student> searchStudent(String ID) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(studentServiceURL)
                .path("/student")
                .queryParam("ID", ID);
        System.out.println("Path: " + builder.toUriString());

        ResponseEntity<Student> responseEntity = restTemplate.getForEntity(builder.toUriString(), Student.class);
        return responseEntity;
    }


    public ResponseEntity<Student> addNewStudent(){

        Student student = new Student("John", "Carter", "02/02/1993", "04/12/2020", "15 Foreshore Road, Philadelphia, PA, 19101");

        HttpEntity<Student> entity = new HttpEntity<Student>(student, headers);

        ResponseEntity<Student> response = restTemplate.exchange(
                createURLWithPort("/student-service/student"),
                HttpMethod.POST, entity, Student.class);

        System.out.println(response.getBody());
        System.out.println(response.getStatusCode());
        return response;

    }

    /*public ResponseEntity<Student> enrollStudent(String ID) {
        need to work on

    }*/

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
