package com.mediscreen.patient.TU;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediscreen.patient.DTO.PatientDTO;
import com.mediscreen.patient.services.PatientServices;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private LocalDate birthD ;

    @MockBean
    private PatientServices patientServices;

    private PatientDTO patientDTO ;

    private PatientDTO patientDTO2 ;

    private List<PatientDTO> patientDTOList = new ArrayList<>();

    private int id;


    @BeforeEach
    public  void setup(){

        int id = 1 ;

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        birthD = LocalDate.parse("01/01/1995",df);

        patientDTO = new PatientDTO(id,"testt","TESTT", birthD,"H","10 avenue des tests","0605040302");
        patientDTO2 = new PatientDTO(id,"testt2","TESTT2", birthD,"F","20 avenue des tests","0607080910");

        patientDTOList.add(patientDTO);
        patientDTOList.add(patientDTO);

    }

    @Test
    public void getHomePage_isOk() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void getPatient() throws Exception {

        when(patientServices.getPatient(anyInt())).thenReturn(patientDTO);

        mockMvc.perform(get("/getPatient?id="+id))
                .andExpect(status().isOk());
    }

    @Test
    public void getPatientList() throws Exception{

        when(patientServices.getPatientList()).thenReturn(patientDTOList);

        mockMvc.perform((get("/getPatientList")))
                .andExpect(status().isOk());
    }

    @Test
    public void savePatient() throws Exception{

        when(patientServices.savePatient(any())).thenReturn(patientDTO);

        mockMvc.perform((post("/addPatient"))
                .content(asJsonString(patientDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    public void updatePatient() throws Exception{

        when(patientServices.updatePatient(any())).thenReturn(patientDTO);

        mockMvc.perform((post("/updatePatient"))
                .content(asJsonString(patientDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }



    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
