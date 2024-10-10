package ru.rsreu.sanitary_ware;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class Lab1ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getHome() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void getLostPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void getNamePage() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(view().name("home"));
    }

    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(model().attribute("name", "магазин сантехники"));
    }


}
