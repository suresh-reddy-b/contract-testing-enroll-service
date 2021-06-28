package com.testannotation.appointmentservice.service;

import com.testannotation.appointmentservice.model.Appointment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {

    private static List<Appointment> appointments = new ArrayList<>();
    public Appointment createAppointment(Appointment appointment) {
        long id = (long) (Math.floor(Math.random() * (9*Math.pow(10, 5))) + Math.pow(10, 5));
        appointment.setAppointmentID(Long.toString(id));
        return appointment;
    }
}
