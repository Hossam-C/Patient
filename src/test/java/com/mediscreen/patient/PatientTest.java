package com.mediscreen.patient;

import com.mediscreen.patient.DTO.PatientDTO;
import com.mediscreen.patient.domain.Patient;
import com.mediscreen.patient.repositories.PatientRepository;
import com.mediscreen.patient.services.PatientServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PatientTest {

    @Autowired
    private PatientServices patientServices;

    @Autowired
    private PatientRepository patientRepository;


    private Patient patient = new Patient();
    private Patient patient2 = new Patient();
    private PatientDTO patientDTO = new PatientDTO();
    private PatientDTO patientDTO2 = new PatientDTO();
    private int id;
    private LocalDate birthD ;

    @BeforeEach
    public  void setup() {

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        birthD = LocalDate.parse("01/01/1995",df);

        patientRepository.deleteAll();

        patient.setPrenom("test99");
        patient.setNom("Test99");
        patient.setDateDeNaissance(birthD);
        patient.setGenre("F");
        patient.setAdressePostale("1 rue des tests");
        patient.setNumeroDeTelephone("0601010101");

        patient2.setPrenom("test222");
        patient2.setNom("Test222");
        patient2.setDateDeNaissance(birthD);
        patient2.setGenre("H");
        patient2.setAdressePostale("2 rue des tests");
        patient2.setNumeroDeTelephone("0602020202");


        patientDTO.setPrenom("test");
        patientDTO.setNom("Test");
        patientDTO.setDateDeNaissance(birthD);
        patientDTO.setGenre("F");
        patientDTO.setAdressePostale("1 rue des tests");
        patientDTO.setNumeroDeTelephone("0601010101");

        patientDTO2.setPrenom("test2");
        patientDTO2.setNom("Test2");
        patientDTO2.setDateDeNaissance(birthD);
        patientDTO2.setGenre("H");
        patientDTO2.setAdressePostale("2 rue des tests");
        patientDTO2.setNumeroDeTelephone("0602020202");

    }


    @Test
    public void getPatientTest() {

        patientRepository.save(patient);

        System.out.println("Date : "+patientDTO.getDateDeNaissance());

        id = patientRepository.findPatientByPrenomAndNomAndDateDeNaissance("test99","Test99", birthD).getId();

        PatientDTO patientDTO = patientServices.getPatient(id);

        assertThat(patientDTO.getPrenom()).isEqualTo("test99");

    }

    @Test
    public void getPatientList(){

        patientRepository.save(patient);
        patientRepository.save(patient2);

        List<PatientDTO> patientDTOList = patientServices.getPatientList();

        assertThat(patientDTOList.size()).isEqualTo(2);
    }

    @Test
    public void savePatientTest() {



        patientServices.savePatient(patientDTO);

        assertThat(patientRepository.findPatientByPrenomAndNomAndDateDeNaissance("test","Test", birthD).getNom()).isEqualTo("Test");

    }

    @Test
    public void updatePatientTest() {

        patientRepository.save(patient);

        id = patientRepository.findPatientByPrenomAndNomAndDateDeNaissance("test99","Test99", birthD).getId();

        patientDTO.setId(id);

        patientServices.updatePatient(patientDTO);

        assertThat(patientRepository.findPatientByPrenomAndNomAndDateDeNaissance("test","Test", birthD).getNom()).isEqualTo("Test");

    }
}
