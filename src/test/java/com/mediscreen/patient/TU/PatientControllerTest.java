package com.mediscreen.patient.TU;

import com.mediscreen.patient.DTO.PatientDTO;
import com.mediscreen.patient.services.PatientServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private LocalDate birthD ;

    @MockBean
    private PatientServices patientServices;

    @Test
    public void getHomePage_isOk() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void getPatient() throws Exception {

        int id = 1 ;

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        birthD = LocalDate.parse("01/01/1995",df);

        PatientDTO patientDTO = new PatientDTO(id,"testt","TESTT", birthD,"H","10 avenue des tests","0605040302");

        when(patientServices.getPatient(anyInt())).thenReturn(patientDTO);

        mockMvc.perform(get("/getPatient?id="+id))
                .andExpect(status().isOk());
    }
}
