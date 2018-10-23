package org.healtheta.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/patient")
public class PatientController {
    /*@Autowired
    private PatientRepository patientRepository;
    @Autowired
    private IdentifierRepository identifierRepository;*/

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update() {
        return "updating a patient object";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete() {
        return "deleting a patient object";
    }
}