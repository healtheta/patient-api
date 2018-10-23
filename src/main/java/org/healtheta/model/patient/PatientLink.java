package org.healtheta.model.patient;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PatientLink")
public class PatientLink implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "_id")
    private Long id;
    @Column(name = "_type")
    private String type;
    @Column(name = "_url")
    private String url;

    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }
}
