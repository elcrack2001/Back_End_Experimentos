package com.example.jobagapi.cucumber;

import com.example.jobagapi.domain.model.Employeer;
import com.example.jobagapi.domain.model.JobOffer;
import com.example.jobagapi.domain.model.Postulant;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ProfessionalProfileAddinSkillsSteps {

    @LocalServerPort
    private RestTemplate restTemplate = new RestTemplate();
    private String postUrl="http://localhost:8080";

    Postulant postulantId = new Postulant(100L, "mark", "schmidt", "prieto@gmail.com", 909090334L, "troya", "DNI", "Casado");

    public String randomString() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        return generatedString;
    }

    @Given("I want my professionalprofile")
    public void i_want_my_professionalprofile() {
        String url = postUrl + "/api/postulants/" + postulantId.getId() + "/profiles";
        String allJobs=restTemplate.getForObject(url, String.class);
        log.info(allJobs);
        assertTrue(!allJobs.isEmpty());
    }

    @Given("I add my new skill {string}")
    public void i_add_my_new_skill(String string) {
        String url=postUrl + "/api/postulants/" + postulantId.getId() + "/profiles";

        LocalDate data=LocalDate.now();

        log.info(string);

        Postulant postulant = new Postulant();


        postulant.setId(postulantId.getId());
        postulant.setFirstname(postulantId.getFirstname());
        postulant.setLastname(postulantId.getLastname());
        postulant.setEmail(postulantId.getEmail());
        postulant.setNumber(postulantId.getNumber());
        postulant.setPassword(postulantId.getPassword());
        postulant.setDocument(postulantId.getDocument());
        postulant.setCivil_status(postulantId.getCivil_status());
    }
}
