package com.assignment.simplecalculator.controller;

import com.assignment.simplecalculator.SimpleCalculatorApplication;
import com.assignment.simplecalculator.exception.DivideByZeroError;
import com.assignment.simplecalculator.service.SimpleCalculatorService;
import com.assignment.simplecalculator.util.TestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.assignment.simplecalculator.util.TestUtils.asJsonString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Prachi Das
 */
@SpringBootTest(classes= SimpleCalculatorApplication.class)
@AutoConfigureMockMvc
public class SimpleCalculatorControllerTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private SimpleCalculatorService simpleCalculatorService;

    @Autowired
    private MockMvc mockMvc;



    @Test
    public void testResponse_returnsStatusOKForADD() throws DivideByZeroError, IOException,Exception {
        when(simpleCalculatorService.add(anyDouble(),anyDouble())).thenReturn(30d);
        when(simpleCalculatorService.getHistory()).thenReturn(mockedResponse());
        mockMvc.perform(get("/calculator?firstNum=10&secondNum=20&operation=add").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andExpect(content().string("{\n" +
                "  \"result\": \"30\",\n" +
                "  \"history\": [\"10+20+30=60\", \"10-5=5\", \"9*9+9=90\"]\n" +
                "}"));

        verify(simpleCalculatorService, times(1)).add(anyDouble(),anyDouble());
    }

    private List<String> mockedResponse() {
        return Arrays.asList(new String[]{"10+20+30=60", "10-5=5", "9*9+9=90"});
    }


}
