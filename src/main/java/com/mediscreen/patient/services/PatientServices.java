package com.mediscreen.patient.services;

import com.mediscreen.patient.DTO.PatientDTO;

import java.util.List;

public interface PatientServices {

    public PatientDTO getPatient(Integer id);

    public List<PatientDTO> getPatientList();

    public PatientDTO savePatient(PatientDTO patientDTO);

    public PatientDTO updatePatient(PatientDTO patientDTO);

    public PatientDTO deletePatient(Integer id);

    public List<PatientDTO> getPatientByName(String name);

}
