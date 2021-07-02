package com.mediscreen.patient.services;

import com.mediscreen.patient.DTO.PatientDTO;

public interface PatientServices {

    public PatientDTO getPatient(Integer id);

    public PatientDTO savePatient(PatientDTO patientDTO);

    public PatientDTO updatePatient(PatientDTO patientDTO);

}
