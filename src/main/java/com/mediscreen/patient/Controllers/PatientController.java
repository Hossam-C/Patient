package com.mediscreen.patient.Controllers;

import com.mediscreen.patient.DTO.PatientDTO;
import com.mediscreen.patient.services.PatientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class PatientController {

    @Autowired
    private PatientServices patientServices;


    @RequestMapping ("/")
    public String index(){
        return "MS Patient";
    }

    @RequestMapping ("/getPatient")
    public PatientDTO getPatient(@RequestParam Integer id) {

        return patientServices.getPatient(id);

    }

    @PostMapping("/addPatient")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PatientDTO getPatient(@RequestBody PatientDTO patientDTO) {

        return patientServices.savePatient(patientDTO);

    }

    @PostMapping("/updatePatient")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PatientDTO updatePatient(@RequestBody PatientDTO patientDTO) {

        return patientServices.updatePatient(patientDTO);

    }
}
