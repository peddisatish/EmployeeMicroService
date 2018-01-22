package com.example.demo.controller;

import com.codahale.metrics.annotation.Timed;
import com.example.demo.Logger.ApiLogger;
import com.example.demo.dto.EmployeeDto;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/employee")
public class EmployeeController {

    private static final Logger logger = Logger.getLogger(ApiLogger.class);

    @ApiOperation(value = "Get Employee details",
            notes = "Get Employee details", response = EmployeeController.class)
    @Timed(name = "Employee Details", absolute = true)
    @RequestMapping(path = "/details", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> employeeDetails() {

        logger.debug("Inside employeeDetails() of EmployeeController");
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setAddress("Address1");
        employeeDto.setLastName("peddi");
        employeeDto.setName("satish");
        employeeDto.setMail("peddisatish@yahoo.com");
        HttpHeaders headers = new HttpHeaders();
        logger.debug("Exiting employeeDetails() of EmployeeController");

        return ResponseEntity.ok().headers(headers).body(employeeDto);
    }

}
