package com.example;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Service;

@Service
public class ThingService {

    private String getData(String id) {
        return "{" +
            "\"id\": \"" + id + "\", " +
            "\"name\": \"first\", " +
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
    }

    public String cleanse(String raw) {
        while(raw.contains("\\\"")) {
            raw = StringEscapeUtils.unescapeJavaScript(raw);
        }
        return raw;
    }

    public String getThing(String id) {
        return cleanse(getData(id));
    }
}
