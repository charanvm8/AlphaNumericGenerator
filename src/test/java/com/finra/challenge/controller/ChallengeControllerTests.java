package com.finra.challenge.controller;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest(classes = ChallengeController.class)
@AutoConfigureMockMvc
public class ChallengeControllerTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    public void getAlphaNumericValues() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(post("/getAlphaNumericValues")
                .contentType(MediaType.APPLICATION_JSON).content("{\"number\":\"43643643\",\"pageSize\":0}").characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
