package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path = "/api")
public class ApiController {

    @Autowired
    ObjectMapper mapper;

    @RequestMapping("/things/{id}")
    public Thing getThing(@PathVariable String id) throws IOException {
        String json = "{" +
                            "\"id\":\"" + id + "\", " +
                            "\"name\":\"first\", " +
                            "\"config\": {" +
                                "\\\"one\\\": \\\"1\\\", " +
                                "\\\"two\\\": \\\"2\\\", " +
                                "\\\"three\\\": \\\"3\\\", " +
                                "\\\"nested\\\": {" +
                                    "\\\\\"four\\\\\": \\\\\"4\\\\\", " +
                                    "\\\\\"fi\\ve\\\\\": \\\\\"5\\\\\", " +
                                    "\\\\\"nested\\\\\": {" +
                                        "\\\\\\\"six\\\\\\\": \\\\\\\"6\\\\\\\", " +
                                        "\\\\\\\"seven\\\\\\\": \\\\\\\"7\\\\\\\"" +
                                    "}" +
                                "}" +
                            "}" +
                        "}";

        while(json.contains("\\\"")) json = StringEscapeUtils.unescapeJavaScript(json);

        System.out.println("\n\nUnescaped: " + json + "\n\n");

        return mapper.readValue(json, Thing.class);
    }
}
