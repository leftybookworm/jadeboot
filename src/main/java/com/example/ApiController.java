package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path = "/api")
public class ApiController {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ThingService service;

    @RequestMapping("/things/{id}")
    public Thing getThing(@PathVariable String id) {
        String json = service.getThing(id);
        Thing thing;
        try {
            thing = mapper.readValue(json, Thing.class);
        } catch(IOException ex) {
            throw new DataMarshallingException();
        }
        return thing;
    }
}
