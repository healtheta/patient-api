package org.healtheta.model.patient;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.*;
import org.healtheta.model.common.CodeableConcept;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "_PatientCommunication")
public class PatientCommunication implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "_id")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    @Column(name = "_language")
    private List<CodeableConcept> language;

    @Column(name = "_prefered")
    private boolean prefered;
}
