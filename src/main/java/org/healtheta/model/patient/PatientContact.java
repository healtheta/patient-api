package org.healtheta.model.patient;

import org.healtheta.model.common.Address;
import org.healtheta.model.common.ContactPoint;
import org.healtheta.model.common.Period;
import org.healtheta.model.common.Reference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "PatientContact")
public class PatientContact implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    private Long id;

    @Column(name = "_firstname")
    private String firstname;

    @Column(name = "_lastname")
    private String lastname;

    @OneToMany(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    @Column(name = "_telecom")
    private List<ContactPoint> telecom;

    @OneToMany(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    @Column(name = "_address")
    private List<Address> address;

    @Column(name = "_gender")
    private String gender;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(name = "_organization")
    private Reference organization;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(name = "_period")
    private Period period;
}
