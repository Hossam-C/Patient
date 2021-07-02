package com.mediscreen.patient.repositories;


import com.mediscreen.patient.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {

    public Patient findPatientByPrenomAndNomAndDateDeNaissance(String prenom, String nom, LocalDate dateDeNaissance);

}
