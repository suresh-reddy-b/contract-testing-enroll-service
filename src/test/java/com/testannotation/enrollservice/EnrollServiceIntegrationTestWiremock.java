package com.testannotation.enrollservice;

import com.testannotation.enrollservice.client.StudentClient;
import com.testannotation.enrollservice.model.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWireMock (port = 9090)
public class EnrollServiceIntegrationTestWiremock {
    @Autowired
    StudentClient studentClient;

    @Before
    public void createStub(){
        stubFor(get(urlPathEqualTo("/student-service/student"))
                .withQueryParam("ID", equalTo("2020091701"))
                .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")
                    .withBody("{\n"+
                            "\"firstName\": \"John\",\n"+
                            "\"lastName\": \"Smart\",\n"+
                            "\"birthDate\": \"02/02/1997\",\n"+
                            "\"registration\": \"04/12/2009\",\n"+
                            "\"address\": \"15 Foreshore Road, Philadelphia, PA, 19101\",\n"+
                            "\"id\": \"2020091701\"\n"+
                             "}")
                )
        );
    }

    @Test
    public void enrollExistingStudent(){
        //Given
        String ID = "2020091701";

        //When
        ResponseEntity<Student> response = studentClient.searchStudent(ID);

        //Then
        then(response.getStatusCode().equals(200));
        then(response.getBody().getID().equals(ID));
        then(response.getBody().getRegistrationDate().equals("04/12/2009"));
    }

}
