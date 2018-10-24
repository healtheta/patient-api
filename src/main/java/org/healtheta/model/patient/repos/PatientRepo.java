package org.healtheta.model.patient.repos;

import org.healtheta.model.common.HumanName;
import org.healtheta.model.common.Reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.healtheta.model.patient.Patient;
import org.healtheta.model.common.Identifier;
import java.util.List;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Long> {
    public Patient findPatientById(Long id);
    public Patient findPatientByIdentifier(Identifier identifier);
    public List<Patient> findByNameIn(List<HumanName> humanNameList);
    public List<Patient> findPatientByManagingOrganization(Reference managingOrganization);
    public List<Patient> findPatientByGeneralPractitioner(Reference generalPractitioner);
    public List<Patient> findPatientByManagingOrganizationAndGeneralPractitioner(Reference managingOrganization,
                                                                                        Reference generalPractitioner);
    public List<Patient> findAll();
}
