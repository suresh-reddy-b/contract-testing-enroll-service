package com.testannotation.enrollservice;

import com.testannotation.enrollservice.client.StudentClient;
import com.testannotation.enrollservice.model.Student;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureStubRunner(ids = "com.testannotation:student-service:+:7070",
        stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class EnrollServiceIntegrationTestSCC {
    @Autowired
    StudentClient studentClient;

    @Test
    public void enrollExistingStudent(){
        // Get call to Student service --- completed
        // Post call to Enroll service --- need to work

        //Given
        String ID = "2020091701";

        //When
        ResponseEntity<Student> response = studentClient.searchStudent(ID);
        //studentClient.enrollStudent(ID);

        //Then
        then(response.getStatusCode().equals(200));
        then(response.getBody().getID().equals(ID));
        then(response.getBody().getRegistrationDate().equals("04/12/2009"));
    }

    @Test
    public void enrollNewStudent() {
        // Post to student service --- done
        // Get the student object from search -- need to work
        // Post to Enroll service by using student object -- need to work

        ResponseEntity<Student> responseNewStudent = studentClient.addNewStudent();
        String id = responseNewStudent.getBody().getID();
        System.out.println(id);
        System.out.println(responseNewStudent.getBody());

    }

}
