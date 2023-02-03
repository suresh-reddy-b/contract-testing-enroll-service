package com.testannotation.enrollservice;

import com.testannotation.enrollservice.client.StudentClient;
import com.testannotation.enrollservice.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
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
        //Given
        String ID = "2020091701";

        //When
        ResponseEntity<Student> response = studentClient.searchStudent(ID);
        studentClient.enrollStudent(ID);

        //Then
        then(response.getStatusCode().equals(200));
        then(response.getBody().getID().equals(ID));
        then(response.getBody().getRegistrationDate().equals("04/12/2009"));
    }

}
