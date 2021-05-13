package com.taskman.controller.rest;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.taskman.entity.User;
import com.taskman.repository.UserRepository;
import com.taskman.util.JsonUtil;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserRestControllerIT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository repository;
    
    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }  
    
    @Test
    public void whenValidInput_thenCreateUser() throws IOException, Exception {
        User bob = new User("bob", "bob@email.com");
        mvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(bob)));
        Iterable <User> found = repository.findAll();
        assertThat(found).extracting(User::getName).containsOnly("bob");
    }    

}
