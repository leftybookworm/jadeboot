package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JadebootApplication.class)
public class ThingTests {

    @Autowired
    ThingService service;

	@Test
	public void get_thing_removes_unwanted_quote_escapes() {
        String actual = "{\"a\": \"aaa\", \"b\": {\\\"n\\\": 1}}";
        String expected = "{\"a\": \"aaa\", \"b\": {\"n\": 1}}";
        assertThat(service.cleanse(actual), equalTo(expected));
	}

}
