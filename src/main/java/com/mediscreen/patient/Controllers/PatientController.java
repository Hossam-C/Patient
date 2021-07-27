package com.mediscreen.patient.Controllers;

import com.mediscreen.patient.DTO.PatientDTO;
import com.mediscreen.patient.Exceptions.ControllerException;
import com.mediscreen.patient.domain.Patient;
import com.mediscreen.patient.repositories.PatientRepository;
import com.mediscreen.patient.services.PatientServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class PatientController {


    private static final Logger logger = LogManager.getLogger(PatientController.class);

    @Autowired
    private PatientServices patientServices;

    @Autowired
    private PatientRepository patientRepository;


    @GetMapping ("/")
    public String index(){
        return "MS Patient";
    }


    @GetMapping ("/getPatient/{id}")
    public PatientDTO getPatient(@PathVariable("id") Integer id) throws ControllerException {

        Optional<Patient> patient = patientRepository.findById(id);

        if (!patient.isPresent()) {
            logger.error("Patient is not found");
            throw new ControllerException("Patient is not found");
        }
        else {
            logger.info( "/getPatient");
            return patientServices.getPatient(id);
        }
    }

    @GetMapping ("/getPatientList")
    public List<PatientDTO> getPatientList() {

        return patientServices.getPatientList();

    }

    @PostMapping("/addPatient")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PatientDTO addPatient(@RequestBody PatientDTO patientDTO) throws ControllerException {

        Optional<Patient> patient = Optional.ofNullable(patientRepository.findPatientByPrenomAndNomAndDateDeNaissance(patientDTO.getPrenom(), patientDTO.getNom(), patientDTO.getDateDeNaissance()));

        if (patient.isPresent()) {
            logger.error("Patient exist already");
            throw new ControllerException("Patient exist already");
        }
        else {
            return patientServices.savePatient(patientDTO);
        }

    }

    @PostMapping("/updatePatient")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PatientDTO updatePatient(@RequestBody PatientDTO patientDTO) {

        Optional<Patient> patient = patientRepository.findById(patientDTO.getId());

        if (!patient.isPresent()) {
            logger.error("Patient is not found");
            throw new ControllerException("Patient is not found");
        }
        else {
            return patientServices.updatePatient(patientDTO);
        }

    }

    @GetMapping("/deletePatient/{id}")
    public String deletePatient(@PathVariable("id") Integer id) {

        Optional<Patient> patient = patientRepository.findById(id);

        if (!patient.isPresent()) {
            logger.error("Patient is not found");
            throw new ControllerException("Patient is not found");
        }
        else {
            patientServices.deletePatient(id);
        }

        return "delete done";
    }

    @GetMapping("/patientByFamily/{name}")
    public List<PatientDTO> getPatientsByFamilyName(@PathVariable("name") String name) {

        return patientServices.getPatientByName(name);
    }
}
