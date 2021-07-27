package com.mediscreen.patient.repositories;


import com.mediscreen.patient.domain.Patient;
import com.mediscreen.patient.services.PatientServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {

    public Patient findPatientByPrenomAndNomAndDateDeNaissance(String prenom, String nom, LocalDate dateDeNaissance);

    public List<Patient> findByNom(String name);

}
