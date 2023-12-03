package academy.kata.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.Date;


@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Data
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @XmlElement(name = "id", required = true)
    private Long id;

    @XmlElement(name = "first_name", required = true)
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "family_name")
    @XmlElement(name = "family_name", required = true)
    private String familyName;

    @Column(name = "second_name")
    @XmlElement(name = "second_name", nillable = true)
    private String secondName;

    @Column(name = "birth_date")
    @XmlElement(name = "birth_date", nillable = true)
    private Date birthDate;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
