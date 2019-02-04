package org.optidb.optidbclient.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
public class MainControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void liste() throws Exception {
        this.mockMvc.perform(get("/liste")).andExpect(status().isOk());
    }

    @Test
    public void home() throws Exception {
        this.mockMvc.perform(get("/home")).andExpect(status().isOk());
    }

    @Test
    public void platformVersion() throws Exception{
        this.mockMvc.perform(get("/liste")).andExpect(status().isOk());
    }

    @Test
    public void getAllPlatforms() {
    }

    @Test
    public void getResultat() {
    }
}