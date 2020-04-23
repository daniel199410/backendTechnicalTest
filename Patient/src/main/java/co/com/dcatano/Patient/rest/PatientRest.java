package co.com.dcatano.Patient.rest;

import co.com.dcatano.Patient.model.Patient;
import co.com.dcatano.Patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient")
public class PatientRest {

    @Autowired
    private PatientService patientService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Patient save(@RequestBody Patient patient) {
        return patientService.save(patient);
    }
}
