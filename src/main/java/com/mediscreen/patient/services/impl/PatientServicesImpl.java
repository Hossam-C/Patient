package com.mediscreen.patient.services.impl;

import com.mediscreen.patient.DTO.PatientDTO;
import com.mediscreen.patient.domain.Patient;
import com.mediscreen.patient.repositories.PatientRepository;
import com.mediscreen.patient.services.PatientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Transactional
@Service
public class PatientServicesImpl implements PatientServices {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public PatientDTO getPatient(Integer id) {

        Patient patient = new Patient();
        PatientDTO patientDTO = new PatientDTO();
        patient =  patientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        patientDTO.setId(patient.getId());
        patientDTO.setPrenom(patient.getPrenom());
        patientDTO.setNom(patient.getNom());
        patientDTO.setDateDeNaissance(patient.getDateDeNaissance());
        patientDTO.setGenre(patient.getGenre());
        patientDTO.setAdressePostale(patient.getAdressePostale());
        patientDTO.setNumeroDeTelephone(patient.getNumeroDeTelephone());

        return patientDTO;

    }

    @Override
    public List<PatientDTO> getPatientList(){

        List<Patient> patientList;
        List<PatientDTO> patientListDTO = new ArrayList<>();

        patientList = patientRepository.findAll();

        for (Patient patient:patientList){

            PatientDTO patientDTO = new PatientDTO();

            patientDTO.setId(patient.getId());
            patientDTO.setPrenom(patient.getPrenom());
            patientDTO.setNom(patient.getNom());
            patientDTO.setDateDeNaissance(patient.getDateDeNaissance());
            patientDTO.setGenre(patient.getGenre());
            patientDTO.setAdressePostale(patient.getAdressePostale());
            patientDTO.setNumeroDeTelephone(patient.getNumeroDeTelephone());

            patientListDTO.add(patientDTO);

        }


        return patientListDTO;
    }

    @Override
    public PatientDTO savePatient(PatientDTO patientDTO){

        Patient patient = new Patient();

        patient.setPrenom(patientDTO.getPrenom());
        patient.setNom(patientDTO.getNom());
        patient.setDateDeNaissance(patientDTO.getDateDeNaissance());
        patient.setGenre(patientDTO.getGenre());
        patient.setAdressePostale(patientDTO.getAdressePostale());
        patient.setNumeroDeTelephone(patientDTO.getNumeroDeTelephone());

        patientRepository.save(patient);

        return patientDTO;


    }

    @Override
    public PatientDTO updatePatient(PatientDTO patientDTO){

        Patient patient = new Patient();

        patient.setId(patientDTO.getId());
        patient.setPrenom(patientDTO.getPrenom());
        patient.setNom(patientDTO.getNom());
        patient.setDateDeNaissance(patientDTO.getDateDeNaissance());
        patient.setGenre(patientDTO.getGenre());
        patient.setAdressePostale(patientDTO.getAdressePostale());
        patient.setNumeroDeTelephone(patientDTO.getNumeroDeTelephone());

        patientRepository.save(patient);

        return patientDTO;

    }

    @Override
    public PatientDTO deletePatient(Integer id) {

        Patient patient = patientRepository.getById(id);
        PatientDTO patientDTO = new PatientDTO(patient.getId(),patient.getPrenom(),patient.getNom(),patient.getDateDeNaissance(),patient.getGenre(),patient.getAdressePostale(),patient.getNumeroDeTelephone());
        patientRepository.deleteById(id);
        return patientDTO;

    }

    @Override
    public List<PatientDTO> getPatientByName(String name){

        List<Patient> patientList = patientRepository.findByNom(name);
        List<PatientDTO> patientDTOList = new ArrayList<>();

        for (Patient patient : patientList){
            PatientDTO patientDTO = new PatientDTO();
            patientDTO.setId(patient.getId());
            patientDTO.setPrenom(patient.getPrenom());
            patientDTO.setNom(patient.getNom());
            patientDTO.setDateDeNaissance(patient.getDateDeNaissance());
            patientDTO.setGenre(patient.getGenre());
            patientDTO.setAdressePostale(patient.getAdressePostale());
            patientDTO.setNumeroDeTelephone(patient.getNumeroDeTelephone());

            patientDTOList.add(patientDTO);
        }

        return patientDTOList;

    }
}
