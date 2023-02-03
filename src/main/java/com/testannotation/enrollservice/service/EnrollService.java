package com.testannotation.enrollservice.service;

import com.testannotation.enrollservice.model.Enroll;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EnrollService {

    private static List<Enroll> cource = new ArrayList<>();

    static {
        Enroll dataScience = new Enroll("2023020301", "ds001", "3M", "Data science");
        Enroll sfdc = new Enroll("2023020301", "sfdb", "6M", "Salesforce CRM");
        cource.addAll(Arrays.asList(dataScience, sfdc));
    }

    public Enroll createCourse(Enroll courses) {
        long id = (long) (Math.floor(Math.random() * (9*Math.pow(10, 5))) + Math.pow(10, 5));
        courses.setEnrollID(Long.toString(id));
        cource.add(courses);
        return courses;
    }
}
