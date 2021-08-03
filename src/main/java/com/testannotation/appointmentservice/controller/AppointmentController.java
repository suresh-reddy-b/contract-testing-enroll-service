package com.testannotation.appointmentservice.controller;

import com.testannotation.appointmentservice.client.PatientClient;
import com.testannotation.appointmentservice.model.Appointment;
import com.testannotation.appointmentservice.model.Patient;
import com.testannotation.appointmentservice.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientClient patientClient;

    @PostMapping(value = "/appointment-service/appointment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Appointment> addNewPatient(@RequestBody Appointment appointment){

        ResponseEntity<Patient> response = patientClient.searchPatient(appointment.getMRN());

        if(response.getStatusCode().equals(404)){
            //PATIENT_NOT_FOUND
            return ResponseEntity.notFound().build();
        }
        appointment = appointmentService.createAppointment(appointment);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().queryParam("id", appointment.getAppointmentID())
                .build().toUri();
        return ResponseEntity.created(location).body(appointment);
    }
}
