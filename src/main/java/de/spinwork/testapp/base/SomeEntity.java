package de.spinwork.testapp.base;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Entity class with an association
 */
@Entity
public class SomeEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String property;

    @OneToMany
    private List<AnotherEntity> others;

    public Long getId() {
        return id;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public List<AnotherEntity> getOthers() {
        return others;
    }

    public void setOthers(List<AnotherEntity> others) {
        this.others = others;
    }
}
