package com.testannotation.appointmentservice.client;

import com.testannotation.appointmentservice.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class PatientClient {
    private RestTemplate restTemplate;
    private final String patientServiceURL;

    @Autowired
    public PatientClient(@Value("${patient-service.url}") final String patientServiceURL) {
        System.out.println("Inside PatientClient: "+patientServiceURL);
        this.patientServiceURL = patientServiceURL;
        restTemplate = new RestTemplate();
    }

    public ResponseEntity<Patient> searchPatient(String MRN){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(patientServiceURL)
                .path("/patient")
                .queryParam("MRN", MRN);
        System.out.println("Path: "+builder.toUriString());

        ResponseEntity<Patient> responseEntity = restTemplate.getForEntity(builder.toUriString(), Patient.class);
        return responseEntity;
    }



}
