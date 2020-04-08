package co.com.dcatano.Patient.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class Test {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = "/ping")
    public String ping() {
        logger.debug("-----> Ping received");
        return "Hello";
    }
}
