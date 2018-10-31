package org.healtheta.web;

import org.healtheta.model.common.HumanName;
import org.healtheta.model.common.Identifier;
import org.healtheta.model.common.OperationOutcome;
import org.healtheta.model.common.Reference;
import org.healtheta.model.common.repos.HumanNameRepo;
import org.healtheta.model.common.repos.IdentifierRepo;
import org.healtheta.model.common.repos.ReferenceRepo;
import org.healtheta.model.patient.Patient;
import org.healtheta.model.patient.repos.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;

@RestController
public class PatientController {
    @Autowired
    private PatientRepo patientRepo;
    @Autowired
    private IdentifierRepo identifierRepo;
    @Autowired
    private HumanNameRepo humanNameRepo;
    @Autowired
    private ReferenceRepo referenceRepo;

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Patient patient){
        Identifier identifer = patient.getIdentifier();

        //validate param
        if(identifer.getValue() == null){
            return new ResponseEntity<OperationOutcome>(OperationOutcome.InternalError(), HttpStatus.OK);
        }

        //check whether id exists
        if ( identifierRepo.findIdentifierByValue(identifer.getValue()) != null){
            return new ResponseEntity<OperationOutcome>(OperationOutcome.RecordExists(), HttpStatus.OK);
        }

        //save patient record;
        try{
            Patient tmpPatient = new Patient();
            tmpPatient = patientRepo.save(tmpPatient);
            patient.setId(tmpPatient.getId());

            //Create an internal reference to patient;
            Reference reference = new Reference();
            reference.setIdentifier(patient.getIdentifier());
            reference.setDisplay("patient-reference");
            patient.setReference(reference);
            patient = patientRepo.save(patient);
            return new ResponseEntity<Patient>(patient, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<OperationOutcome>(OperationOutcome.InternalError(), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/read/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> read(@PathVariable String id){
        try{
            Long lId = Long.parseLong(id);
            Patient patient = patientRepo.findPatientById(lId);
            if(patient != null)
                return new ResponseEntity<Patient>(patient, HttpStatus.OK);
            else
                return new ResponseEntity<OperationOutcome>(OperationOutcome.RecordNotFound(), HttpStatus.NOT_FOUND);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<OperationOutcome>(OperationOutcome.InternalError(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody Patient patient){
        Long id = patient.getId();
        Patient tmpPatient = patientRepo.findPatientById(id);
        if ( tmpPatient != null){
            //record exists let;s update.
            //reference are not to be modified.
            patient.setReference(tmpPatient.getReference());
            patient = patientRepo.save(patient);
            return new ResponseEntity<Patient>(patient, HttpStatus.OK);
        }else{
            return new ResponseEntity<OperationOutcome>(OperationOutcome.RecordNotFound(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> delete(String id) {

        return null;
    }

    @RequestMapping(value = "/search",
                    method = RequestMethod.GET,
                    produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> search(@RequestParam(value = "familyName", required=false) String familyName,
                                        @RequestParam(value = "givenName", required=false) String givenName,
                                            @RequestParam(value = "orgRefId", required=false) Long orgRefId,
                                                @RequestParam(value = "practitionerRefId", required=false) Long practitionerRefId){
        if ( familyName != null && givenName != null){
            //do family;given name search
            List<HumanName> humanNameList = humanNameRepo.findHumanNameByFamilyAndGiven(familyName, givenName);
            System.out.println(humanNameList.size());
            List<Patient> patientList = patientRepo.findByNameIn(humanNameList);
            return new ResponseEntity<List>(patientList, HttpStatus.NOT_FOUND);
        }
        else if ( orgRefId != null && practitionerRefId == null){
            Reference orgReference = referenceRepo.findReferenceById(orgRefId);
            if(orgReference == null){
                return new ResponseEntity<OperationOutcome>(OperationOutcome.RecordNotFound(), HttpStatus.OK);
            }else {
                List<Patient> patientList = patientRepo.findPatientByManagingOrganization(orgReference);
                return new ResponseEntity<List>(patientList, HttpStatus.OK);
            }
            //do orgReference based search
        }
        else if (practitionerRefId != null && orgRefId == null){
            Reference practReference = referenceRepo.findReferenceById(practitionerRefId);
            if(practReference == null){
                return new ResponseEntity<OperationOutcome>(OperationOutcome.RecordNotFound(), HttpStatus.OK);
            }else {
                List<Patient> patientList = patientRepo.findPatientByGeneralPractitioner(practReference);
                return new ResponseEntity<List>(patientList, HttpStatus.OK);
            }
        }else if ( orgRefId != null && practitionerRefId != null){
            Reference orgReference = referenceRepo.findReferenceById(orgRefId);
            Reference practReference = referenceRepo.findReferenceById(practitionerRefId);
            if(orgReference == null || practReference == null){
                return new ResponseEntity<OperationOutcome>(OperationOutcome.RecordNotFound(), HttpStatus.OK);
            }else{
                List<Patient> patientList = patientRepo.findPatientByManagingOrganizationAndGeneralPractitioner(orgReference,
                                                    practReference);
                return new ResponseEntity<List>(patientList, HttpStatus.OK);
            }
        }else{
            return new ResponseEntity<OperationOutcome>(OperationOutcome.OperationNotSupported(), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/test",
            method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> test(){
        Patient patient =  new Patient();
        Identifier identifier = new Identifier();
        identifier.setValue("999999999");
        patient.setIdentifier(identifier);
        HumanName name = new HumanName();
        name.setFamily("Doe");
        name.setGiven("John");
        patient.setName(name);
        return new ResponseEntity<Patient>(patient, HttpStatus.OK);
    }
}