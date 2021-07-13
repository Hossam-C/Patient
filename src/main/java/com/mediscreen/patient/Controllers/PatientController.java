package com.mediscreen.patient.Controllers;

import com.mediscreen.patient.DTO.PatientDTO;
import com.mediscreen.patient.services.PatientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PatientController {

    @Autowired
    private PatientServices patientServices;


    @RequestMapping ("/")
    public String index(){
        return "MS Patient";
    }


    @RequestMapping ("/getPatient/{id}")
    public PatientDTO getPatient(@PathVariable("id") Integer id) {

        return patientServices.getPatient(id);

    }

    @RequestMapping ("/getPatientList")
    public List<PatientDTO> getPatientList() {

        return patientServices.getPatientList();

    }

    @PostMapping("/addPatient")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PatientDTO addPatient(@RequestBody PatientDTO patientDTO) {

        return patientServices.savePatient(patientDTO);

    }

    @PostMapping("/updatePatient")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PatientDTO updatePatient(@RequestBody PatientDTO patientDTO) {

        return patientServices.updatePatient(patientDTO);

    }

    @GetMapping("/deletePatient/{id}")
    public String deletePatient(@PathVariable("id") Integer id) {

        patientServices.deletePatient(id);

        return "delete done";
    }
}
